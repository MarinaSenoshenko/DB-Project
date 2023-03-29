package db.entities;

import db.entities.models.keys.AthleteKey;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AthleteKey.class)
public class AthleteRanking {
    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(name = "athlete_id", referencedColumnName = "id")
    public Athlete athlete;
    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(name = "sport", referencedColumnName = "id")
    public Sport sport;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "rank", referencedColumnName = "id")
    public AthleteRank rank;
}
