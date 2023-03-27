package db.api.service;

import db.entities.SportClub;
import db.repository.SportClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportClubService {
    private final SportClubRepository sportClubRepository;

    @Autowired
    public SportClubService(SportClubRepository sportClubRepository) {
        this.sportClubRepository = sportClubRepository;
    }

    public SportClub addSportClub(String value) {
        SportClub sportClub = new SportClub(value);
        sportClubRepository.save(sportClub);
        return sportClub;
    }

    public SportClub deleteSportClub(String value) {
        SportClub sportClub = sportClubRepository.getSportClubByTitle(value);
        sportClubRepository.delete(sportClub);
        return sportClub;
    }
}