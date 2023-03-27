package db.api.service;

import db.entities.Sponsor;
import db.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorService {
    private final SponsorRepository sponsorRepository;

    @Autowired
    public SponsorService(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    public Sponsor addSponsor(String name, String company) {
        Sponsor sponsor = new Sponsor(name, company);
        sponsorRepository.save(sponsor);
        return sponsor;
    }

    public Sponsor deleteSponsor(Long sponsorId) {
        Sponsor sponsor = sponsorRepository.getSponsorById(sponsorId);
        sponsorRepository.delete(sponsor);
        return sponsor;
    }
}