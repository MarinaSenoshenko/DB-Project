package db.api.service;

import db.entities.*;
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
        AthleteRanking athleteRanking = new AthleteRanking(athlete, sport, athleteRank);
        System.out.println(athleteRanking);
        athleteRankingRepository.save(athleteRanking);
        return athleteRanking;
    }
}