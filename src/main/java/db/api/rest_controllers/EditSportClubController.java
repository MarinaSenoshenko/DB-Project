package db.api.rest_controllers;

import db.api.service.SportClubService;
import db.entities.SportClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sportclub")
public class EditSportClubController {
    private final SportClubService sportClubService;

    @Autowired
    public EditSportClubController(SportClubService sportClubService) {
        this.sportClubService = sportClubService;
    }

    @PostMapping("")
    public SportClub addSportClub(@RequestParam("sport") String value) {
        return sportClubService.addSportClub(value);
    }

    @DeleteMapping("")
    public SportClub deleteSportClub(@RequestParam("sport") String value) {
        return sportClubService.deleteSportClub(value);
    }
}