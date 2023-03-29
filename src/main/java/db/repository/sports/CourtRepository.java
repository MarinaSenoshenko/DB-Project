package db.repository.sports;

import db.entities.models.surface.Arena;
import db.entities.models.surface.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourtRepository extends JpaRepository<Court, Long> {
    @Query(name = "getCourtWithSurface", nativeQuery = true)
    List<Court> getCourtWithSurface(String surface);
}
