package db.api.rest_controllers;

import db.api.service.CompetitionPlayerService;
import db.entities.Competition;
import db.entities.*;
import db.repository.AthleteRepository;
import db.repository.CompetitionRepository;
import db.entities.CompetitionPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/competitionplayer")
public class EditCompetitionPlayerController {
    private final AthleteRepository athleteRepository;
    private final CompetitionRepository competitionRepository;
    private final CompetitionPlayerService competitionPlayerService;

    @Autowired
    public EditCompetitionPlayerController(AthleteRepository athleteRepository,
                                           CompetitionRepository competitionRepository,
                                           CompetitionPlayerService competitionPlayerService) {
        this.athleteRepository = athleteRepository;
        this.competitionRepository = competitionRepository;
        this.competitionPlayerService = competitionPlayerService;
    }

    @PostMapping("")
    public CompetitionPlayer addCompetitionPlayer(@RequestParam("athlete") Long athleteId,
                                                  @RequestParam("competition") Long competitionId,
                                                  @RequestParam("wasawarding") boolean wasawarding,
                                                  @RequestParam("result") Long result) {
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow();
        Competition competition = competitionRepository.findById(competitionId).orElseThrow();
        return competitionPlayerService.addCompetitionPlayer(athlete, competition, wasawarding, result);
    }

    @DeleteMapping("")
    public CompetitionPlayer deleteCompetitionPlayer(@RequestParam("athlete") Long athleteId,
                                                     @RequestParam("competition") Long competitionId) {
        return competitionPlayerService.deleteCompetitionPlayer(athleteId, competitionId);
    }
}
