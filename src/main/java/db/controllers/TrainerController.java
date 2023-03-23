package db.controllers;

import db.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/trainer")
@AllArgsConstructor
public class TrainerController {
    private TrainerRepository trainerRepository;
    private SportRepository sportRepository;
    private AthleteRepository athleteRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("trainers", trainerRepository.findAll());
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        return "trainer";
    }

    @GetMapping("/add")
    public String getAddGrades(Model model) {
        model.addAttribute("trainers", athleteRepository.findAll());
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        return "add_trainer";
    }

    @GetMapping("/byathlete/{athleteid}")
    public String getTrainerByAthlete(@PathVariable("athleteid") Long athleteId, Model model) {
        model.addAttribute("trainers", trainerRepository.getTrainerByAthlete(athleteId));
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        return "trainer";
    }

    @GetMapping("/bysport/{sportvalue}")
    public String getTrainersBySport(@PathVariable("sportvalue") String sportValue, Model model) {
        model.addAttribute("trainers", trainerRepository.getTrainersBySport(sportRepository
                .findByValue(sportValue).getId()));
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        return "trainer";
    }
}
