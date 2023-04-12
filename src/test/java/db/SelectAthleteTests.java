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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = DbSportApplication.class, initializers = TestInitializer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class SelectAthleteTests {
    @Autowired
    private SportRepository sportRepository;
    @Autowired
    private AthleteRepository athleteRepository;

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

    @Test
    @Sql(scripts = {"classpath:insert.sql"})
    public void testGetAthletesWhoWinnerByCompetitionShouldReturnCorrectAthletes() {
        Iterable<Athlete> athletes = athleteRepository.getAthletesWhoWinnerByCompetition(1L);

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
        athl2.setId(3L);
        athl2.setFirstName("Aleksandr");
        athl2.setLastName("Aleksandrov");
        athl2.setPatronymic("Aleksandrovich");
        athl2.setClub(sportClub);

        athls.add(athl2);

        assertEquals(athletes, athls);
    }

    @Test
    @Sql(scripts = {"classpath:insert.sql"})
    public void testGetAthletesWhoNotInCompetitionByPeriodShouldReturnCorrectAthletes() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Iterable<Athlete> athletes = athleteRepository.getAthletesWhoNotInCompetitionByPeriod(
                dateFormat.parse("01-10-2023"), dateFormat.parse("03-11-2023"));
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

        Athlete athl3 = new Athlete();
        athl3.setId(3L);
        athl3.setFirstName("Aleksandr");
        athl3.setLastName("Aleksandrov");
        athl3.setPatronymic("Aleksandrovich");
        athl3.setClub(sportClub);

        athls.add(athl3);

        assertEquals(athletes, athls);
    }

    @Test
    @Sql(scripts = {"classpath:insert.sql"})
    public void testGetAthletesByTrainerAndRankShouldReturnCorrectAthletes() {
        Iterable<Athlete> athletes = athleteRepository.getAthletesByTrainerAndRank(2L, 8L);
        SportClub sportClub = new SportClub(1L, "Siberia");

        List<Athlete> athls = new ArrayList<>();

        Athlete athl = new Athlete();
        athl.setId(2L);
        athl.setFirstName("Petr");
        athl.setLastName("Petrov");
        athl.setPatronymic("Petrovich");
        athl.setClub(sportClub);

        athls.add(athl);

        assertEquals(athletes, athls);
    }
}