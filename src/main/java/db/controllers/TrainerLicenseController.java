package db.controllers;

import db.repository.SportRepository;
import db.repository.TrainerLicenseRepository;
import db.repository.TrainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/trainerlicense")
@AllArgsConstructor
public class TrainerLicenseController {
    private TrainerLicenseRepository trainerLicenseRepository;
    private TrainerRepository trainerRepository;
    private SportRepository sportRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("trainerlicenses", trainerLicenseRepository.findAll());
        return "/pages/trainerlicense";
    }

    @GetMapping("/add")
    public String addTrainerLicense(Model model) {
        model.addAttribute("trainers", trainerRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("sports", sportRepository.findAll());
        return "/edit/post/add_trainer_license";
    }

    @GetMapping("/delete")
    public String deleteTrainerLicense(Model model) {
        model.addAttribute("trainerlicenses", trainerLicenseRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        return "/edit/delete/delete_trainer_license";
    }
}
