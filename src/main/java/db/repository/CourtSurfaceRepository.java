package db.repository;

import db.entities.CourtSurface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtSurfaceRepository extends JpaRepository<CourtSurface, Long> {
    CourtSurface findByValue(String value);
    @Query(name = "getNotUsedInOtherTablesSurfaces", nativeQuery = true)
    Iterable<Long> getNotUsedInOtherTablesSurfaces();
}
