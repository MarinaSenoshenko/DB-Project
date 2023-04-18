package db.api.rest_controllers;

import db.api.service.TrainerService;
import db.entities.Trainer;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/trainer")
public class CrudTrainerController {
    private final TrainerService trainerService;

    @PostMapping("")
    public Trainer addTrainer(@RequestParam("firstName") String firstName, @RequestParam("patronymic") String patronymic,
                              @RequestParam("lastName") String lastName, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return null;
        }
        return trainerService.addTrainer(firstName, patronymic, lastName);
    }

    @PutMapping("")
    public Trainer updateTrainer(@RequestParam("id") Long id, @RequestParam("firstName") String firstName,
                                 @RequestParam("patronymic") String patronymic, @RequestParam("lastName") String lastName,
                                 Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return null;
        }
        return trainerService.updateTrainer(id, firstName, patronymic, lastName);
    }

    @DeleteMapping("")
    public Trainer deleteTrainer(@RequestParam("trainer") Long trainerId, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return null;
        }
        return trainerService.deleteTrainer(trainerId);
    }
}
