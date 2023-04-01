package db.api.service;

import db.entities.Sport;
import db.repository.SportRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SportService {
    private final SportRepository sportRepository;

    public Sport addSport(String value) {
        Sport sport = new Sport(value);
        sportRepository.save(sport);
        return sport;
    }

    public Sport deleteSport(Long sportId) {
        Sport sport = sportRepository.findById(sportId).orElseThrow();
        sportRepository.delete(sport);
        return sport;
    }

    public Sport updateSport(Long id, String value) {
        Sport sport = sportRepository.findById(id).orElseThrow();
        sport.setValue(value);
        sportRepository.save(sport);
        return sport;
    }
}
