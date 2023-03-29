package db.repository;

import db.entities.TrainerLicense;
import db.entities.models.keys.LicenseKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TrainerLicenseRepository extends CrudRepository<TrainerLicense, LicenseKey> {
    @Query(name = "getTrainerLicenseById", nativeQuery = true)
    TrainerLicense getTrainerLicenseById(Long id);
}
