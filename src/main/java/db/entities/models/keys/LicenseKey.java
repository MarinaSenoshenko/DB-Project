package db.entities.models.keys;

import db.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LicenseKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "trainer_id", insertable = false, updatable = false)
    public Trainer trainer;
    @ManyToOne
    @JoinColumn(name = "sport", insertable = false, updatable = false)
    public Sport sport;
}
