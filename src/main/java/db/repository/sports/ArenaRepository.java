package db.repository.sports;

import db.entities.models.surface.Arena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArenaRepository extends JpaRepository<Arena, Long> {
    @Query(name = "getArenaByTrackNumber", nativeQuery = true)
    Iterable<Arena> getArenaByTrackNumber(Long trackNumber);
}
