package db.repository;

import db.entities.SportClub;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface SportClubRepository extends CrudRepository<SportClub, Long> {
    @Query(name = "getSportClubsAndCountAthletes", nativeQuery = true)
    Iterable<SportClub> getSportClubsAndCountAthletes(Date startDate, Date endDate);
    @Query(name = "getSportClubByTitle", nativeQuery = true)
    SportClub getSportClubByTitle(String title);
}
