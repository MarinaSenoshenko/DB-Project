package db.controllers;

import db.repository.TrainerLicenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/trainerlicense")
@AllArgsConstructor
public class TrainerLicenseController {
    private TrainerLicenseRepository trainerLicenseRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("trainerlicenses", trainerLicenseRepository.findAll());
        return "/pages/trainerlicense";
    }
}
