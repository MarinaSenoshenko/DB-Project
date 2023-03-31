package db.controllers;

import db.repository.AthleteRepository;
import db.repository.CompetitionPlayerRepository;
import db.repository.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/competitionplayer")
@AllArgsConstructor
public class CompetitionPlayerController {
    private CompetitionPlayerRepository competitionPlayerRepository;
    private AthleteRepository athleteRepository;
    private CompetitionRepository competitionRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll());
        return "/pages/competitionplayer";
    }

    @GetMapping("/add")
    public String addCompetitionPlayer(Model model) {
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("competitions", competitionRepository.findAll());
        return "/post/add_competition_player";
    }

    @GetMapping("/update")
    public String updateCompetitionPlayer(Model model) {
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("competitions", competitionRepository.findAll());
        return "/update/update_competition_player";
    }

    @GetMapping("/delete")
    public String deleteCompetitionPlayer(Model model) {
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("competitions", competitionRepository.findAll());
        return "/delete/delete_competition_player";
    }
}
