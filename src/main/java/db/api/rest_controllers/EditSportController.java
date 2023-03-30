package db.api.rest_controllers;

import db.api.service.SportService;
import db.entities.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sport")
public class EditSportController {
    private final SportService sportService;

    @Autowired
    public EditSportController(SportService sportService) {
        this.sportService = sportService;
    }

    @PostMapping("")
    public Sport addSport(@RequestParam("sport") String value) {
        return sportService.addSport(value);
    }

    @DeleteMapping("")
    public Sport deleteSport(@RequestParam("sport") Long sportId) {
        return sportService.deleteSport(sportId);
    }

    @PutMapping("")
    public Sport updateSport(@RequestParam("id") Long id, @RequestParam("sport") String sport) {
        return sportService.updateSport(id, sport);
    }
}