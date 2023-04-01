package db.api.service;

import db.entities.Athlete;
import db.entities.users.AthletePassword;
import db.repository.AthleteRepository;
import db.repository.user.AthletePasswordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class RegistrationService {
    private final AthleteRepository athleteRepository;
    private final AthletePasswordRepository athletePasswordRepository;
    public AthletePassword addAthlete(String firstName, String lastName, String patronymic, String login, String password) {
        Iterable<Athlete> athletes = athleteRepository.findAll();
        for (var athlete: athletes) {
            if (Objects.equals(athlete.getFirstName(), firstName) && Objects.equals(athlete.getLastName(), lastName)
                    && Objects.equals(athlete.getPatronymic(), patronymic)) {
                AthletePassword athletePassword = new AthletePassword(athlete, password, login);
                System.out.println(athletePassword);
                athletePasswordRepository.save(athletePassword);
                return athletePassword;
            }
        }
        return null;
    }
}
