package db.repository;

import db.entities.SportsFacilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SportsFacilityTypeRepository extends JpaRepository<SportsFacilityType, Long> {
    @Query(name = "getSportsFacilityByValue", nativeQuery = true)
    SportsFacilityType getSportsFacilityByValue(String value);
}
