package db.api.service;
import db.entities.SportClub;
import db.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import db.entities.Athlete;

import java.util.Objects;

@Service
public class AthleteService {
    private final AthleteRepository athleteRepository;

    @Autowired
    public AthleteService(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    public Athlete addAthlete(String firstName, String patronymic, String lastName, SportClub club) {
        Athlete athlete = new Athlete(firstName, patronymic, lastName, club);
        athleteRepository.save(athlete);
        return athlete;
    }

    public Athlete deleteAthlete(Long athleteId) {
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow();
        athleteRepository.delete(athlete);
        return athlete;
    }

    public Athlete updateAthlete(Long id, String firstName, String patronymic, String lastName, SportClub club) {
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
