package db.repository;

import db.entities.SportsFacilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsFacilityTypeRepository extends JpaRepository<SportsFacilityType, Long> {
    SportsFacilityType findByValue(String value);
}
