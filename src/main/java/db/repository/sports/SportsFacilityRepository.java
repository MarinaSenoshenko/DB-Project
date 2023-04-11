package db.repository.sports;

import db.entities.models.surface.SportsFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface SportsFacilityRepository extends JpaRepository<SportsFacility, Long> {
    @Query(name = "getSportsFacilityByCompetitionPeriod", nativeQuery = true)
    Iterable<SportsFacility> getSportsFacilityByCompetitionPeriod(Date startDate, Date endDate);
    @Query(name = "getNotUsedInArenaSportsFacilityId", nativeQuery = true)
    Iterable<Long> getNotUsedInArenaSportsFacilityId();
    @Query(name = "getMaxFacilityId", nativeQuery = true)
    Long getMaxFacilityId(String value);
}
