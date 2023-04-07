package db.controllers;

import db.entities.SportClub;
import db.entities.SportClubWithAthletes;
import db.repository.SportClubRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/main/sportclub")
@AllArgsConstructor
public class SportClubController {
    private SportClubRepository sportClubRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("allsportclubs", sportClubRepository.findAll());
        return "/pages/sportclub";
    }

    @GetMapping("/add")
    public String addSportClub() {
        return "/edit/post/add_sport_club";
    }

    @GetMapping("/update")
    public String updateSportClub(Model model) {
        model.addAttribute("sportclubs", sportClubRepository.findAll());
        return "/edit/update/update_sport_club";
    }

    @GetMapping("/delete")
    public String deleteSportClub(Model model) {
        model.addAttribute("sportclubs", sportClubRepository.getNotUsedInOtherTablesSportClubsId());
        return "/edit/delete/delete_sport_club";
    }

    public static <T> Collection<T> iterableToCollection(Iterable<T> iterable) {
        Collection<T> collection = new ArrayList<>();
        for (T element: iterable) {
            collection.add(element);
        }
        return collection;
    }

    // TODO вернуть count athletes, придумать как это нормально показывать
    @GetMapping("/byperiod/{startdate}/{enddate}")
    public String getSportClubsAndCountAthletes(@PathVariable("startdate") String startDate,
                                                @PathVariable("enddate") String endDate, Model model) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        model.addAttribute("allsportclubs", sportClubRepository.getSportClubsAndCountAthletes(
                dateFormat.parse(startDate), dateFormat.parse(endDate)));
        return "/pages/sportclub2";
    }
}
