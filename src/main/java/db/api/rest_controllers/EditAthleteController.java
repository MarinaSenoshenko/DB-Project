package db.api.rest_controllers;

import db.api.service.AthleteRankingService;
import db.entities.*;
import db.repository.AthleteRankRepository;
import db.repository.AthleteRepository;
import db.repository.SportClubRepository;
import db.api.service.AthleteService;
import db.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/athlete")
public class EditAthleteController {
    private final AthleteService athleteService;
    private final AthleteRankingService athleteRankingService;
    private final AthleteRepository athleteRepository;
    private final SportClubRepository sportClubRepository;
    private final SportRepository sportRepository;
    private final AthleteRankRepository athleteRankRepository;

    @Autowired
    public EditAthleteController(AthleteService athleteService, SportClubRepository sportClubRepository,
                                 AthleteRepository athleteRepository, SportRepository sportRepository,
                                 AthleteRankRepository athleteRankRepository,
                                 AthleteRankingService athleteRankingService) {
        this.athleteService = athleteService;
        this.athleteRankingService = athleteRankingService;
        this.sportClubRepository = sportClubRepository;
        this.athleteRepository = athleteRepository;
        this.sportRepository = sportRepository;
        this.athleteRankRepository = athleteRankRepository;
    }

    @PostMapping("")
    public Athlete addAthlete(@RequestParam("firstName") String firstName, @RequestParam("patronymic") String patronymic,
                              @RequestParam("lastName") String lastName, @RequestParam("club") String title) {
        SportClub club = sportClubRepository.getSportClubByTitle(title);
        return athleteService.addAthlete(firstName, patronymic, lastName, club);
    }

    @DeleteMapping("")
    public Athlete deleteAthlete(@RequestParam("athlete") Long athleteId) {
        return athleteService.deleteAthlete(athleteId);
    }

    @PostMapping("/addinfo")
    public AthleteRanking addAthleteRankAndSport(@RequestParam("rank") String rank, @RequestParam("sport") String sportValue) {
        Athlete athlete = athleteRepository.getAthleteByMaxId();
        Sport sport = sportRepository.findByValue(sportValue);
        AthleteRank athleteRank = athleteRankRepository.findByValue(rank);
        return athleteRankingService.addAthleteRanking(athlete, sport, athleteRank);
    }
}
