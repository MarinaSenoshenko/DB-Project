package db.api.rest_controllers;

import db.entities.AthleteRank;
import db.api.service.AthleteRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addrank")
public class AddRankController {
    private final AthleteRankService athleteRankService;

    @Autowired
    public AddRankController(AthleteRankService athleteRankService) {
        this.athleteRankService = athleteRankService;
    }

    @PostMapping("")
    public AthleteRank addRank(@RequestParam("rank") String value) {
        return athleteRankService.addAthleteRank(value);
    }
}
