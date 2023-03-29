package db.entities;

import db.entities.models.keys.LicenseKey;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class TrainerLicense implements Serializable {
    @Id
    @NotNull
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;
    @Embedded
    private LicenseKey licenseKey;

    public TrainerLicense(LicenseKey licenseKey) {
        this.licenseKey = licenseKey;
    }
}
