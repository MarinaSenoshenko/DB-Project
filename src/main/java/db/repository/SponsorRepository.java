package db.repository;

import db.entities.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    @Query(name = "getAndCountSponsorByPeriod", nativeQuery = true)
    Iterable<?> getAndCountSponsorByPeriod(Date startDate, Date endDate);
    @Query(name = "getNotUsedInOtherTablesSponsorsId", nativeQuery = true)
    Iterable<Long> getNotUsedInOtherTablesSponsorsId();
}
