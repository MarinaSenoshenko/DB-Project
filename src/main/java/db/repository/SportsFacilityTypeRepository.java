package db.repository;

import db.entities.SportsFacilityType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SportsFacilityTypeRepository extends CrudRepository<SportsFacilityType, Long> {
    @Query(name = "getSportsFacilityByValue", nativeQuery = true)
    SportsFacilityType getSportsFacilityByValue(String value);
}
