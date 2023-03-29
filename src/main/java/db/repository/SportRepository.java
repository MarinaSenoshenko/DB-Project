package db.repository;

import db.entities.Sport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SportRepository extends CrudRepository<Sport, Long> {
    @Query(name = "getNotUsedInOtherTablesSports", nativeQuery = true)
    Iterable<Long> getNotUsedInOtherTablesSports();
    Sport findByValue(String value);
    @Query(name = "getSportByValue", nativeQuery = true)
    Sport getSportByValue(String value);
}
