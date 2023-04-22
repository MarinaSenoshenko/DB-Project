package db.api.rest_controllers;

import db.api.service.AthleteService;
import db.entities.Athlete;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/athlete")
public class CrudAthleteController {
    private final AthleteService athleteService;

    @PostMapping("")
    public Athlete addAthlete(@RequestParam("firstName") String firstName, @RequestParam("patronymic") String patronymic,
                              @RequestParam("lastName") String lastName, @RequestParam("club") String title, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return athleteService.addAthlete(firstName, patronymic, lastName, title);
    }

    @PutMapping("")
    public Athlete updateAthlete(@RequestParam("id") Long id, @RequestParam("firstName") String firstName,
                                 @RequestParam("patronymic") String patronymic, @RequestParam("lastName") String lastName,
                                 @RequestParam("club") String title, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return athleteService.updateAthlete(id, firstName, patronymic, lastName, title);
    }

    @DeleteMapping("")
    public Athlete deleteAthlete(@RequestParam("athlete") Long athleteId, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return athleteService.deleteAthlete(athleteId);
    }
}
