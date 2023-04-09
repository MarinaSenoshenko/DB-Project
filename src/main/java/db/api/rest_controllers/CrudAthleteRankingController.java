package db.api.rest_controllers;

import db.api.service.AthleteRankingService;
import db.entities.AthleteRanking;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/athleteranking")
public class CrudAthleteRankingController {
    private final AthleteRankingService athleteRankingService;

    @PostMapping("")
    public AthleteRanking addAthleteRankAndSport(@RequestParam("id") Long athleteId, @RequestParam("rank") String rank,
                                                 @RequestParam("sport") String value) {
        return athleteRankingService.addAthleteRanking(athleteId, rank, value);
    }

    @PutMapping("")
    public AthleteRanking updateAthleteRankAndSport(@RequestParam("id") Long athleteId, @RequestParam("rank") String rank,
                                                 @RequestParam("sport") Long sportId) {
        return athleteRankingService.updateAthleteRanking(athleteId, sportId, rank);
    }

    @DeleteMapping("")
    public AthleteRanking deleteAthleteRankAndSport(@RequestParam("athlete") Long athleteId,
                                                 @RequestParam("sport") Long sportId) {
        return athleteRankingService.deleteAthleteRanking(athleteId, sportId);
    }
}
