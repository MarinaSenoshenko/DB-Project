package db.repository;

import db.entities.AthleteRanking;
import db.entities.models.keys.AthleteKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AthleteRankingRepository extends CrudRepository<AthleteRanking, AthleteKey> {
    @Query(name = "getAthleteRankingByAthleteAndSport", nativeQuery = true)
    AthleteRanking getAthleteRankingByAthleteAndSport(Long athleteId, Long sportId);
}
