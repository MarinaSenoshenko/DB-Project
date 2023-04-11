package db.repository;

import db.entities.TrainerLicense;
import db.entities.models.keys.LicenseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrainerLicenseRepository extends JpaRepository<TrainerLicense, LicenseKey> {
    @Query(name = "getTrainerLicenseById", nativeQuery = true)
    TrainerLicense getTrainerLicenseById(Long trainerId);
}
