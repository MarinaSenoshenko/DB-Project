package db.repository;

import db.entities.Athlete;
import db.entities.Trainer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.lang.annotation.Target;

public interface TrainerRepository extends CrudRepository<Trainer, Long> {
    @Query(name = "getTrainerByAthlete", nativeQuery = true)
    Iterable<Trainer> getTrainerByAthlete(Long athleteId);
    @Query(name = "getTrainersBySport", nativeQuery = true)
    Iterable<Trainer> getTrainersBySport(Long sportId);
    @Query(name = "getTrainerById", nativeQuery = true)
    Trainer getTrainerById(Long id);
}
