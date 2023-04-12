package db;

import db.entities.Athlete;
import db.entities.Sport;
import db.entities.SportClub;
import db.entities.outer.AthleteWithSports;
import db.repository.AthleteRepository;
import db.repository.SportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = DbSportApplication.class, initializers = TestInitializer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class GetTests {
    @Autowired
    SportRepository sportRepository;
    @Autowired
    AthleteRepository athleteRepository;

    @Test
    @Sql(scripts = {"classpath:insert.sql"})
    public void testGetAthletesByRankingShouldReturnCorrectAthletes() {
        Sport sport = sportRepository.findById(2L).orElseThrow();
        Iterable<Athlete> athletes = athleteRepository.getAthletesByRanking(sport.getValue(), 8L);

        List<Athlete> athls = new ArrayList<>();
        SportClub sportClub = new SportClub(1L, "Siberia");

        Athlete athl1 = new Athlete();
        athl1.setId(1L);
        athl1.setFirstName("Ivan");
        athl1.setLastName("Ivanov");
        athl1.setPatronymic("Ivanovich");
        athl1.setClub(sportClub);

        athls.add(athl1);

        Athlete athl2 = new Athlete();
        athl2.setId(2L);
        athl2.setFirstName("Petr");
        athl2.setLastName("Petrov");
        athl2.setPatronymic("Petrovich");
        athl2.setClub(sportClub);

        athls.add(athl2);

        assertEquals(athletes, athls);
    }

    @Test
    @Sql(scripts = {"classpath:insert.sql"})
    public void testGetAthletesWhoMoreThanOneSportShouldReturnCorrectAthletes() {
        Iterable<?> results = athleteRepository.getAthletesWhoMoreThanOneSport();
        boolean isFirstValueFound = false, isSecondValueFound = false;

        for (Object result : results) {
            Object[] row = (Object[]) result;

            AthleteWithSports athlete = new AthleteWithSports();
            athlete.setId((Integer) row[0]);
            athlete.setFirstName((String) row[1]);
            athlete.setPatronymic((String) row[2]);
            athlete.setLastName((String) row[3]);
            athlete.setTitle((String) row[4]);
            athlete.setValue((String) row[5]);

            switch (athlete.getValue()) {
                case "Swimming" -> isFirstValueFound = !isFirstValueFound;
                case "Hokey" -> isSecondValueFound = !isSecondValueFound;
                default -> assertEquals(0, 1);
            }
            assertEquals(athlete.getId(), 1);
            assertEquals(athlete.getFirstName(), "Ivan");
            assertEquals(athlete.getLastName(), "Ivanov");
            assertEquals(athlete.getPatronymic(), "Ivanovich");
            assertEquals(athlete.getTitle(), "Siberia");
        }
        assertTrue(isFirstValueFound);
        assertTrue(isSecondValueFound);
    }
}