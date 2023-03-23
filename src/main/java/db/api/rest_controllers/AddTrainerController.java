package db.api.rest_controllers;

import db.api.service.TrainerService;
import db.entities.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addtrainer")
public class AddTrainerController {
    private final TrainerService trainerService;

    @Autowired
    public AddTrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("")
    public Trainer addAthlete(@RequestParam("firstName") String firstName, @RequestParam("patronymic") String patronymic,
                              @RequestParam("lastName") String lastName) {
        return trainerService.addTrainer(firstName, patronymic, lastName);
    }
}