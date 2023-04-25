package db.controllers;

import db.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
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
        model.addAttribute("athletes", athleteRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("sports", sportRepository.findAll());
        return "/pages/trainer";
    }

    @GetMapping("/add")
    public String addTrainer(Model model) {
        model.addAttribute("trainers", athleteRepository.findAll());
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        return "/edit/post/add_trainer";
    }

    @GetMapping("/update")
    public String updateTrainer(Model model) {
        model.addAttribute("trainers", trainerRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        return "/edit/update/update_trainer";
    }

    @GetMapping("/delete")
    public String deleteTrainer(Model model) {
        model.addAttribute("trainers", trainerRepository.getNotUsedInOtherTablesTrainersId());
        return "/edit/delete/delete_trainer";
    }

    @GetMapping("/byathlete/{athleteid}")
    public String getTrainerByAthlete(@PathVariable("athleteid") Long athleteId, Model model, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            model.addAttribute("trainers", trainerRepository.getTrainerByAthlete(athleteId));
            model.addAttribute("athletes", athleteRepository.findAll());
            model.addAttribute("sports", sportRepository.findAll());
            return "/pages/trainer";
        }
        throw new AccessDeniedException("Access denied");

    }

    @GetMapping("/bysport/{sportvalue}")
    public String getTrainersBySport(@PathVariable("sportvalue") String sportValue, Model model, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {
            throw new AccessDeniedException("Access denied");
        }
        model.addAttribute("trainers", trainerRepository.getTrainersBySport
                (sportRepository.findByValue(sportValue).getId()));
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        return "/pages/trainer";
    }
}
