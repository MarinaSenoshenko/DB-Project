package db.repository.sports;

import db.entities.models.surface.Arena;
import db.entities.models.surface.SportsFacility;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArenaRepository extends CrudRepository<Arena, Long> {
    @Query(name = "getArenaByTrackNumber", nativeQuery = true)
    Iterable<Arena> getArenaByTrackNumber(Long trackNumber);
}
