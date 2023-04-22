package db.repository;

import db.entities.models.surface.Arena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArenaRepository extends JpaRepository<Arena, Long> {
    Iterable<Arena> findArenaByTrackNumber(Long trackNumber);
}
