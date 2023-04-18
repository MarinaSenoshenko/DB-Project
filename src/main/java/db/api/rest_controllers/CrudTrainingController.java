package db.api.rest_controllers;

import db.api.service.TrainingService;
import db.entities.Training;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/training")
public class CrudTrainingController {
    private final TrainingService trainingService;
    @PostMapping("")
    public Training addTraining(@RequestParam("id") Long id, @RequestParam("athlete") Long athleteId,
                                Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return null;
        }
        return trainingService.addTraining(id, athleteId);
    }

    @DeleteMapping("")
    public Training deleteTraining(@RequestParam("trainer") Long trainerId, @RequestParam("athlete") Long athleteId,
                                   Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return null;
        }
        return trainingService.deleteTraining(trainerId, athleteId);
    }
}
