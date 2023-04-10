package db.controllers;

import db.repository.AthleteRepository;
import db.repository.CompetitionPlayerRepository;
import db.repository.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
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
        model.addAttribute("athletes", athleteRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("competitions", competitionRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        return "/edit/post/add_competition_player";
    }

    @GetMapping("/update")
    public String updateCompetitionPlayer(Model model) {
        model.addAttribute("athletes", athleteRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("competitions", competitionRepository.findAll(Sort.by(Sort.Direction.ASC, "id")
        ));
        return "/edit/update/update_competition_player";
    }

    @GetMapping("/delete")
    public String deleteCompetitionPlayer(Model model) {
        model.addAttribute("athletes", athleteRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("competitions", competitionRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        return "/edit/delete/delete_competition_player";
    }

    @GetMapping("/result/ascending")
    public String orderByResultAscending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll(
                Sort.by(Sort.Direction.ASC, "result")
        ));
        return "/pages/competitionplayer";
    }

    @GetMapping("/result/descending")
    public String orderByResultDescending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll(
                Sort.by(Sort.Direction.DESC, "result")
        ));
        return "/pages/competitionplayer";
    }

    @GetMapping("/wasawarding/ascending")
    public String orderByWasAwardingAscending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll(
                Sort.by(Sort.Direction.ASC, "wasAwarding")
        ));
        return "/pages/competitionplayer";
    }

    @GetMapping("/wasawarding/descending")
    public String orderByWasAwardingDescending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll(
                Sort.by(Sort.Direction.DESC, "wasAwarding")
        ));
        return "/pages/competitionplayer";
    }

    @GetMapping("/firstName/ascending")
    public String orderByFirstNameAscending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByFirstNameOrderByAscending());
        return "/pages/competitionplayer";
    }

    @GetMapping("/firstName/descending")
    public String orderByFirstNameDescending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByFirstNameOrderByDescending());
        return "/pages/competitionplayer";
    }

    @GetMapping("/lastName/ascending")
    public String orderByLastNameAscending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByLastNameOrderByAscending());
        return "/pages/competitionplayer";
    }

    @GetMapping("/lastName/descending")
    public String orderByLastNameDescending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByLastNameOrderByDescending());
        return "/pages/competitionplayer";
    }

    @GetMapping("/patronymic/ascending")
    public String orderByPatronymicAscending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByPatronymicOrderByAscending());
        return "/pages/competitionplayer";
    }

    @GetMapping("/patronymic/descending")
    public String orderByPatronymicDescending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByPatronymicOrderByDescending());
        return "/pages/competitionplayer";
    }

    @GetMapping("/competitiontitle/ascending")
    public String orderByCompetitionAscending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByCompetitionOrderByAscending());
        return "/pages/competitionplayer";
    }

    @GetMapping("/competitiontitle/descending")
    public String orderByCompetitionDescending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByCompetitionOrderByDescending());
        return "/pages/competitionplayer";
    }

    @GetMapping("/clubtitle/ascending")
    public String orderByClubAscending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByClubOrderByAscending());
        return "/pages/competitionplayer";
    }

    @GetMapping("/clubtitle/descending")
    public String orderByClubDescending(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByClubOrderByDescending());
        return "/pages/competitionplayer";
    }
}
