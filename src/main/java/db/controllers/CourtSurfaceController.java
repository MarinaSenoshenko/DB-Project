package db.controllers;

import db.entities.CourtSurface;
import db.repository.CourtSurfaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/main/courtsurface")
public class CourtSurfaceController {
    private CourtSurfaceRepository courtSurfaceRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("courtsurfaces", courtSurfaceRepository.findAll());
        return "courtsurface";
    }
}
