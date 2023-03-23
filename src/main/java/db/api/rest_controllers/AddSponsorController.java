package db.api.rest_controllers;

import db.api.service.SponsorService;
import db.entities.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addsponsor")
public class AddSponsorController {
    private final SponsorService sponsorService;

    @Autowired
    public AddSponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @PostMapping("")
    public Sponsor addRank(@RequestParam("name") String name, @RequestParam("company") String company) {
        return sponsorService.addSponsor(name, company);
    }
}