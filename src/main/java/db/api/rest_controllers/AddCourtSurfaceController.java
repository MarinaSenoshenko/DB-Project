package db.api.rest_controllers;

import db.api.service.CourtSurfaceService;
import db.api.service.SportService;
import db.entities.CourtSurface;
import db.entities.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addcourtsurface")
public class AddCourtSurfaceController {
    private final CourtSurfaceService courtSurfaceService;

    @Autowired
    public AddCourtSurfaceController(CourtSurfaceService courtSurfaceService) {
        this.courtSurfaceService = courtSurfaceService;
    }

    @PostMapping("")
    public CourtSurface addCourtSurface(@RequestParam("surface") String value) {
        return courtSurfaceService.addCourtSurface(value);
    }
}
