package db.entities;

import db.entities.models.surface.SportsFacility;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "competition")
public class Competition {
    @Id
    @NotNull
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Date period;
    @ManyToOne
    @JoinColumn(name = "main_sponsor", referencedColumnName = "id")
    private Sponsor sponsor;
    @ManyToOne
    @JoinColumn(name = "sport", referencedColumnName = "id")
    private Sport sport;
    @ManyToOne
    @JoinColumn(name = "location", referencedColumnName = "id")
    private SportsFacility sportsFacility;

    public Competition(String title, Date period, Sponsor sponsor, Sport sport, SportsFacility sportsFacility) {
        this.period = period;
        this.title = title;
        this.sponsor = sponsor;
        this.sport = sport;
        this.sportsFacility = sportsFacility;
    }
}
