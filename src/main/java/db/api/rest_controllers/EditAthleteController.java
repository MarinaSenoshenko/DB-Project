package db.api.rest_controllers;

import db.entities.*;
import db.repository.SportClubRepository;
import db.api.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/athlete")
public class EditAthleteController {
    private final AthleteService athleteService;
    private final SportClubRepository sportClubRepository;

    @Autowired
    public EditAthleteController(AthleteService athleteService, SportClubRepository sportClubRepository) {
        this.athleteService = athleteService;
        this.sportClubRepository = sportClubRepository;
    }

    @PostMapping("")
    public Athlete addAthlete(@RequestParam("firstName") String firstName, @RequestParam("patronymic") String patronymic,
                              @RequestParam("lastName") String lastName, @RequestParam("club") String title) {
        SportClub club = sportClubRepository.getSportClubByTitle(title);
        return athleteService.addAthlete(firstName, patronymic, lastName, club);
    }

    @PutMapping("")
    public Athlete updateAthlete(@RequestParam("id") Long id, @RequestParam("firstName") String firstName,
                                 @RequestParam("patronymic") String patronymic, @RequestParam("lastName") String lastName,
                                 @RequestParam("club") String title) {
        SportClub club = sportClubRepository.getSportClubByTitle(title);
        return athleteService.updateAthlete(id, firstName, patronymic, lastName, club);
    }

    @DeleteMapping("")
    public Athlete deleteAthlete(@RequestParam("athlete") Long athleteId) {
        return athleteService.deleteAthlete(athleteId);
    }
}
