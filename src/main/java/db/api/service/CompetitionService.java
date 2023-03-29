package db.api.service;

import db.entities.*;
import db.entities.models.surface.SportsFacility;
import db.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Competition addCompetition(String title, Date period, Sponsor sponsor, Sport sport, SportsFacility sportsFacility) {
        Competition competition = new Competition(title, period, sponsor, sport, sportsFacility);
        competitionRepository.save(competition);
        return competition;
    }

    public Competition deleteCompetition(Long competitionId) {
        Competition competition = competitionRepository.findById(competitionId).orElseThrow();
        competitionRepository.delete(competition);
        return competition;
    }
}
