package db.entities;

import db.entities.models.keys.AthleteKey;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class AthleteRanking {
    @EmbeddedId
    private AthleteKey athleteKey;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "rank", referencedColumnName = "id")
    public AthleteRank rank;

    public AthleteRanking(AthleteKey athleteKey, AthleteRank athleteRank) {
        this.athleteKey = athleteKey;
        this.rank = athleteRank;
    }
}
