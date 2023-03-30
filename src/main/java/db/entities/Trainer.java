package db.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "trainer")
public class Trainer {
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

    public Trainer(String firstName, String patronymic, String lastName) {
        this.patronymic = patronymic;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
