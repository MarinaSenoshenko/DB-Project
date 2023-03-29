package db.repository;

import db.api.service.CompetitionPlayerService;
import db.entities.CompetitionPlayer;
import db.entities.models.keys.CompetitionKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionPlayerRepository extends CrudRepository<CompetitionPlayer, CompetitionKey> {
    @Query(name = "getCompetitionPlayerByAthleteAndCompetition", nativeQuery = true)
    CompetitionPlayer getCompetitionPlayerByAthleteAndCompetition(Long athleteId, Long competitionId);
}
