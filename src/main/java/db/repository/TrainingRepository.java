package db.repository;

import db.entities.Training;
import db.entities.models.keys.TrainingKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends CrudRepository<Training, TrainingKey> {
    @Query(name = "deleteTrainingByTrainerAndAthleteId", nativeQuery = true)
    Training deleteTrainingByTrainerAndAthleteId(Long trainerId, Long athleteId);
}
