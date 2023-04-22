package db.entities.models.surface;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Arena {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;
    @NotNull
    private Long trackNumber;
    @OneToOne
    @JoinColumn(name = "sports_facility_id", referencedColumnName = "id")
    private SportsFacility sportsFacility;

    public Arena(String param, SportsFacility sportsFacility) {
        this.sportsFacility = sportsFacility;
        this.trackNumber = Long.valueOf(param);
    }
}
