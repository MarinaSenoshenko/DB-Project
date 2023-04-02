package db.api.rest_controllers;

import db.api.service.SponsorService;
import db.entities.Sponsor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/sponsor")
public class EditSponsorController {
    private final SponsorService sponsorService;

    @PostMapping("")
    public Sponsor addSponsor(@RequestParam("name") String name, @RequestParam("company") String company) {
        return sponsorService.addSponsor(name, company);
    }

    @PutMapping("")
    public Sponsor updateSponsor(@RequestParam("id") Long id, @RequestParam("name") String name,
                                 @RequestParam("company") String company) {
        return sponsorService.updateSponsor(id, name, company);
    }

    @DeleteMapping("")
    public Sponsor deleteSponsor(@RequestParam("sponsor") Long sponsorId) {
        return sponsorService.deleteSponsor(sponsorId);
    }
}