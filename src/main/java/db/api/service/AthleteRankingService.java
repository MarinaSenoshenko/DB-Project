package db.api.service;

import db.entities.Athlete;
import db.entities.AthleteRank;
import db.entities.AthleteRanking;
import db.entities.Sport;
import db.entities.models.keys.AthleteKey;
import db.repository.AthleteRankRepository;
import db.repository.AthleteRankingRepository;
import db.repository.AthleteRepository;
import db.repository.SportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AthleteRankingService {
    private final AthleteRankingRepository athleteRankingRepository;
    private final AthleteRankRepository athleteRankRepository;
    private final AthleteRepository athleteRepository;
    private final SportRepository sportRepository;

    public AthleteRanking addAthleteRanking(Long athleteId, String rank, String value) {
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow();
        Sport sport = sportRepository.findByValue(value);
        AthleteRank athleteRank = athleteRankRepository.findByValue(rank);

        AthleteRanking athleteRanking = new AthleteRanking(new AthleteKey(athlete, sport), athleteRank);
        athleteRankingRepository.save(athleteRanking);
        return athleteRanking;
    }

    public AthleteRanking deleteAthleteRanking(Long athleteId, Long sportId) {
        AthleteRanking athleteRanking = athleteRankingRepository.getAthleteRankingByAthleteAndSport(athleteId, sportId);
        athleteRankingRepository.delete(athleteRanking);
        return athleteRanking;
    }

    public AthleteRanking updateAthleteRanking(Long athleteId, Long sportId, String rank) {
        AthleteRanking athleteRanking = athleteRankingRepository.getAthleteRankingByAthleteAndSport(athleteId, sportId);
        AthleteRank athleteRank = athleteRankRepository.findByValue(rank);
        athleteRanking.setRank(athleteRank);
        athleteRankingRepository.save(athleteRanking);
        return athleteRanking;
    }
}
