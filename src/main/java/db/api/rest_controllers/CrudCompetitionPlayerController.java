package db.api.rest_controllers;

import db.api.service.CompetitionPlayerService;
import db.entities.CompetitionPlayer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/competitionplayer")
public class CrudCompetitionPlayerController {
    private final CompetitionPlayerService competitionPlayerService;

    @PostMapping("")
    public CompetitionPlayer addCompetitionPlayer(@RequestParam("athlete") Long athleteId,
                                                  @RequestParam("competition") Long competitionId,
                                                  @RequestParam("wasawarding") boolean wasawarding,
                                                  @RequestParam("result") Long result) {
        return competitionPlayerService.addCompetitionPlayer(athleteId, competitionId, wasawarding, result);
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
