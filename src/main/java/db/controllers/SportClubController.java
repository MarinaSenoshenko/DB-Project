package db.controllers;

import db.entities.outer.SportClubWithAthletes;
import db.repository.SportClubRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute("sportclubs", sportClubRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        return "/edit/update/update_sport_club";
    }

    @GetMapping("/delete")
    public String deleteSportClub(Model model) {
        model.addAttribute("sportclubs", sportClubRepository.getNotUsedInOtherTablesSportClubsId());
        return "/edit/delete/delete_sport_club";
    }

    @GetMapping("/byperiod/{startdate}/{enddate}")
    public String getSportClubsAndCountAthletes(@PathVariable("startdate") String startDate,
                                                @PathVariable("enddate") String endDate, Model model) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Iterable<?> results = sportClubRepository.getSportClubsAndCountAthletes(dateFormat.parse(startDate),
                dateFormat.parse(endDate));

        List<SportClubWithAthletes> sportClubs = new ArrayList<>();

        for (Object result : results) {
            Object[] row = (Object[]) result;

            SportClubWithAthletes sportClub = new SportClubWithAthletes();
            sportClub.setId((Integer) row[0]);
            sportClub.setTitle((String) row[1]);
            if (row[2] == null) {
                sportClub.setAthletesCount(BigInteger.valueOf(0));
            }
            else {
                sportClub.setAthletesCount((BigInteger) row[2]);
            }
            sportClubs.add(sportClub);
        }

        model.addAttribute("allsportclubs", sportClubs);
        return "pages/outer/sportclubwithathletes";
    }
}
