package db.api.rest_controllers;

import db.api.service.SponsorService;
import db.entities.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addsponsor")
public class EditSponsorController {
    private final SponsorService sponsorService;

    @Autowired
    public EditSponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @PostMapping("")
    public Sponsor addSponsor(@RequestParam("name") String name, @RequestParam("company") String company) {
        return sponsorService.addSponsor(name, company);
    }

    @DeleteMapping("")
    public Sponsor deleteSponsor(@RequestParam("sponsor") Long sponsorId) {
        return sponsorService.deleteSponsor(sponsorId);
    }
}