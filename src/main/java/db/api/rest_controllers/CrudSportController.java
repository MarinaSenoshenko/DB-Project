package db.api.rest_controllers;

import db.api.service.SportService;
import db.entities.Sport;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/sport")
public class CrudSportController {
    private final SportService sportService;

    @PostMapping("")
    public Sport addSport(@RequestParam("sport") String value, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportService.addSport(value);
    }

    @DeleteMapping("")
    public Sport deleteSport(@RequestParam("sport") Long sportId, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportService.deleteSport(sportId);
    }

    @PutMapping("")
    public Sport updateSport(@RequestParam("id") Long id,
                             @RequestParam("sport") String sport, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportService.updateSport(id, sport);
    }
}
