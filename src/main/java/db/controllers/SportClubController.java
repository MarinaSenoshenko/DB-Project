package db.controllers;

import db.repository.SportClubRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.*;


@Controller
@RequestMapping("/main/sportclub")
@AllArgsConstructor
public class SportClubController {
    private SportClubRepository sportClubRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("allsportclubs", sportClubRepository.findAll());
        return "sportclub";
    }

    @GetMapping("/add")
    public String addSportClub() {
        return "add_sport_club";
    }

    //TODO вернуть count athletes, придумать как это нормально показывать
    @GetMapping("/byperiod/{startdate}/{enddate}")
    public String getSportClubsAndCountAthletes(@PathVariable("startdate") String startDate,
                                                     @PathVariable("enddate") String endDate, Model model) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("allsportclubs", sportClubRepository.getSportClubsAndCountAthletes(
                dateFormat.parse(startDate), dateFormat.parse(endDate)));
        return "sportclub";
    }
}
