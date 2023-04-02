package db.api.rest_controllers;

import db.api.service.CompetitionPlayerService;
import db.entities.Athlete;
import db.entities.Competition;
import db.entities.CompetitionPlayer;
import db.repository.AthleteRepository;
import db.repository.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/competitionplayer")
public class EditCompetitionPlayerController {
    private final AthleteRepository athleteRepository;
    private final CompetitionRepository competitionRepository;
    private final CompetitionPlayerService competitionPlayerService;

    @PostMapping("")
    public CompetitionPlayer addCompetitionPlayer(@RequestParam("athlete") Long athleteId,
                                                  @RequestParam("competition") Long competitionId,
                                                  @RequestParam("wasawarding") boolean wasawarding,
                                                  @RequestParam("result") Long result) {
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow();
        Competition competition = competitionRepository.findById(competitionId).orElseThrow();
        return competitionPlayerService.addCompetitionPlayer(athlete, competition, wasawarding, result);
    }

    @PutMapping("")
    public CompetitionPlayer updateCompetitionPlayer(@RequestParam("athlete") Long athleteId,
                                                  @RequestParam("competition") Long competitionId,
                                                  @RequestParam("wasawarding") boolean wasawarding,
                                                  @RequestParam("result") Long result) {
        return competitionPlayerService.updateCompetitionPlayer(athleteId, competitionId, wasawarding, result);
    }

    @DeleteMapping("")
    public CompetitionPlayer deleteCompetitionPlayer(@RequestParam("athlete") Long athleteId,
                                                     @RequestParam("competition") Long competitionId) {
        return competitionPlayerService.deleteCompetitionPlayer(athleteId, competitionId);
    }
}
