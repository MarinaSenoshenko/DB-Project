package db.controllers;

import db.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.*;

@Controller
@RequestMapping("/main/athlete")
@AllArgsConstructor
public class AthleteController {
    private final AthleteRepository athleteRepository;
    private final AthleteRankRepository athleteRankRepository;
    private final TrainerLicenseRepository trainerLicenseRepository;
    private final CompetitionRepository competitionRepository;
    private final SportRepository sportRepository;
    private final SportClubRepository sportClubRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("athletes", athleteRepository.findAll());
        addAttributesToModel(model);
        return "athlete";
    }

    @GetMapping("/add")
    public String addAthlete(Model model) {
        addAttributesToModel(model);
        model.addAttribute("sportclubs", sportClubRepository.findAll());
        return "add_athlete";
    }

    @GetMapping("/delete")
    public String deleteAthlete(Model model) {
        model.addAttribute("athletes", athleteRepository.getNotUsedInOtherTablesAthletes());
        return "delete_athlete";
    }

    @GetMapping("/add/addranking")
    public String addAthleteRanking(Model model) {
        addAttributesToModel(model);
        return "add_ranking";
    }

    @GetMapping("/bysport/{sport}/{athleterank}")
    public String getAthletesByRanking(@PathVariable("sport") String sport, @PathVariable("athleterank")
    String athleteRankValue, Model model) {
        addAttributesToModel(model);
        model.addAttribute("athletes", athleteRepository.getAthletesByRanking(sport,
                  athleteRankRepository.findByValue(athleteRankValue).getId()));
        return "athlete";
    }

    @GetMapping("/bytrainerlicense/{trainerlicenseid}/{athleterank}")
    public String getAthletesByTrainerAndRank(@PathVariable("trainerlicenseid") Long trainerLicenseId,
                                                         @PathVariable("athleterank") String athleteRankValue, Model model) {
        addAttributesToModel(model);
        model.addAttribute("athletes",athleteRepository.getAthletesByTrainerAndRank(trainerLicenseId,
                athleteRankRepository.findByValue(athleteRankValue).getId()));
        return "athlete";
    }

    //TODO изменить запрос, добавить виды спорта
    @GetMapping("/morethanone")
    public String getAthletesWhoMoreThanOneSport(Model model) {
        model.addAttribute("athletes", athleteRepository.getAthletesWhoMoreThanOneSport());
        return "athlete";
    }

    @GetMapping("/bycompetition/{competitionid}")
    public String getAthletesWhoWinnerByCompetition(@PathVariable("competitionid") Long competitionId, Model model) {
        addAttributesToModel(model);
        model.addAttribute("athletes", athleteRepository.getAthletesWhoWinnerByCompetition(competitionId));
        return "athlete";
    }

    @GetMapping("/notincompetitionbyperiod/{startdate}/{enddate}")
    public String getAthletesWhoNotInCompetitionByPeriod(@PathVariable("startdate") String startDate, @PathVariable("enddate")
    String endDate, Model model) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        addAttributesToModel(model);
        model.addAttribute("athletes", athleteRepository.getAthletesWhoNotInCompetitionByPeriod(
                dateFormat.parse(startDate), dateFormat.parse(endDate)));
        return "athlete";
    }

    private void addAttributesToModel(Model model) {
        model.addAttribute("sports", sportRepository.findAll());
        model.addAttribute("athleteranks", athleteRankRepository.findAll());
        model.addAttribute("trainerlicenses", trainerLicenseRepository.findAll());
        model.addAttribute("competitions", competitionRepository.findAll());
    }
}
