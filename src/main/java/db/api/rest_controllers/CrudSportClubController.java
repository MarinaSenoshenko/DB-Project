package db.api.rest_controllers;

import db.api.service.SportClubService;
import db.entities.SportClub;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/sportclub")
public class CrudSportClubController {
    private final SportClubService sportClubService;

    @PostMapping("")
    public SportClub addSportClub(@RequestParam("sport") String value, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportClubService.addSportClub(value);
    }

    @DeleteMapping("")
    public SportClub deleteSportClub(@RequestParam("sport") Long sportClubId, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportClubService.deleteSportClub(sportClubId);
    }

    @PutMapping("")
    public SportClub updateSportClub(@RequestParam("id") Long id, @RequestParam("sport") String value,
                                     Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return sportClubService.updateSportClub(id, value);
    }
}
