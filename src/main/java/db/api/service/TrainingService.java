package db.api.service;

import db.entities.*;
import db.entities.Training;
import db.entities.models.keys.TrainingKey;
import db.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainerLicenseRepository trainerLicenseRepository;
    private final AthleteRepository athleteRepository;

    public Training addTraining(Long id, Long athleteId) {
        TrainerLicense trainerLicense = trainerLicenseRepository.getTrainerLicenseById(id);
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
