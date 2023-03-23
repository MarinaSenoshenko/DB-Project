package db.entities.models.keys;

import db.entities.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class TrainingKey implements Serializable {
    private Athlete athlete;
    private TrainerLicense trainerLicense;
}
