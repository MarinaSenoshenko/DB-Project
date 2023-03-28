package db.repository.sports;

import db.entities.models.surface.Arena;
import db.entities.models.surface.Gym;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GymRepository extends CrudRepository<Gym, Long> {
    @Query(name = "getGymByFloorArea", nativeQuery = true)
    Iterable<Gym> getGymByFloorArea(Double floorArea);
    @Query(name = "getGymById", nativeQuery = true)
    Gym getGymById(Long id);
}
