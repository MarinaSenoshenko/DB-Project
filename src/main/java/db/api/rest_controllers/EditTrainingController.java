package db.api.rest_controllers;

import db.api.service.TrainingService;
import db.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/training")
public class EditTrainingController {
    private final TrainingService trainingService;

    @Autowired
    public EditTrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping("")
    public Training addTraining(@RequestParam("trainer") Long trainerId, @RequestParam("athlete") Long athleteId) {
        return trainingService.addTraining(trainerId, athleteId);
    }

    @DeleteMapping("")
    public Training deleteTraining(@RequestParam("trainer") Long trainerId, @RequestParam("athlete") Long athleteId) {
        return trainingService.deleteTraining(trainerId, athleteId);
    }
}
