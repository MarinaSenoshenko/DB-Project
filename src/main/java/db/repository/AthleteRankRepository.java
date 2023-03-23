package db.repository;

import db.entities.AthleteRank;
import org.springframework.data.repository.CrudRepository;

public interface AthleteRankRepository extends CrudRepository<AthleteRank, Long> {
    AthleteRank findByValue(String value);
}
