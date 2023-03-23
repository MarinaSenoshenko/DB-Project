package db.api.service;

import db.entities.Trainer;
import db.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }
    public Trainer addTrainer(String firstName, String patronymic, String lastName) {
        Trainer trainer = new Trainer(firstName, patronymic, lastName);
        trainerRepository.save(trainer);
        return trainer;
    }
}