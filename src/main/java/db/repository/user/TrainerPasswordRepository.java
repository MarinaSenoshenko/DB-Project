package db.repository.user;

import db.entities.users.TrainerPassword;
import org.springframework.data.repository.CrudRepository;

public interface TrainerPasswordRepository extends CrudRepository<TrainerPassword, Long> {
}
