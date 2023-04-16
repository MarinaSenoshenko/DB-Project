package db.repository.sports;

import db.entities.models.surface.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
    Iterable<Gym> findByFloorArea(Double floorArea);
}
