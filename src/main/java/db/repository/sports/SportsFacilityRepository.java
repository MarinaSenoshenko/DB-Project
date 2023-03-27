package db.repository.sports;

import db.entities.models.surface.SportsFacility;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface SportsFacilityRepository extends CrudRepository<SportsFacility, Long> {
    @Query(name = "getSportsFacilityByCompetitionPeriod", nativeQuery = true)
    Iterable<SportsFacility> getSportsFacilityByCompetitionPeriod(Date startDate, Date endDate);
    @Query(name = "getMaxFacilityId", nativeQuery = true)
    Long getMaxFacilityId(String value);
    @Query(name = "getSportsFacilityById", nativeQuery = true)
    SportsFacility getSportsFacilityById(Long id);
}
