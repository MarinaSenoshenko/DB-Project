package db.entities.outer;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
public class SportClubWithAthletes {
    private Integer id;
    private String title;
    private BigInteger athletesCount;
}
