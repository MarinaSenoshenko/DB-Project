package db.controllers;

import db.entities.CompetitionPlayer;
import db.repository.AthleteRepository;
import db.repository.CompetitionPlayerRepository;
import db.repository.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

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

        List<CompetitionPlayer> allcompetitionplayers = competitionPlayerRepository.findAll();
        HashSet<String> uniqueLastNames = new HashSet<>();
        HashSet<String> uniqueFirstNames = new HashSet<>();
        HashSet<String> uniquePatronymics = new HashSet<>();
        HashSet<String> uniqueClubs = new HashSet<>();
        HashSet<String> uniqueCompetitions = new HashSet<>();
        HashSet<Integer> uniqueResults = new HashSet<>();

        for (CompetitionPlayer player : allcompetitionplayers) {
            uniqueLastNames.add(player.getCompetitionKey().getAthlete().getLastName());
            uniqueFirstNames.add(player.getCompetitionKey().getAthlete().getFirstName());
            uniquePatronymics.add(player.getCompetitionKey().getAthlete().getPatronymic());
            uniqueClubs.add(player.getCompetitionKey().getAthlete().getClub().getTitle());
            uniqueCompetitions.add(player.getCompetitionKey().getCompetition().getTitle());
            uniqueResults.add(Math.toIntExact(player.getResult()));
        }
        List<String> sortedUniqueLastNames = new ArrayList<>(uniqueLastNames);
        List<String> sortedUniqueFirstNames = new ArrayList<>(uniqueFirstNames);
        List<String> sortedUniquePatronymics = new ArrayList<>(uniquePatronymics);
        List<String> sortedUniqueClubs = new ArrayList<>(uniqueClubs);
        List<String> sortedUniqueCompetitions = new ArrayList<>(uniqueCompetitions);
        List<Integer> sortedUniqueResults = new ArrayList<>(uniqueResults);

        Collections.sort(sortedUniqueLastNames);
        Collections.sort(sortedUniqueFirstNames);
        Collections.sort(sortedUniquePatronymics);
        Collections.sort(sortedUniqueClubs);
        Collections.sort(sortedUniqueCompetitions);
        Collections.sort(sortedUniqueResults);

        model.addAttribute("uniqueLastNames", sortedUniqueLastNames);
        model.addAttribute("uniqueFirstNames", sortedUniqueFirstNames);
        model.addAttribute("uniquePatronymics", sortedUniquePatronymics);
        model.addAttribute("uniqueClubs", sortedUniqueClubs);
        model.addAttribute("uniqueCompetitions", sortedUniqueCompetitions);
        model.addAttribute("uniqueResults", sortedUniqueResults);
        return "/pages/competitionplayer";
    }

    @GetMapping(value = {"/filterby/{firstName}/{lastName}/{patronymic}/{club}/{title}/{wasawarding}/{result}"})
    public String filter(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("patronymic") String patronymic,
                         @PathVariable("club") String club, @PathVariable("title") String title, @PathVariable("wasawarding") String wasawarding,
                         @PathVariable("result") String result, Model model) {
        if (!Objects.equals(result, "null")) {
            model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findByResult(Long.valueOf(result)));
        }
        else if (!Objects.equals(club, "null")) {
            model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findByCompetitionKeyAthleteClubTitle(club));
        }
        else if (!Objects.equals(firstName, "null")) {
            model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findByCompetitionKeyAthleteFirstName(firstName));
        }
        else if (!Objects.equals(lastName, "null")) {
            model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findByCompetitionKeyAthleteLastName(lastName));
        }
        else if (!Objects.equals(patronymic, "null")) {
            model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findByCompetitionKeyAthletePatronymic(patronymic));
        }
        else if (!Objects.equals(title, "null")) {
            model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findByCompetitionKeyCompetitionTitle(title));
        }
        else if (!Objects.equals(result, "true")) {
            model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findCompetitionPlayerByWasAwardingTrue());
        }
        else if (!Objects.equals(result, "false")) {
            model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findCompetitionPlayerByWasAwardingFalse());
        }
        else {
            model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll());
        }
        return "/pages/competitionplayerfiltered";
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
