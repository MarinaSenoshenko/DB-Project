package db.entities.models.keys;

import db.entities.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LicenseKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    public Trainer trainer;
    @ManyToOne
    @JoinColumn(name = "sport", referencedColumnName = "id")
    public Sport sport;
}