package db.repository.sports;

import db.entities.models.surface.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    @Query(name = "getStadiumByCapacity", nativeQuery = true)
    Iterable<Stadium> getStadiumByCapacity(Long capacity);
}
