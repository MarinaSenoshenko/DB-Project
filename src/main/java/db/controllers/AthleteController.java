package db.controllers;

import db.entities.outer.AthleteWithSports;
import db.repository.*;
import db.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    private final UserRepository userRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("role", userRepository.toString());
        addAttributesToModel(model);
        return "/pages/athlete";
    }

    @GetMapping("/add")
    public String addAthlete(Model model) {
        addAttributesToModel(model);
        model.addAttribute("sportclubs", sportClubRepository.findAll());
        return "/edit/post/add_athlete";
    }

    @GetMapping("/update")
    public String updateAthlete(Model model) {
        addAttributesToModel(model);
        model.addAttribute("athletes", athleteRepository.findAll());
        model.addAttribute("sportclubs", sportClubRepository.findAll());
        return "/edit/update/update_athlete";
    }

    @GetMapping("/delete")
    public String deleteAthlete(Model model) {
        model.addAttribute("athletes", athleteRepository.getNotUsedInOtherTablesAthletes());
        return "/edit/delete/delete_athlete";
    }


    @GetMapping("/bysport/{sport}/{athleterank}")
    public String getAthletesByRanking(@PathVariable("sport") String sport, @PathVariable("athleterank")
    String athleteRankValue, Model model) {
        addAttributesToModel(model);
        model.addAttribute("athletes", athleteRepository.getAthletesByRanking(sport,
                  athleteRankRepository.findByValue(athleteRankValue).getId()));
        return "/pages/athlete";
    }

    @GetMapping("/bytrainerlicense/{trainerlicenseid}/{athleterank}")
    public String getAthletesByTrainerAndRank(@PathVariable("trainerlicenseid") Long trainerLicenseId,
                                                         @PathVariable("athleterank") String athleteRankValue, Model model) {
        addAttributesToModel(model);
        model.addAttribute("athletes",athleteRepository.getAthletesByTrainerAndRank(trainerLicenseId,
                athleteRankRepository.findByValue(athleteRankValue).getId()));
        return "/pages/athlete";
    }

    @GetMapping("/morethanone")
    public String getAthletesWhoMoreThanOneSport(Model model) {
        Iterable<?> results = athleteRepository.getAthletesWhoMoreThanOneSport();

        List<AthleteWithSports> athletes = new ArrayList<>();

        for (Object result : results) {
            Object[] row = (Object[]) result;

            AthleteWithSports athlete = new AthleteWithSports();
            athlete.setId((Integer) row[0]);
            athlete.setFirstName((String) row[1]);
            athlete.setPatronymic((String) row[2]);
            athlete.setLastName((String) row[3]);
            athlete.setTitle((String) row[4]);
            athlete.setValue((String) row[5]);
            athletes.add(athlete);
        }
        model.addAttribute("athletes", athletes);

        return "pages/outer/athletewithsports";
    }

    @GetMapping("/bycompetition/{competitionid}")
    public String getAthletesWhoWinnerByCompetition(@PathVariable("competitionid") Long competitionId, Model model) {
        addAttributesToModel(model);
        model.addAttribute("athletes", athleteRepository.getAthletesWhoWinnerByCompetition(competitionId));
        return "/pages/athlete";
    }

    @GetMapping("/notincompetitionbyperiod/{startdate}/{enddate}")
    public String getAthletesWhoNotInCompetitionByPeriod(@PathVariable("startdate") String startDate, @PathVariable("enddate")
    String endDate, Model model) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        addAttributesToModel(model);
        model.addAttribute("athletes", athleteRepository.getAthletesWhoNotInCompetitionByPeriod(
                dateFormat.parse(startDate), dateFormat.parse(endDate)));
        return "/pages/athlete";
    }

    private void addAttributesToModel(Model model) {
        model.addAttribute("sports", sportRepository.findAll());
        model.addAttribute("athleteranks", athleteRankRepository.findAll());
        model.addAttribute("trainerlicenses", trainerLicenseRepository.findAll());
        model.addAttribute("competitions", competitionRepository.findAll());
    }
}
