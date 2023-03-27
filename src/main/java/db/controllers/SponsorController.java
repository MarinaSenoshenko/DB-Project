package db.controllers;

import db.repository.SponsorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.*;

@Controller
@RequestMapping("/main/sponsor")
@AllArgsConstructor
public class SponsorController {
    private SponsorRepository sponsorRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("allsponsors", sponsorRepository.findAll());
        return "sponsor";
    }

    @GetMapping("/add")
    public String addSponsor() {
        return "add_sponsor";
    }

    @GetMapping("/delete")
    public String deleteSponsor(Model model) {
        Iterable<Long> sponsorsId = sponsorRepository.getNotUsedInOtherTablesSponsorsId();
        model.addAttribute("sponsors", sponsorsId);
        return "delete_sponsor";
    }

    //TODO вернуть count sponsor, придумать как это нормально показывать
    @GetMapping("/byperiod/{startdate}/{enddate}")
    public String getAndCountSponsorByPeriod(@PathVariable("startdate") String startDate,
                                                  @PathVariable("enddate") String endDate, Model model) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("allsponsors", sponsorRepository.getAndCountSponsorByPeriod(
                dateFormat.parse(startDate), dateFormat.parse(endDate)));
        return "sponsor";
    }
}
