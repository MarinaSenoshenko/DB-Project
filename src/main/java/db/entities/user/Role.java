package db.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @NotNull
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private Long id;
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.ALL})
    private Set<User> users;
    @Override
    public String getAuthority() {
        return name;
    }
}
