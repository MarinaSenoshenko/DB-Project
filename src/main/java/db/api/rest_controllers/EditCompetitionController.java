package db.api.rest_controllers;

import db.api.service.CompetitionService;
import db.entities.*;
import db.entities.models.surface.SportsFacility;
import db.repository.SponsorRepository;
import db.repository.SportRepository;
import db.repository.sports.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/competition")
public class EditCompetitionController {
    private final SportRepository sportRepository;
    private final SponsorRepository sponsorRepository;
    private final SportsFacilityRepository sportsFacilityRepository;
    private final CompetitionService competitionService;

    @Autowired
    public EditCompetitionController(SportRepository sportRepository, SponsorRepository sponsorRepository,
                                     SportsFacilityRepository sportsFacilityRepository, CompetitionService competitionService) {
        this.sportRepository = sportRepository;
        this.sponsorRepository = sponsorRepository;
        this.sportsFacilityRepository = sportsFacilityRepository;
        this.competitionService = competitionService;
    }

    @PostMapping("")
    public Competition addCompetition(@RequestParam("title") String title, @RequestParam("period") String period,
                              @RequestParam("sponsor") Long sponsorId, @RequestParam("sport") Long sportId,
                                      @RequestParam("sportsfacility") Long sportsfacilityId) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Sponsor sponsor = sponsorRepository.findById(sponsorId).orElseThrow();
        Sport sport = sportRepository.findById(sportId).orElseThrow();
        SportsFacility sportsFacility = sportsFacilityRepository.findById(sportsfacilityId).orElseThrow();
        return competitionService.addCompetition(title, dateFormat.parse(period), sponsor, sport, sportsFacility);
    }

    @DeleteMapping("")
    public Competition deleteCompetition(@RequestParam("competition") Long competitionId) {
        return competitionService.deleteCompetition(competitionId);
    }
}
