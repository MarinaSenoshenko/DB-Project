package db.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Random;
import java.util.random.RandomGenerator;

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
    @NotNull
    private int code;

    public Trainer(String firstName, String patronymic, String lastName) {
        this.patronymic = patronymic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = 10000000 + new Random().nextInt(90000000);
    }
}
