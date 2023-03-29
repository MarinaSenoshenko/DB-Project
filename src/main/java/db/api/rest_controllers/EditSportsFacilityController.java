package db.api.rest_controllers;

import db.api.service.SportsFacilityService;
import db.entities.CourtSurface;
import db.entities.SportsFacilityType;
import db.entities.models.surface.*;
import db.repository.CourtSurfaceRepository;
import db.repository.SportsFacilityTypeRepository;
import db.repository.sports.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sportsfacility")
public class EditSportsFacilityController {
    private final SportsFacilityService sportsFacilityService;
    private final SportsFacilityTypeRepository sportsFacilityTypeRepository;
    private final SportsFacilityRepository sportsFacilityRepository;
    private final CourtSurfaceRepository courtSurfaceRepository;

    @Autowired
    public EditSportsFacilityController(SportsFacilityService sportsFacilityService,
                                        SportsFacilityTypeRepository sportsFacilityTypeRepository,
                                        SportsFacilityRepository sportsFacilityRepository,
                                        CourtSurfaceRepository courtSurfaceRepository) {
        this.sportsFacilityService = sportsFacilityService;
        this.sportsFacilityRepository = sportsFacilityRepository;
        this.sportsFacilityTypeRepository = sportsFacilityTypeRepository;
        this.courtSurfaceRepository = courtSurfaceRepository;
    }

    @PostMapping("")
    public SportsFacility addSportsFacility(@RequestParam("address") String address,
                                            @RequestParam("type") String value) {
        SportsFacilityType type = sportsFacilityTypeRepository.getSportsFacilityByValue(value);
        return sportsFacilityService.addSportsFacility(address, type);
    }

    @DeleteMapping("")
    public SportsFacility deleteSportsFacility(@RequestParam("sportsfacility") Long facilityId) {
        return sportsFacilityService.deleteSportsFacility(facilityId);
    }

    @PostMapping("/arena")
    public Arena addArena(@RequestParam("param") String param) {
        Long id = sportsFacilityRepository.getMaxFacilityId(param);
        SportsFacility sportsFacility = sportsFacilityRepository.findById(id).orElseThrow();
        return sportsFacilityService.addArenaParam(param, sportsFacility);
    }

    @DeleteMapping("/arena")
    public Arena deleteArena(@RequestParam("param") Long arenaId) {
        return sportsFacilityService.deleteArenaParam(arenaId);
    }

    @PostMapping("/court")
    public Court addCourt(@RequestParam("param") String param) {
        Long id = sportsFacilityRepository.getMaxFacilityId(param);
        SportsFacility sportsFacility = sportsFacilityRepository.findById(id).orElseThrow();
        CourtSurface surface = courtSurfaceRepository.getCourtSurfaceByValue(param);
        return sportsFacilityService.addCourtParam(surface, sportsFacility);
    }

    @DeleteMapping("/court")
    public Court deleteCourt(@RequestParam("param") Long courtId) {
        return sportsFacilityService.deleteCourtParam(courtId);
    }

    @PostMapping("/gym")
    public Gym addGym(@RequestParam("param") String param) {
        Long id = sportsFacilityRepository.getMaxFacilityId(param);
        SportsFacility sportsFacility = sportsFacilityRepository.findById(id).orElseThrow();
        return sportsFacilityService.addGymParam(param, sportsFacility);
    }

    @DeleteMapping("/gym")
    public Gym deleteGym(@RequestParam("param") Long gymId) {
        return sportsFacilityService.deleteGymParam(gymId);
    }

    @PostMapping("/stadium")
    public Stadium addStadium(@RequestParam("param") String param) {
        Long id = sportsFacilityRepository.getMaxFacilityId(param);
        SportsFacility sportsFacility = sportsFacilityRepository.findById(id).orElseThrow();
        return sportsFacilityService.addStadiumParam(param, sportsFacility);
    }

    @DeleteMapping("/stadium")
    public Stadium deleteStadium(@RequestParam("param") Long stadiumId) {
        return sportsFacilityService.deleteStadiumParam(stadiumId);
    }
}
