package db.repository;

import db.entities.Sponsor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface SponsorRepository extends CrudRepository<Sponsor, Long> {
    @Query(name = "getAndCountSponsorByPeriod", nativeQuery = true)
    Iterable<?> getAndCountSponsorByPeriod(Date startDate, Date endDate);
    @Query(name = "getNotUsedInOtherTablesSponsorsId", nativeQuery = true)
    Iterable<Long> getNotUsedInOtherTablesSponsorsId();
}
