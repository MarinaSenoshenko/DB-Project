package db.api.rest_controllers;

import db.api.service.SportsFacilityService;
import db.entities.SportsFacilityType;
import db.entities.models.surface.SportsFacility;
import db.repository.SportsFacilityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addsportsfacility")
public class AddSportsFacilityController {
    private final SportsFacilityService sportsFacilityService;
    private final SportsFacilityTypeRepository sportsFacilityTypeRepository;

    @Autowired
    public AddSportsFacilityController(SportsFacilityService sportsFacilityService,
                                       SportsFacilityTypeRepository sportsFacilityTypeRepository) {
        this.sportsFacilityService = sportsFacilityService;
        this.sportsFacilityTypeRepository = sportsFacilityTypeRepository;
    }

    //TODO inset new facility to both tables (post to sports_facility and to arena/stadium/court/gym)
    @PostMapping("")
    public SportsFacility addSportsFacility(@RequestParam("address") String address,
                                            @RequestParam("type") String value) {
        SportsFacilityType type = sportsFacilityTypeRepository.getSportsFacilityByValue(value);
        return sportsFacilityService.addSportsFacility(address, type);
    }
}
