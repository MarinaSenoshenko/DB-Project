package db.repository;

import db.entities.Training;
import db.entities.models.keys.TrainingKey;
import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends CrudRepository<Training, TrainingKey> {
}
