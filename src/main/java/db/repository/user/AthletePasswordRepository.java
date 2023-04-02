package db.repository.user;

import db.entities.users.AthletePassword;
import org.springframework.data.repository.CrudRepository;

public interface AthletePasswordRepository extends CrudRepository<AthletePassword, Long> {
}
