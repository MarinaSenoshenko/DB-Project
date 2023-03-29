package db.entities.models.keys;

import db.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "athlete_id", insertable = false, updatable = false)
    public Athlete athlete;
    @ManyToOne
    @JoinColumn(name = "competition_id", insertable = false, updatable = false)
    public Competition competition;
}
