package db.api.rest_controllers;

import db.api.service.TrainingService;
import db.entities.Training;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/training")
public class EditTrainingController {
    private final TrainingService trainingService;
    @PostMapping("")
    public Training addTraining(@RequestParam("trainer") Long trainerId, @RequestParam("athlete") Long athleteId) {
        return trainingService.addTraining(trainerId, athleteId);
    }

    @DeleteMapping("")
    public Training deleteTraining(@RequestParam("trainer") Long trainerId, @RequestParam("athlete") Long athleteId) {
        return trainingService.deleteTraining(trainerId, athleteId);
    }
}
