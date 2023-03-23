package db.api.service;

import db.entities.SportsFacilityType;
import db.repository.SportsFacilityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportsFacilityTypeService {
    private final SportsFacilityTypeRepository sportsFacilityTypeRepository;

    @Autowired
    public SportsFacilityTypeService(SportsFacilityTypeRepository sportsFacilityTypeRepository) {
        this.sportsFacilityTypeRepository = sportsFacilityTypeRepository;
    }

    public SportsFacilityType addSportsFacilityType(String value) {
        SportsFacilityType sportsFacilityType = new SportsFacilityType(value);
        sportsFacilityTypeRepository.save(sportsFacilityType);
        return sportsFacilityType;
    }
}
