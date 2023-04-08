package db.entities;

import db.repository.SportClubRepository;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
public class SportClubWithAthletes {
    Integer id;
    String title;
    BigInteger athletesCount;

}
