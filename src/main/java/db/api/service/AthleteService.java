package db.api.service;

import db.entities.Athlete;
import db.entities.SportClub;
import db.repository.AthleteRepository;
import db.repository.SportClubRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AthleteService {
    private final AthleteRepository athleteRepository;
    private final SportClubRepository sportClubRepository;

    public Athlete addAthlete(String firstName, String patronymic, String lastName, String title) {
        SportClub club = sportClubRepository.findByTitle(title);
        Athlete athlete = new Athlete(firstName, patronymic, lastName, club);
        athleteRepository.save(athlete);
        return athlete;
    }

    public Athlete deleteAthlete(Long athleteId) {
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow();
        athleteRepository.delete(athlete);
        return athlete;
    }

    public Athlete updateAthlete(Long id, String firstName, String patronymic, String lastName, String title) {
        SportClub club = sportClubRepository.findByTitle(title);
        Athlete athlete = athleteRepository.findById(id).orElseThrow();
        if (!Objects.equals(patronymic, "")) {
            athlete.setPatronymic(patronymic);
        }
        if (!Objects.equals(firstName, "")) {
            athlete.setFirstName(firstName);
        }
        if (!Objects.equals(lastName, "")) {
            athlete.setLastName(lastName);
        }
        athlete.setClub(club);
        athleteRepository.save(athlete);
        return athlete;
    }
}
