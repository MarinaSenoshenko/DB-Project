package db.entities.models.keys;

import db.entities.*;
import lombok.*;
import javax.persistence.Embeddable;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class AthleteKey implements Serializable {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "athlete_id", insertable = false, updatable = false)
    private Athlete athlete;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "sport", insertable = false, updatable = false)
    private Sport sport;
}
