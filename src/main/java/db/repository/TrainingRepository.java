package db.repository;

import db.entities.Training;
import db.entities.models.keys.TrainingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrainingRepository extends JpaRepository<Training, TrainingKey> {
    @Query(name = "deleteTrainingByTrainerAndAthleteId", nativeQuery = true)
    Training deleteTrainingByTrainerAndAthleteId(Long trainerId, Long athleteId);
}
