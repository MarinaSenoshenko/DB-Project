package db.api.rest_controllers;

import db.api.service.CourtSurfaceService;
import db.entities.CourtSurface;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/courtsurface")
public class CrudCourtSurfaceController {
    private final CourtSurfaceService courtSurfaceService;

    @DeleteMapping("")
    public CourtSurface deleteCourtSurface(@RequestParam("surface") Long surfaceId, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return null;
        }
        return courtSurfaceService.deleteCourtSurface(surfaceId);
    }

    @PostMapping("")
    public CourtSurface addCourtSurface(@RequestParam("surface") String value, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return null;
        }
        return courtSurfaceService.addCourtSurface(value);
    }

    @PutMapping("")
    public CourtSurface updateCourtSurface(@RequestParam("id") Long id, @RequestParam("surface") String value,
                                           Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return null;
        }
        return courtSurfaceService.updateCourtSurface(id, value);
    }
}
