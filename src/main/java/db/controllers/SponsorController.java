package db.controllers;

import db.entities.outer.SponsorWithCount;
import db.repository.SponsorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

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
        return "/edit/post/add_sponsor";
    }

    @GetMapping("/update")
    public String updateSponsor(Model model) {
        model.addAttribute("sponsors", sponsorRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        return "/edit/update/update_sponsor";
    }

    @GetMapping("/delete")
    public String deleteSponsor(Model model) {
        model.addAttribute("sponsors", sponsorRepository.getNotUsedInOtherTablesSponsorsId());
        return "/edit/delete/delete_sponsor";
    }

    @GetMapping("/byperiod/{startdate}/{enddate}")
    public String getAndCountSponsorByPeriod(@PathVariable("startdate") String startDate,
                                             @PathVariable("enddate") String endDate,
                                             Model model, Authentication authentication) throws ParseException {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {
            throw new AccessDeniedException("Access denied");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Iterable<?> results = sponsorRepository.getAndCountSponsorByPeriod(dateFormat.parse(startDate),
                dateFormat.parse(endDate));

        List<SponsorWithCount> sponsors = new ArrayList<>();

        for (Object result : results) {
            Object[] row = (Object[]) result;

            SponsorWithCount sponsor = new SponsorWithCount();
            sponsor.setId((Integer) row[0]);
            sponsor.setName((String) row[1]);
            sponsor.setCompany((String) row[2]);
            if (row[3] == null) {
                sponsor.setSponsorsCount(BigInteger.valueOf(0));
            }
            else {
                sponsor.setSponsorsCount((BigInteger) row[3]);
            }
            sponsors.add(sponsor);
        }

        model.addAttribute("allsponsors", sponsors);
        return "pages/outer/sponsorwithcount";
    }
}
