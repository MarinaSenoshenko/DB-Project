package db.api.rest_controllers;

import db.api.service.SportsFacilityService;
import db.entities.models.surface.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/sportsfacility")
public class CrudSportsFacilityController {
    private final SportsFacilityService sportsFacilityService;

    @PostMapping("")
    public SportsFacility addSportsFacility(@RequestParam("address") String address,
                                            @RequestParam("type") String value, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.addSportsFacility(address, value);
    }

    @PutMapping("")
    public SportsFacility updateSportsFacility(@RequestParam("type") String type, @RequestParam("id") Long id,
                                               @RequestParam("address") String address, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.updateSportsFacility(id, type, address);
    }

    @DeleteMapping("")
    public SportsFacility deleteSportsFacility(@RequestParam("sportsfacility") Long facilityId, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.deleteSportsFacility(facilityId);
    }

    @PostMapping("/arena")
    public Arena addArena(@RequestParam("param") String param, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.addArenaParam(param);
    }

    @PutMapping("/arena")
    public Arena updateArena(@RequestParam("id") Long id, @RequestParam("param") String param,
                             Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.updateArenaParam(id, param);
    }

    @DeleteMapping("/arena")
    public Arena deleteArena(@RequestParam("param") Long arenaId, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.deleteArenaParam(arenaId);
    }

    @PostMapping("/court")
    public Court addCourt(@RequestParam("param") String param, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.addCourtParam(param);
    }

    @PutMapping("/court")
    public Court updateCourt(@RequestParam("id") Long id, @RequestParam("param") String param,
                             Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.updateCourtParam(id, param);
    }

    @DeleteMapping("/court")
    public Court deleteCourt(@RequestParam("param") Long courtId, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.deleteCourtParam(courtId);
    }

    @PostMapping("/gym")
    public Gym addGym(@RequestParam("param") String param, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.addGymParam(param);
    }

    @PutMapping("/gym")
    public Gym updateGym(@RequestParam("id") Long id, @RequestParam("param") String param,
                         Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.updateGymParam(id, param);
    }

    @DeleteMapping("/gym")
    public Gym deleteGym(@RequestParam("param") Long gymId, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.deleteGymParam(gymId);
    }

    @PostMapping("/stadium")
    public Stadium addStadium(@RequestParam("param") String param, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.addStadiumParam(param);
    }

    @PutMapping("/stadium")
    public Stadium updateStadium(@RequestParam("id") Long id, @RequestParam("param") String param,
                                 Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.updateStadiumParam(id, param);
    }

    @DeleteMapping("/stadium")
    public Stadium deleteStadium(@RequestParam("param") Long stadiumId, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportsFacilityService.deleteStadiumParam(stadiumId);
    }
}
