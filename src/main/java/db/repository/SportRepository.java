package db.repository;

import db.entities.Athlete;
import db.entities.Sport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SportRepository extends CrudRepository<Sport, Long> {
    Sport findByValue(String value);
}
