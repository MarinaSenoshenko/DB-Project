package db.api.rest_controllers;

import db.api.service.SportsFacilityService;
import db.entities.SportsFacilityType;
import db.entities.models.surface.*;
import db.repository.SportsFacilityTypeRepository;
import db.repository.sports.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addsportsfacility")
public class AddSportsFacilityController {
    private final SportsFacilityService sportsFacilityService;
    private final SportsFacilityTypeRepository sportsFacilityTypeRepository;
    private final SportsFacilityRepository sportsFacilityRepository;

    @Autowired
    public AddSportsFacilityController(SportsFacilityService sportsFacilityService,
                                       SportsFacilityTypeRepository sportsFacilityTypeRepository,
                                       SportsFacilityRepository sportsFacilityRepository) {
        this.sportsFacilityService = sportsFacilityService;
        this.sportsFacilityRepository = sportsFacilityRepository;
        this.sportsFacilityTypeRepository = sportsFacilityTypeRepository;
    }

    @PostMapping("")
    public SportsFacility addSportsFacility(@RequestParam("address") String address,
                                            @RequestParam("type") String value) {
        SportsFacilityType type = sportsFacilityTypeRepository.getSportsFacilityByValue(value);
        return sportsFacilityService.addSportsFacility(address, type);
    }

    @PostMapping("/addarena")
    public Arena addArena(@RequestParam("param") String param) {
        Long id = sportsFacilityRepository.getMaxFacilityId(param);
        SportsFacility sportsFacility = sportsFacilityRepository.getSportsFacilityById(id);
        return sportsFacilityService.addArenaParam(param, sportsFacility);
    }

    //TODO fix inner entity
    @PostMapping("/addcourt")
    public Court addCourt(@RequestParam("param") String param) {
        Long id = sportsFacilityRepository.getMaxFacilityId(param);
        SportsFacility sportsFacility = sportsFacilityRepository.getSportsFacilityById(id);
        return sportsFacilityService.addCourtParam(param, sportsFacility);
    }

    @PostMapping("/addgym")
    public Gym addGym(@RequestParam("param") String param) {
        Long id = sportsFacilityRepository.getMaxFacilityId(param);
        SportsFacility sportsFacility = sportsFacilityRepository.getSportsFacilityById(id);
        return sportsFacilityService.addGymParam(param, sportsFacility);
    }

    @PostMapping("/addstadium")
    public Stadium addStadium(@RequestParam("param") String param) {
        Long id = sportsFacilityRepository.getMaxFacilityId(param);
        SportsFacility sportsFacility = sportsFacilityRepository.getSportsFacilityById(id);
        return sportsFacilityService.addStadiumParam(param, sportsFacility);
    }
}
