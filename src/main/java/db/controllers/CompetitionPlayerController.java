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
    private List<String> sortedUniqueLastNames, sortedUniqueFirstNames, sortedUniquePatronymics,
            sortedUniqueClubs, sortedUniqueCompetitions;
    private List<Integer> sortedUniqueResults;

    @GetMapping("")
    public String getAll(Model model) {
        HashSet<String> uniqueLastNames = new HashSet<>();
        HashSet<String> uniqueFirstNames = new HashSet<>();
        HashSet<String> uniquePatronymics = new HashSet<>();
        HashSet<String> uniqueClubs = new HashSet<>();
        HashSet<String> uniqueCompetitions = new HashSet<>();
        HashSet<Integer> uniqueResults = new HashSet<>();

        for (CompetitionPlayer player : competitionPlayerRepository.findAll()) {
            uniqueLastNames.add(player.getCompetitionKey().getAthlete().getLastName());
            uniqueFirstNames.add(player.getCompetitionKey().getAthlete().getFirstName());
            uniquePatronymics.add(player.getCompetitionKey().getAthlete().getPatronymic());
            uniqueClubs.add(player.getCompetitionKey().getAthlete().getClub().getTitle());
            uniqueCompetitions.add(player.getCompetitionKey().getCompetition().getTitle());
            uniqueResults.add(Math.toIntExact(player.getResult()));
        }
        sortedUniqueLastNames = new ArrayList<>(uniqueLastNames);
        sortedUniqueFirstNames = new ArrayList<>(uniqueFirstNames);
        sortedUniquePatronymics = new ArrayList<>(uniquePatronymics);
        sortedUniqueClubs = new ArrayList<>(uniqueClubs);
        sortedUniqueCompetitions = new ArrayList<>(uniqueCompetitions);
        sortedUniqueResults = new ArrayList<>(uniqueResults);

        Collections.sort(sortedUniqueResults);
        List.of(sortedUniqueFirstNames, sortedUniqueLastNames,sortedUniquePatronymics,
                sortedUniqueClubs, sortedUniqueCompetitions).forEach(Collections::sort);

        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll());
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping(value = {"/filterby/{firstName}/{lastName}/{patronymic}/{club}/{title}/{wasawarding}/{result}"})
    public String filter(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
                         @PathVariable("patronymic") String patronymic, @PathVariable("club") String club,
                         @PathVariable("title") String title, @PathVariable("wasawarding") String wasawarding,
                         @PathVariable("result") String result, Model model) {
        long res = (Objects.equals(result, "all")) ? -1L : Long.parseLong(result);

        if (Objects.equals(wasawarding, "all")) {
            model.addAttribute("allcompetitionplayers", competitionPlayerRepository
                    .getFilteredWithout(res, club, firstName, lastName, patronymic, title));
        } else {
            boolean wasAwarding = Objects.equals(wasawarding, "true");
            model.addAttribute("allcompetitionplayers", competitionPlayerRepository
                    .getFiltered(res, club, firstName, lastName, patronymic, title, wasAwarding));
        }
        addAttributes(model);
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
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll(
                Sort.by(Sort.Direction.ASC, "result")
        ));
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/result/descending")
    public String orderByResultDescending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll(
                Sort.by(Sort.Direction.DESC, "result")
        ));
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/wasawarding/ascending")
    public String orderByWasAwardingAscending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll(
                Sort.by(Sort.Direction.ASC, "wasAwarding")
        ));
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/wasawarding/descending")
    public String orderByWasAwardingDescending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.findAll(
                Sort.by(Sort.Direction.DESC, "wasAwarding")
        ));
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/firstName/ascending")
    public String orderByFirstNameAscending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByFirstNameOrderByAscending());
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/firstName/descending")
    public String orderByFirstNameDescending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByFirstNameOrderByDescending());
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/lastName/ascending")
    public String orderByLastNameAscending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByLastNameOrderByAscending());
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/lastName/descending")
    public String orderByLastNameDescending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByLastNameOrderByDescending());
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/patronymic/ascending")
    public String orderByPatronymicAscending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByPatronymicOrderByAscending());
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/patronymic/descending")
    public String orderByPatronymicDescending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByPatronymicOrderByDescending());
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/competitiontitle/ascending")
    public String orderByCompetitionAscending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByCompetitionOrderByAscending());
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/competitiontitle/descending")
    public String orderByCompetitionDescending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByCompetitionOrderByDescending());
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/clubtitle/ascending")
    public String orderByClubAscending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByClubOrderByAscending());
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    @GetMapping("/clubtitle/descending")
    public String orderByClubDescending(Model model) {
        model.addAttribute("allcompetitionplayers", competitionPlayerRepository.getByClubOrderByDescending());
        addAttributes(model);
        return "/pages/competitionplayer";
    }

    private void addAttributes(Model model) {
        model.addAttribute("competitionplayers", competitionPlayerRepository.findAll());
        model.addAttribute("uniqueLastNames", sortedUniqueLastNames);
        model.addAttribute("uniqueFirstNames", sortedUniqueFirstNames);
        model.addAttribute("uniquePatronymics", sortedUniquePatronymics);
        model.addAttribute("uniqueClubs", sortedUniqueClubs);
        model.addAttribute("uniqueCompetitions", sortedUniqueCompetitions);
        model.addAttribute("uniqueResults", sortedUniqueResults);

    }
}
