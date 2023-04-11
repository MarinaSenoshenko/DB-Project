package db.repository.sports;

import db.entities.models.surface.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GymRepository extends JpaRepository<Gym, Long> {
    @Query(name = "getGymByFloorArea", nativeQuery = true)
    Iterable<Gym> getGymByFloorArea(Double floorArea);
}
