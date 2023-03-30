package db.api.service;

import db.entities.*;
import db.entities.models.keys.AthleteKey;
import db.repository.AthleteRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AthleteRankingService {
    private final AthleteRankingRepository athleteRankingRepository;

    @Autowired
    public AthleteRankingService(AthleteRankingRepository athleteRankingRepository) {
        this.athleteRankingRepository = athleteRankingRepository;
    }

    public AthleteRanking addAthleteRanking(Athlete athlete, Sport sport, AthleteRank athleteRank) {
        AthleteRanking athleteRanking = new AthleteRanking(new AthleteKey(athlete, sport), athleteRank);
        athleteRankingRepository.save(athleteRanking);
        return athleteRanking;
    }

    public AthleteRanking deleteAthleteRanking(Long athleteId, Long sportId) {
        AthleteRanking athleteRanking = athleteRankingRepository.getAthleteRankingByAthleteAndSport(athleteId, sportId);
        athleteRankingRepository.delete(athleteRanking);
        return athleteRanking;
    }
}