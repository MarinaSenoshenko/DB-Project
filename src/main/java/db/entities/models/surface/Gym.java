package db.entities.models.surface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Gym {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;
    @NotNull
    private Double floorArea;
    @OneToOne
    @JoinColumn(name = "sports_facility_id", referencedColumnName = "id")
    private SportsFacility sportsFacility;

    public Gym(String param, SportsFacility sportsFacility) {
        this.sportsFacility = sportsFacility;
        this.floorArea = Double.valueOf(param);
    }
}
