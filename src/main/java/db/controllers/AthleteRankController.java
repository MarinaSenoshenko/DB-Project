package db.controllers;

import db.repository.AthleteRankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/athleterank")
@AllArgsConstructor
public class AthleteRankController {
    private AthleteRankRepository athleteRankRepository;
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("athleteranks", athleteRankRepository.findAll());
        return "athleterank";
    }
    @GetMapping("/add")
    public String addRank(Model model) {
        model.addAttribute("athleteranks", athleteRankRepository.findAll());
        return "add_rank";
    }
}
