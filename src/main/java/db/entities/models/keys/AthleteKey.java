package db.entities.models.keys;

import db.entities.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class AthleteKey implements Serializable {
    private Athlete athlete;
    private Sport sport;
}
