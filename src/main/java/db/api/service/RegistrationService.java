package db.api.service;

import db.entities.*;
import db.entities.users.*;
import db.repository.*;
import db.repository.user.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class RegistrationService {
    private final AthleteRepository athleteRepository;
    private final TrainerRepository trainerRepository;
    private final AthletePasswordRepository athletePasswordRepository;
    private final TrainerPasswordRepository trainerPasswordRepository;

    public AthletePassword addAthlete(Long id, String firstName, String lastName, String patronymic, String login, String password) {
        Athlete athlete = athleteRepository.findById(id).orElseThrow();
        if (Objects.equals(athlete.getFirstName(), firstName) && Objects.equals(athlete.getLastName(), lastName)
                && Objects.equals(athlete.getPatronymic(), patronymic)) {
                AthletePassword athletePassword = new AthletePassword(athlete, password, login);
                athletePasswordRepository.save(athletePassword);
                return athletePassword;
        }
        return null;
    }

    public TrainerPassword addTrainer(Long id, String firstName, String lastName, String patronymic, String login, String password) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow();
        if (Objects.equals(trainer.getFirstName(), firstName) && Objects.equals(trainer.getLastName(), lastName)
                && Objects.equals(trainer.getPatronymic(), patronymic)) {
            TrainerPassword trainerPassword = new TrainerPassword(trainer, password, login);
            trainerPasswordRepository.save(trainerPassword);
            return trainerPassword;
        }
        return null;
    }
}
