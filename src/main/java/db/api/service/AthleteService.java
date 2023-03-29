package db.api.service;
import db.entities.SportClub;
import db.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import db.entities.Athlete;

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
}
