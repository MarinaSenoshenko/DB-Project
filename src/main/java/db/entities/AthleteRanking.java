package db.entities;

import db.entities.models.keys.AthleteKey;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@IdClass(AthleteKey.class)
public class AthleteRanking {
    @Id
    @ManyToOne
    @JoinColumn(name = "athlete_id", referencedColumnName = "id")
    private Athlete athlete;
    @Id
    @ManyToOne
    @JoinColumn(name = "sport", referencedColumnName = "id")
    private Sport sport;
    @ManyToOne
    @JoinColumn(name = "rank", referencedColumnName = "id")
    private AthleteRank rank;
}
