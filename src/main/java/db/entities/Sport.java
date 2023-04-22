package db.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance (strategy = InheritanceType.JOINED)
@Component
@Table(name = "sport")
public class Sport {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;
    @NotNull
    @Column(unique = true)
    private String value;

    public Sport(String value) {
        this.value = value;
    }
}
