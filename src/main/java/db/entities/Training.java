package db.entities;

import db.entities.models.keys.TrainingKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@IdClass(TrainingKey.class)
public class Training {
    @Id
    @ManyToOne
    @JoinColumn(name = "athlete_id", referencedColumnName = "id")
    private Athlete athlete;
    @Id
    @ManyToOne
    @JoinColumn(name = "trainer_license_id", referencedColumnName = "id")
    private TrainerLicense trainerLicense;
}
