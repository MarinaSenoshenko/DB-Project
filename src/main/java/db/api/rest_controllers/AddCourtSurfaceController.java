package db.api.rest_controllers;

import db.api.service.CourtSurfaceService;
import db.entities.CourtSurface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addcourtsurface")
public class AddCourtSurfaceController {
    private final CourtSurfaceService courtSurfaceService;

    @Autowired
    public AddCourtSurfaceController(CourtSurfaceService courtSurfaceService) {
        this.courtSurfaceService = courtSurfaceService;
    }

    @DeleteMapping("/delete")
    public CourtSurface deleteCourtSurface(@RequestParam("surface") String value) {
        return courtSurfaceService.deleteCourtSurface(value);
    }

    @PostMapping("")
    public CourtSurface addCourtSurface(@RequestParam("surface") String value) {
        return courtSurfaceService.addCourtSurface(value);
    }
}
