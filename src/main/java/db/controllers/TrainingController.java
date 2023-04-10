package db.controllers;

import db.repository.AthleteRepository;
import db.repository.TrainerLicenseRepository;
import db.repository.TrainerRepository;
import db.repository.TrainingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/training")
@AllArgsConstructor
public class TrainingController {
    private TrainingRepository trainingRepository;
    private TrainerLicenseRepository trainerLicenseRepository;
    private TrainerRepository trainerRepository;
    private AthleteRepository athleteRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("trainings", trainingRepository.findAll());
        return "/pages/training";
    }

    @GetMapping("/add")
    public String addTraining(Model model) {
        model.addAttribute("licenses", trainerLicenseRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("trainers", trainerRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("athletes", athleteRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        return "/edit/post/add_training";
    }

    @GetMapping("/delete")
    public String deleteTraining(Model model) {
        model.addAttribute("trainings", trainingRepository.findAll());
        model.addAttribute("trainers", trainerRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("athletes", athleteRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        return "/edit/delete/delete_training";
    }
}
