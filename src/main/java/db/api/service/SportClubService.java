package db.api.service;

import db.entities.SportClub;
import db.repository.SportClubRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SportClubService {
    private final SportClubRepository sportClubRepository;

    public SportClub addSportClub(String value) {
        SportClub sportClub = new SportClub(value);
        sportClubRepository.save(sportClub);
        return sportClub;
    }

    public SportClub deleteSportClub(Long sportClubId) {
        SportClub sportClub = sportClubRepository.findById(sportClubId).orElseThrow();
        sportClubRepository.delete(sportClub);
        return sportClub;
    }

    public SportClub updateSportClub(Long id, String value) {
        SportClub sportClub = sportClubRepository.findById(id).orElseThrow();
        sportClub.setTitle(value);
        sportClubRepository.save(sportClub);
        return sportClub;
    }
}