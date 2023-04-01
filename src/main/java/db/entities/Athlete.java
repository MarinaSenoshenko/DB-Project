package db.entities;

import lombok.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private Long id;
    @NotNull
    private String firstName;
    private String patronymic;
    private String lastName;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "club", referencedColumnName = "id")
    private SportClub club;

    public Athlete(String firstName, String patronymic, String lastName, SportClub club) {
        this.patronymic = patronymic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.club = club;
    }
}
