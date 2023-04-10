package db.controllers;

import db.repository.AthleteRankRepository;
import db.repository.AthleteRankingRepository;
import db.repository.AthleteRepository;
import db.repository.SportRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/athleteranking")
@AllArgsConstructor
public class AthleteRankingController {
    private AthleteRankingRepository athleteRankingRepository;
    private AthleteRepository athleteRepository;
    private AthleteRankRepository athleteRankRepository;
    private SportRepository sportRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("athleterankings", athleteRankingRepository.findAll());
        return "/pages/athleteranking";
    }

    @GetMapping("/add")
    public String getAthleteRanking(Model model) {
        model.addAttribute("athletes", athleteRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("athleterankings", athleteRankingRepository.findAll());
        model.addAttribute("athleteranks", athleteRankRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        return "/edit/post/add_ranking";
    }

    @GetMapping("/update")
    public String updateAthleteRanking(Model model) {
        model.addAttribute("athletes", athleteRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("athleterankings", athleteRankingRepository.findAll());
        model.addAttribute("athleteranks", athleteRankRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        return "/edit/update/update_ranking";
    }

    @GetMapping("/delete")
    public String deleteAthleteRanking(Model model) {
        model.addAttribute("athletes", athleteRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("sports", sportRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        return "/edit/delete/delete_ranking";
    }
}
