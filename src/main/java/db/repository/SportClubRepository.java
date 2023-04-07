package db.repository;

import db.entities.SportClub;
import db.entities.SportClubWithAthletes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface SportClubRepository extends CrudRepository<SportClub, Long> {
    @Query(name = "getSportClubsAndCountAthletes", nativeQuery = true)
    Iterable<?> getSportClubsAndCountAthletes(Date startDate, Date endDate);
    @Query(name = "getNotUsedInOtherTablesSportClubsId", nativeQuery = true)
    Iterable<Long> getNotUsedInOtherTablesSportClubsId();
    @Query(name = "getSportClubByTitle", nativeQuery = true)
    SportClub getSportClubByTitle(String title);
}
