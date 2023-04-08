package db.entities.outer;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AthleteWithSports {
    private Integer id;
    private String firstName;
    private String patronymic;
    private String lastName;
    private String title;
    private String value;
}
