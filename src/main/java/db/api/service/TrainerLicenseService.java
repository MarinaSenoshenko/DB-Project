package db.api.service;

import db.entities.Sport;
import db.entities.Trainer;
import db.entities.TrainerLicense;
import db.entities.models.keys.LicenseKey;
import db.repository.SportRepository;
import db.repository.TrainerLicenseRepository;
import db.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerLicenseService {
    private final TrainerLicenseRepository trainerLicenseRepository;
    private final TrainerRepository trainerRepository;
    private final SportRepository sportRepository;

    @Autowired
    public TrainerLicenseService(TrainerLicenseRepository trainerLicenseRepository,
                                 TrainerRepository trainerRepository,
                                 SportRepository sportRepository) {
        this.trainerLicenseRepository = trainerLicenseRepository;
        this.trainerRepository = trainerRepository;
        this.sportRepository = sportRepository;
    }

    public TrainerLicense addTrainerLicense(Long trainerId, String value) {
        Sport sport = sportRepository.getSportByValue(value);
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow();
        TrainerLicense trainerLicense = new TrainerLicense(new LicenseKey(trainer, sport));
        System.out.println(trainerLicense);

        trainerLicenseRepository.save(trainerLicense);
        return trainerLicense;
    }

    public TrainerLicense deleteTrainerLicense(Long trainerLicenseId) {
        TrainerLicense trainerLicense = trainerLicenseRepository.getTrainerLicenseById(trainerLicenseId);
        trainerLicenseRepository.delete(trainerLicense);
        return trainerLicense;
    }
}
