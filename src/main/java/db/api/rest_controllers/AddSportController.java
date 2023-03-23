package db.api.rest_controllers;

import db.api.service.SportService;
import db.entities.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addsport")
public class AddSportController {
    private final SportService sportService;

    @Autowired
    public AddSportController(SportService sportService) {
        this.sportService = sportService;
    }

    @PostMapping("")
    public Sport addRank(@RequestParam("sport") String value) {
        return sportService.addSport(value);
    }
}