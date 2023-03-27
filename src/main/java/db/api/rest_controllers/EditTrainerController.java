package db.api.rest_controllers;

import db.api.service.TrainerService;
import db.entities.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addtrainer")
public class EditTrainerController {
    private final TrainerService trainerService;

    @Autowired
    public EditTrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("")
    public Trainer addTrainer(@RequestParam("firstName") String firstName, @RequestParam("patronymic") String patronymic,
                              @RequestParam("lastName") String lastName) {
        return trainerService.addTrainer(firstName, patronymic, lastName);
    }

    @DeleteMapping("")
    public Trainer deleteTrainer(@RequestParam("trainer") Long trainerId){
        return trainerService.deleteTrainer(trainerId);
    }
}