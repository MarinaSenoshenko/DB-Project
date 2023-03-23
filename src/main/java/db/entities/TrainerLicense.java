package db.entities;

import db.entities.models.keys.LicenseKey;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@IdClass(LicenseKey.class)
public class TrainerLicense implements Serializable {
    @NotNull
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Id
    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;
    @Id
    @ManyToOne
    @JoinColumn(name = "sport", referencedColumnName = "id")
    private Sport sport;
}
