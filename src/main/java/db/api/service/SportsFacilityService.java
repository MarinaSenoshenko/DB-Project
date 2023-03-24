package db.api.service;

import db.entities.SportsFacilityType;
import db.entities.models.surface.SportsFacility;
import db.repository.sports.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportsFacilityService {
    private final SportsFacilityRepository sportsFacilityRepository;

    @Autowired
    public SportsFacilityService(SportsFacilityRepository sportsFacilityRepository) {
        this.sportsFacilityRepository = sportsFacilityRepository;
    }

    public SportsFacility addSportsFacility(String address, SportsFacilityType type) {
        SportsFacility sportsFacility = new SportsFacility(address, type);
        sportsFacilityRepository.save(sportsFacility);
        return sportsFacility;
    }
}