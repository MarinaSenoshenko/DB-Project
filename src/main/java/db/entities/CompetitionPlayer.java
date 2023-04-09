package db.entities;

import db.entities.models.keys.CompetitionKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class CompetitionPlayer {
    @EmbeddedId
    private CompetitionKey competitionKey;
    @NotNull
    private boolean wasAwarding;
    private Long result;

    public CompetitionPlayer(CompetitionKey competitionKey, boolean wasawarding, Long result) {
        this.competitionKey = competitionKey;
        this.wasAwarding = wasawarding;
        this.result = result;
    }
}
