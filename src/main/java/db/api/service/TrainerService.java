package db.api.service;

import db.entities.Trainer;
import db.repository.TrainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public Trainer addTrainer(String firstName, String patronymic, String lastName) {
        Trainer trainer = new Trainer(firstName, patronymic, lastName);
        trainerRepository.save(trainer);
        return trainer;
    }

    public Trainer deleteTrainer(Long trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow();
        trainerRepository.delete(trainer);
        return trainer;
    }

    public Trainer updateTrainer(Long id, String firstName, String patronymic, String lastName) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow();
        if (!Objects.equals(patronymic, "")) {
            trainer.setPatronymic(patronymic);
        }
        if (!Objects.equals(firstName, "")) {
            trainer.setFirstName(firstName);
        }
        if (!Objects.equals(lastName, "")) {
            trainer.setLastName(lastName);
        }
        trainerRepository.save(trainer);
        return trainer;
    }
}
