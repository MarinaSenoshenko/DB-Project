package db.entities.outer;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class SponsorWithCount {
    private Integer id;
    private String name;
    private String company;
    private BigInteger sponsorsCount;
}
