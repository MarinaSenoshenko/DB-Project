package db.entities.models.keys;

import db.entities.*;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TrainingKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "athlete_id", insertable = false, updatable = false)
    private Athlete athlete;
    @ManyToOne
    @JoinColumn(name = "trainer_license_id", insertable = false, updatable = false)
    private TrainerLicense trainerLicense;
}
