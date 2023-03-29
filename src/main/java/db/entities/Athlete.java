package db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "athlete")
public class Athlete {
    @Id
    @NotNull
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    public Long id;
    @NotNull
    public String firstName;
    public String patronymic;
    public String lastName;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "club", referencedColumnName = "id")
    public SportClub club;

    public Athlete(String firstName, String patronymic, String lastName, SportClub club) {
        this.patronymic = patronymic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.club = club;
    }
}
