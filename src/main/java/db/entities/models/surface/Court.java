package db.entities.models.surface;

import db.entities.CourtSurface;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Court {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "surface", referencedColumnName = "id")
    private CourtSurface surface;
    @OneToOne
    @JoinColumn(name = "sports_facility_id", referencedColumnName = "id")
    private SportsFacility sportsFacility;

    public Court(CourtSurface surface, SportsFacility sportsFacility) {
        this.sportsFacility = sportsFacility;
        this.surface = surface;
    }
}
