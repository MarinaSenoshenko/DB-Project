package db.controllers;

import db.repository.AthleteRankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/athleteranking")
@AllArgsConstructor
public class AthleteRankingController {
    private AthleteRankingRepository athleteRankingRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("athleterankings", athleteRankingRepository.findAll());
        return "/pages/athleteranking";
    }
}
