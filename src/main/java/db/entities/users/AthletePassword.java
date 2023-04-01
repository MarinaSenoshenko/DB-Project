package db.entities.users;

import db.entities.Athlete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "athlete_password")
public class AthletePassword {
    @Id
    @NotNull
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    public Long id;
    @ManyToOne
    @JoinColumn(name = "athlete_id", referencedColumnName = "id")
    private Athlete athlete;
    private String password;
    private String email;

    public AthletePassword(Athlete athlete, String password, String email) {
        this.athlete = athlete;
        this.email = email;
        this.password = password;
    }
}
