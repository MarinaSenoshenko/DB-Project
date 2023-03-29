package db.api.service;

import db.entities.*;
import db.entities.models.keys.CompetitionKey;
import db.repository.CompetitionPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionPlayerService {
    private final CompetitionPlayerRepository competitionPlayerRepository;

    @Autowired
    public CompetitionPlayerService(CompetitionPlayerRepository competitionPlayerRepository) {
        this.competitionPlayerRepository = competitionPlayerRepository;
    }

    public CompetitionPlayer addCompetitionPlayer(Athlete athlete, Competition competition, boolean wasawarding, Long result) {
        CompetitionPlayer competitionPlayer = new CompetitionPlayer(new CompetitionKey(athlete, competition), wasawarding, result);
        competitionPlayerRepository.save(competitionPlayer);
        return competitionPlayer;
    }

    public CompetitionPlayer deleteCompetitionPlayer(Long athleteId, Long competitionId) {
        CompetitionPlayer competitionPlayer = competitionPlayerRepository.getCompetitionPlayerByAthleteAndCompetition(athleteId, competitionId);
        competitionPlayerRepository.delete(competitionPlayer);
        return competitionPlayer;
    }
}

