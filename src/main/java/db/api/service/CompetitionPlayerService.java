package db.api.service;

import db.entities.Athlete;
import db.entities.Competition;
import db.entities.CompetitionPlayer;
import db.entities.models.keys.CompetitionKey;
import db.repository.AthleteRepository;
import db.repository.CompetitionPlayerRepository;
import db.repository.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CompetitionPlayerService {
    private final CompetitionPlayerRepository competitionPlayerRepository;
    private final AthleteRepository athleteRepository;
    private final CompetitionRepository competitionRepository;

    public CompetitionPlayer addCompetitionPlayer(Long athleteId, Long competitionId, boolean wasawarding, Long result) {
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow();
        Competition competition = competitionRepository.findById(competitionId).orElseThrow();
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
