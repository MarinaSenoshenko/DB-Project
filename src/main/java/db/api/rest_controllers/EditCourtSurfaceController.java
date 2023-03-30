package db.api.rest_controllers;

import db.api.service.CourtSurfaceService;
import db.entities.CourtSurface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courtsurface")
public class EditCourtSurfaceController {
    private final CourtSurfaceService courtSurfaceService;

    @Autowired
    public EditCourtSurfaceController(CourtSurfaceService courtSurfaceService) {
        this.courtSurfaceService = courtSurfaceService;
    }

    @DeleteMapping("")
    public CourtSurface deleteCourtSurface(@RequestParam("surface") Long surfaceId) {
        return courtSurfaceService.deleteCourtSurface(surfaceId);
    }

    @PostMapping("")
    public CourtSurface addCourtSurface(@RequestParam("surface") String value) {
        return courtSurfaceService.addCourtSurface(value);
    }

    @PutMapping("")
    public CourtSurface updateCourtSurface(@RequestParam("id") Long id, @RequestParam("surface") String value) {
        return courtSurfaceService.updateCourtSurface(id, value);
    }
}
