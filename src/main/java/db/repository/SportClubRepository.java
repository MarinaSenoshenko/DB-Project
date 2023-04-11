package db.repository;

import db.entities.SportClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface SportClubRepository extends JpaRepository<SportClub, Long> {
    @Query(name = "getSportClubsAndCountAthletes", nativeQuery = true)
    Iterable<?> getSportClubsAndCountAthletes(Date startDate, Date endDate);
    @Query(name = "getNotUsedInOtherTablesSportClubsId", nativeQuery = true)
    Iterable<Long> getNotUsedInOtherTablesSportClubsId();
    @Query(name = "getSportClubByTitle", nativeQuery = true)
    SportClub getSportClubByTitle(String title);
}
