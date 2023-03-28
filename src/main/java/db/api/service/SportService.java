package db.api.service;

import db.entities.Sport;
import db.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportService {
    private final SportRepository sportRepository;

    @Autowired
    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public Sport addSport(String value) {
        Sport sport = new Sport(value);
        sportRepository.save(sport);
        return sport;
    }

    public Sport deleteSport(Long sportId) {
        Sport sport = sportRepository.getSportById(sportId);
        sportRepository.delete(sport);
        return sport;
    }
}
