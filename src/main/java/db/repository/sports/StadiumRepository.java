package db.repository.sports;

import db.entities.models.surface.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    Iterable<Stadium> findByCapacity(Long capacity);
}
