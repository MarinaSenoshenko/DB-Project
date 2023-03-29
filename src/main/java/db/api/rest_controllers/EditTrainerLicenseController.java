package db.api.rest_controllers;

import db.api.service.TrainerLicenseService;
import db.entities.TrainerLicense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainerlicense")
public class EditTrainerLicenseController {
    private final TrainerLicenseService trainerLicenseService;

    @Autowired
    public EditTrainerLicenseController(TrainerLicenseService trainerLicenseService) {
        this.trainerLicenseService = trainerLicenseService;
    }

    @PostMapping("")
    public TrainerLicense addTrainerLicense(@RequestParam("trainer") Long trainerId, @RequestParam("sport") String value) {
        return trainerLicenseService.addTrainerLicense(trainerId, value);
    }

    @DeleteMapping("")
    public TrainerLicense deleteTrainerLicense(@RequestParam("trainerlicense") Long trainerLicenseId) {
        return trainerLicenseService.deleteTrainerLicense(trainerLicenseId);
    }
}