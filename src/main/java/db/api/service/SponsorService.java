package db.api.service;

import db.entities.Sponsor;
import db.repository.SponsorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class SponsorService {
    private final SponsorRepository sponsorRepository;

    public Sponsor addSponsor(String name, String company) {
        Sponsor sponsor = new Sponsor(name, company);
        sponsorRepository.save(sponsor);
        return sponsor;
    }

    public Sponsor deleteSponsor(Long sponsorId) {
        Sponsor sponsor = sponsorRepository.findById(sponsorId).orElseThrow();
        sponsorRepository.delete(sponsor);
        return sponsor;
    }

    public Sponsor updateSponsor(Long id, String name, String company) {
        Sponsor sponsor = sponsorRepository.findById(id).orElseThrow();
        if (!Objects.equals(company, "")) {
            sponsor.setCompany(company);
        }
        if (!Objects.equals(name, "")) {
            sponsor.setName(name);
        }
        sponsorRepository.save(sponsor);
        return sponsor;
    }
}
