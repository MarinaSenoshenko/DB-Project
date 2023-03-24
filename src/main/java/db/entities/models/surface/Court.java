package db.entities.models.surface;

import db.entities.CourtSurface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    public Court(String param, SportsFacility sportsFacility) {
        this.sportsFacility = sportsFacility;
//        this.surface = Long.valueOf(param);
    }
}
