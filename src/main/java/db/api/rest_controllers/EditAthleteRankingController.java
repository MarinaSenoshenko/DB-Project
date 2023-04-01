package db.api.rest_controllers;

import db.api.service.AthleteRankingService;
import db.entities.*;
import db.entities.Sport;
import db.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/athleteranking")
public class EditAthleteRankingController {
    private final AthleteRankingService athleteRankingService;
    private final AthleteRepository athleteRepository;
    private final SportRepository sportRepository;
    private final AthleteRankRepository athleteRankRepository;

    @PostMapping("")
    public AthleteRanking addAthleteRankAndSport(@RequestParam("id") Long athleteId, @RequestParam("rank") String rank,
                                                 @RequestParam("sport") String value) {
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow();
        Sport sport = sportRepository.findByValue(value);
        AthleteRank athleteRank = athleteRankRepository.findByValue(rank);
        return athleteRankingService.addAthleteRanking(athlete, sport, athleteRank);
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
