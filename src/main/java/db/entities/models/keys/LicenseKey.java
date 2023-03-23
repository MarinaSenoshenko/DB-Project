package db.entities.models.keys;

import db.entities.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class LicenseKey implements Serializable {
    private Trainer trainer;
    private Sport sport;
}
