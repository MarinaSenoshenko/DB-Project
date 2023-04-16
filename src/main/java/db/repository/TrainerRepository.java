package db.repository;

import db.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    @Query(name = "getTrainerByAthlete", nativeQuery = true)
    Iterable<Trainer> getTrainerByAthlete(Long athleteId);
    @Query(name = "getTrainersBySport", nativeQuery = true)
    Iterable<Trainer> getTrainersBySport(Long sportId);
    @Query(name = "getNotUsedInOtherTablesTrainersId", nativeQuery = true)
    Iterable<Long> getNotUsedInOtherTablesTrainersId();
}
