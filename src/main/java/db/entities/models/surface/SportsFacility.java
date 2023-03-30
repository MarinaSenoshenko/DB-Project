package db.entities.models.surface;

import db.entities.SportsFacilityType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportsFacility implements Serializable {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;
    private String address;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private SportsFacilityType type;

    public SportsFacility(String address, SportsFacilityType type) {
        this.address = address;
        this.type = type;
    }
}
