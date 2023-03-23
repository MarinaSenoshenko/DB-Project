package db.api.rest_controllers;

import db.entities.Athlete;
import db.entities.SportClub;
import db.repository.SportClubRepository;
import db.api.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addathlete")
public class AddAthleteController {
    private final AthleteService athleteService;
    private final SportClubRepository sportClubRepository;

    @Autowired
    public AddAthleteController(AthleteService athleteService, SportClubRepository sportClubRepository) {
        this.athleteService = athleteService;
        this.sportClubRepository = sportClubRepository;
    }

    @PostMapping("")
    public Athlete addAthlete(@RequestParam("firstName") String firstName, @RequestParam("patronymic") String patronymic,
                              @RequestParam("lastName") String lastName, @RequestParam("club") String title) {
        SportClub club = sportClubRepository.getSportClubByTitle(title);
        return athleteService.addAthlete(firstName, patronymic, lastName, club);
    }
}
