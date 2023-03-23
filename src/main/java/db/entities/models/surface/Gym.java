package db.entities.models.surface;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Gym {
    @Id
    private Long id;
    @NotNull
    private Double floorArea;
    @OneToOne
    @JoinColumn(name = "sports_facility_id", referencedColumnName = "id")
    private SportsFacility sportsFacility;
}
