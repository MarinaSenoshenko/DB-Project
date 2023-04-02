package db.api.rest_controllers;

import db.api.service.RegistrationService;
import db.entities.users.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/password")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/athlete")
    AthletePassword addAthlete(@RequestParam("id") Long id, @RequestParam("firstName") String firstName,
                               @RequestParam("patronymic") String patronymic, @RequestParam("lastName") String lastName,
                               @RequestParam("login") String login, @RequestParam("password") String password) {
        return registrationService.addAthlete(id, firstName, lastName, patronymic, login, password);
    }

    @PostMapping("/trainer")
    TrainerPassword addTrainer(@RequestParam("id") Long id, @RequestParam("firstName") String firstName,
                               @RequestParam("patronymic") String patronymic, @RequestParam("lastName") String lastName,
                               @RequestParam("login") String login, @RequestParam("password") String password) {
        return registrationService.addTrainer(id, firstName, lastName, patronymic, login, password);
    }
}
