package db.repository;

import db.entities.CompetitionPlayer;
import db.entities.models.keys.CompetitionKey;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionPlayerRepository extends CrudRepository<CompetitionPlayer, CompetitionKey> {
}
