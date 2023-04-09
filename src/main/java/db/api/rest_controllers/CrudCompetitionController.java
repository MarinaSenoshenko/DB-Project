package db.api.rest_controllers;

import db.api.service.CompetitionService;
import db.entities.Competition;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@AllArgsConstructor
@RequestMapping("/competition")
public class CrudCompetitionController {
    private final CompetitionService competitionService;

    @PostMapping("")
    public Competition addCompetition(@RequestParam("title") String title, @RequestParam("period") String period,
                              @RequestParam("sponsor") Long sponsorId, @RequestParam("sport") Long sportId,
                                      @RequestParam("sportsfacility") Long sportsfacilityId) throws ParseException {
        return competitionService.addCompetition(title, period, sponsorId, sportId, sportsfacilityId);
    }

    @PutMapping("")
    public Competition updateCompetition(@RequestParam("id") Long id, @RequestParam("title") String title,
                                         @RequestParam("period") String period, @RequestParam("sponsor") Long sponsorId,
                                         @RequestParam("sport") Long sportId,
                                         @RequestParam("sportsfacility") Long sportsfacilityId) throws ParseException {
        return competitionService.updateCompetition(id, title, period, sponsorId, sportId, sportsfacilityId);
    }

    @DeleteMapping("")
    public Competition deleteCompetition(@RequestParam("competition") Long competitionId) {
        return competitionService.deleteCompetition(competitionId);
    }
}
