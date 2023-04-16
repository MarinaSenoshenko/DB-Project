package db.api.service;

import db.entities.Sport;
import db.entities.Trainer;
import db.entities.TrainerLicense;
import db.repository.SportRepository;
import db.repository.TrainerLicenseRepository;
import db.repository.TrainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainerLicenseService {
    private final TrainerLicenseRepository trainerLicenseRepository;
    private final TrainerRepository trainerRepository;
    private final SportRepository sportRepository;

    public TrainerLicense addTrainerLicense(Long trainerId, String value) {
        Sport sport = sportRepository.findByValue(value);
        if (trainerLicenseRepository.getTrainerLicenseByTrainerAndSport(trainerId, sport.getId()) != null) {
             return null;
        }
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow();
        TrainerLicense trainerLicense = new TrainerLicense(trainer, sport);
        trainerLicenseRepository.save(trainerLicense);
        return trainerLicense;
    }

    public TrainerLicense deleteTrainerLicense(Long trainerLicenseId) {
        TrainerLicense trainerLicense = trainerLicenseRepository.getTrainerLicenseById(trainerLicenseId);
        trainerLicenseRepository.delete(trainerLicense);
        return trainerLicense;
    }
}
