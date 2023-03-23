package db.entities.models.keys;

import db.entities.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class CompetitionKey implements Serializable {
    private Athlete athlete;
    private Competition competition;
}
