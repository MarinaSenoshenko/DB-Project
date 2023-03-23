package db.api.rest_controllers;

import db.api.service.SportClubService;
import db.entities.SportClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addsportclub")
public class AddSportClubController {
    private final SportClubService sportClubService;

    @Autowired
    public AddSportClubController(SportClubService sportClubService) {
        this.sportClubService = sportClubService;
    }

    @PostMapping("")
    public SportClub addSportClub(@RequestParam("sport") String value) {
        return sportClubService.addSportClub(value);
    }
}