package db.repository;

import db.entities.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
    @Query(name = "getNotUsedInOtherTablesSports", nativeQuery = true)
    Iterable<Long> getNotUsedInOtherTablesSports();
    Sport findByValue(String value);
}
