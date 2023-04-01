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
        return "/pages/sponsor";
    }

    @GetMapping("/add")
    public String addSponsor() {
        return "/post/add_sponsor";
    }

    @GetMapping("/update")
    public String updateSponsor(Model model) {
        model.addAttribute("sponsors", sponsorRepository.findAll());
        return "/update/update_sponsor";
    }

    @GetMapping("/delete")
    public String deleteSponsor(Model model) {
        model.addAttribute("sponsors", sponsorRepository.getNotUsedInOtherTablesSponsorsId());
        return "/delete/delete_sponsor";
    }

    //TODO вернуть count sponsor, придумать как это нормально показывать
    @GetMapping("/byperiod/{startdate}/{enddate}")
    public String getAndCountSponsorByPeriod(@PathVariable("startdate") String startDate,
                                                  @PathVariable("enddate") String endDate, Model model) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("allsponsors", sponsorRepository.getAndCountSponsorByPeriod(
                dateFormat.parse(startDate), dateFormat.parse(endDate)));
        return "/pages/sponsor";
    }
}
