package db.entities;

import db.entities.models.keys.TrainingKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    @EmbeddedId
    TrainingKey trainingKey;
}
