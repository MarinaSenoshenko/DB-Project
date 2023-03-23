package db.entities.models.surface;

import db.entities.CourtSurface;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Court {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "surface", referencedColumnName = "id")
    private CourtSurface surface;
    @OneToOne
    @JoinColumn(name = "sports_facility_id", referencedColumnName = "id")
    private SportsFacility sportsFacility;
}
