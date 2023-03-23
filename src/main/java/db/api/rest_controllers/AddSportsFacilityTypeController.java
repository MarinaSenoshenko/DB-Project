package db.api.rest_controllers;

import db.api.service.SportsFacilityTypeService;
import db.entities.SportsFacilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addsportsfacilitytype")
public class AddSportsFacilityTypeController {
    private final SportsFacilityTypeService sportsFacilityTypeService;

    @Autowired
    public AddSportsFacilityTypeController(SportsFacilityTypeService sportsFacilityTypeService) {
        this.sportsFacilityTypeService = sportsFacilityTypeService;
    }

    @PostMapping("")
    public SportsFacilityType addRank(@RequestParam("sport_facility_type") String value) {
        return sportsFacilityTypeService.addSportsFacilityType(value);
    }
}
