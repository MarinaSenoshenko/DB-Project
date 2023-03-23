package db.entities;

import db.entities.models.keys.CompetitionKey;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@IdClass(CompetitionKey.class)
public class CompetitionPlayer {
    @Id
    @ManyToOne
    @JoinColumn(name = "athlete_id", referencedColumnName = "id")
    private Athlete athlete;
    @Id
    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;
    @NotNull
    private boolean wasAwarding;
    @NotNull
    private Long result;
}
