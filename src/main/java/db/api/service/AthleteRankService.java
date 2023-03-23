package db.api.service;

import db.entities.AthleteRank;
import db.repository.AthleteRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AthleteRankService {
    private final AthleteRankRepository athleteRankRepository;

    @Autowired
    public AthleteRankService(AthleteRankRepository athleteRankRepository) {
        this.athleteRankRepository = athleteRankRepository;
    }

    public AthleteRank addAthleteRank(String value) {
        AthleteRank athleteRank = new AthleteRank(value);
        athleteRankRepository.save(athleteRank);
        return athleteRank;
    }
}
