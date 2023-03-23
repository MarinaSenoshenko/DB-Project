package db.entities.models.surface;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Stadium {
    @Id
    private Long id;
    @NotNull
    private Long capacity;
    @OneToOne
    @JoinColumn(name = "sports_facility_id", referencedColumnName = "id")
    private SportsFacility sportsFacility;
}
