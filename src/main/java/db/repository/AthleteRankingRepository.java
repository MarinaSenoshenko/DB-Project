package db.repository;

import db.entities.AthleteRanking;
import db.entities.models.keys.AthleteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AthleteRankingRepository extends JpaRepository<AthleteRanking, AthleteKey> {
    @Query(name = "getAthleteRankingByAthleteAndSport", nativeQuery = true)
    AthleteRanking getAthleteRankingByAthleteAndSport(Long athleteId, Long sportId);
}
