package db.repository;

import db.entities.AthleteRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRankRepository extends JpaRepository<AthleteRank, Long> {
    AthleteRank findByValue(String value);
}
