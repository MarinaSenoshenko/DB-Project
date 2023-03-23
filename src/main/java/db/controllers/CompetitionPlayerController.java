package db.controllers;

import db.repository.CompetitionPlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/competitionplayer")
@AllArgsConstructor
public class CompetitionPlayerController {
    private CompetitionPlayerRepository competitionPlayerRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll());
        return "competitionplayer";
    }
}
