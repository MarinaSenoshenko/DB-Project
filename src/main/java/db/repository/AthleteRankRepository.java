package db.repository;

import db.entities.AthleteRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AthleteRankRepository extends JpaRepository<AthleteRank, Long> {
    AthleteRank findByValue(String value);
}
