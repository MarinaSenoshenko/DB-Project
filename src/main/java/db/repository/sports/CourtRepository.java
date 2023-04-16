package db.repository.sports;

import db.entities.models.surface.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {
    Iterable<Court> findCourtBySurfaceValue(String surface);
}
