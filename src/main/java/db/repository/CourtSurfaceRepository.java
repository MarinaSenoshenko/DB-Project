package db.repository;

import db.entities.CourtSurface;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourtSurfaceRepository extends CrudRepository<CourtSurface, Long> {
    @Query(name = "getCourtSurfaceByValue", nativeQuery = true)
    CourtSurface getCourtSurfaceByValue(String value);
    @Query(name = "getNotUsedInOtherTablesSurfaces", nativeQuery = true)
    Iterable<Long> getNotUsedInOtherTablesSurfaces();
    @Query(name = "getCourtSurfaceById", nativeQuery = true)
    CourtSurface getCourtSurfaceById(Long id);
}
