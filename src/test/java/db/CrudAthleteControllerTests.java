package db;

import db.api.rest_controllers.CrudAthleteController;
import db.entities.Athlete;
import db.entities.SportClub;
import db.repository.AthleteRepository;
import db.repository.SportClubRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = DbSportApplication.class, initializers = TestInitializer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrudAthleteControllerTests {
    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private SportClubRepository sportClubRepository;
    @Autowired
    private CrudAthleteController crudAthleteController;

    @BeforeEach
    void setUp() {
        athleteRepository.deleteAll();
        sportClubRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testAddAthleteShouldAddRightValue() {
        SportClub sportClub = new SportClub("Dynamo");
        sportClubRepository.save(sportClub);
        crudAthleteController.addAthlete("Ivan", "Ivanovich", "Ivanov",
                sportClub.getTitle());
        List<Athlete> athletes = new ArrayList<>();

        Athlete athl = new Athlete();
        athl.setId(1L);
        athl.setFirstName("Ivan");
        athl.setLastName("Ivanov");
        athl.setPatronymic("Ivanovich");
        athl.setClub(sportClub);

        athletes.add(athl);

        assertEquals(athletes, athleteRepository.findAll());
    }

    @Test
    @Order(2)
    public void testUpdateAthleteShouldUpdateName() {
        SportClub sportClub = new SportClub("Dynamo");
        sportClubRepository.save(sportClub);
        Athlete savedAthlete = new Athlete("Ivan", "Ivanovich", "Ivanov", sportClub);
        athleteRepository.save(savedAthlete);
        Athlete newAthlete = athleteRepository.findById(savedAthlete.getId()).orElseThrow();
        crudAthleteController.updateAthlete(savedAthlete.getId(), "Pavel", "", "", sportClub.getTitle());

        List<Athlete> athletes = new ArrayList<>();

        Athlete athl = new Athlete();
        athl.setId(newAthlete.getId());
        athl.setFirstName("Pavel");
        athl.setLastName("Ivanov");
        athl.setPatronymic("Ivanovich");
        athl.setClub(sportClub);

        athletes.add(athl);

        assertEquals(athletes, athleteRepository.findAll());
    }

    @Test
    @Order(3)
    public void testUpdateAthleteShouldUpdateAllAthlete() {
        SportClub sportClub = new SportClub("Arsenal");
        sportClubRepository.save(sportClub);
        Athlete savedAthlete = new Athlete("Ivan", "Ivanovich", "Ivanov", sportClub);
        athleteRepository.save(savedAthlete);
        Athlete newAthlete = athleteRepository.findById(savedAthlete.getId()).orElseThrow();
        crudAthleteController.updateAthlete(savedAthlete.getId(), "Pavel", "Pavlovich", "Pavlov",
                sportClub.getTitle());

        List<Athlete> athletes = new ArrayList<>();

        Athlete athl = new Athlete();
        athl.setId(newAthlete.getId());
        athl.setFirstName("Pavel");
        athl.setLastName("Pavlov");
        athl.setPatronymic("Pavlovich");
        athl.setClub(sportClub);

        athletes.add(athl);

        assertEquals(athletes, athleteRepository.findAll());
    }

    @Test
    @Order(4)
    public void testDeleteAthleteShouldDeleteRightValue() {
        SportClub sportClub = new SportClub("Dynamo");
        sportClubRepository.save(sportClub);
        Athlete savedAthlete = new Athlete("Ivan", "Ivanovich", "Ivanov", sportClub);
        athleteRepository.save(savedAthlete);
        Athlete newAthlete = athleteRepository.findById(savedAthlete.getId()).orElseThrow();
        crudAthleteController.deleteAthlete(newAthlete.getId());
        List<Athlete> athletes = new ArrayList<>();

        assertEquals(athletes, athleteRepository.findAll());
    }
}