package db.controllers;

import db.repository.TrainingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/training")
@AllArgsConstructor
public class TrainingController {
    private TrainingRepository trainingRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("trainings", trainingRepository.findAll());
        return "training";
    }
}
