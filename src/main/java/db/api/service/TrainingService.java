package db.api.service;

import db.entities.*;
import db.entities.Training;
import db.entities.models.keys.TrainingKey;
import db.repository.AthleteRepository;
import db.repository.TrainerLicenseRepository;
import db.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainerLicenseRepository trainerLicenseRepository;
    private final AthleteRepository athleteRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository,
                           TrainerLicenseRepository trainerLicenseRepository,
                           AthleteRepository athleteRepository) {
        this.trainingRepository = trainingRepository;
        this.trainerLicenseRepository = trainerLicenseRepository;
        this.athleteRepository = athleteRepository;
    }

    // TODO Кого ты там находишь по одному id?
    public Training addTraining(Long trainerId, Long athleteId) {
        TrainerLicense trainerLicense = trainerLicenseRepository.getTrainerLicenseById(trainerId);
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow();
        Training training = new Training(new TrainingKey(athlete, trainerLicense));
        trainingRepository.save(training);
        return training;
    }

    public Training deleteTraining(Long trainerId, Long athleteId) {
        Training training = trainingRepository.deleteTrainingByTrainerAndAthleteId(trainerId, athleteId);
        trainingRepository.delete(training);
        return training;
    }
}
