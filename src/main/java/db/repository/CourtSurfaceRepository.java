package db.repository;

import db.entities.CourtSurface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourtSurfaceRepository extends JpaRepository<CourtSurface, Long> {
    @Query(name = "getCourtSurfaceByValue", nativeQuery = true)
    CourtSurface getCourtSurfaceByValue(String value);
    @Query(name = "getNotUsedInOtherTablesSurfaces", nativeQuery = true)
    Iterable<Long> getNotUsedInOtherTablesSurfaces();
}
