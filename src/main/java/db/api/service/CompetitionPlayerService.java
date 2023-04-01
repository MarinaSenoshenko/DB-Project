package db.api.service;

import db.entities.*;
import db.entities.models.keys.CompetitionKey;
import db.repository.CompetitionPlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CompetitionPlayerService {
    private final CompetitionPlayerRepository competitionPlayerRepository;

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

    public CompetitionPlayer updateCompetitionPlayer(Long athleteId, Long competitionId, boolean wasawarding, Long result) {
        CompetitionPlayer competitionPlayer = competitionPlayerRepository.getCompetitionPlayerByAthleteAndCompetition(athleteId, competitionId);
        if (wasawarding != competitionPlayer.isWasAwarding()) {
            competitionPlayer.setWasAwarding(wasawarding);
        }
        if (!Objects.equals(result, competitionPlayer.getResult())) {
            competitionPlayer.setResult(result);
        }
        competitionPlayerRepository.save(competitionPlayer);
        return competitionPlayer;
    }
}

