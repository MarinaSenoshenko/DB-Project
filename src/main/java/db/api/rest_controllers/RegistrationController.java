package db.api.rest_controllers;

import db.api.service.RegistrationService;
import db.entities.users.AthletePassword;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/athletepassword")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("")
    AthletePassword addAthlete(@RequestParam("firstName") String firstName, @RequestParam("patronymic") String patronymic,
                               @RequestParam("lastName") String lastName, @RequestParam("login") String login,
                               @RequestParam("password") String password) {
        return registrationService.addAthlete(firstName, lastName, patronymic, login, password);
    }
}
