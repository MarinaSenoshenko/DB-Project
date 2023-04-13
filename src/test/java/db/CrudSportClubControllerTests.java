package db;

import db.api.rest_controllers.CrudSportClubController;
import db.entities.SportClub;
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
public class CrudSportClubControllerTests {
    @Autowired
    private SportClubRepository sportClubRepository;
    @Autowired
    private CrudSportClubController crudSportClubController;

    @BeforeEach
    void setUp() {
        sportClubRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testAddSportClubShouldAddRightValue() {
        crudSportClubController.addSportClub("Dynamo");

        List<SportClub> sportClubs = new ArrayList<>();

        SportClub sportClub = new SportClub();
        sportClub.setId(1L);
        sportClub.setTitle("Dynamo");

        sportClubs.add(sportClub);

        assertEquals(sportClubs, sportClubRepository.findAll());
    }

    @Test
    @Order(2)
    public void testUpdateSportClubShouldUpdateRightValue() {
        SportClub savedSportClub = new SportClub("Dynamo");
        sportClubRepository.save(savedSportClub);
        SportClub newSportClub = sportClubRepository.findById(savedSportClub.getId()).orElseThrow();
        crudSportClubController.updateSportClub(newSportClub.getId(), "Arsenal");

        List<SportClub> sportClubs = new ArrayList<>();

        SportClub sportClub = new SportClub();
        sportClub.setId(newSportClub.getId());
        sportClub.setTitle("Arsenal");

        sportClubs.add(sportClub);

        assertEquals(sportClubs, sportClubRepository.findAll());
    }

    @Test
    @Order(3)
    public void testDeleteSportClubShouldDeleteRightValue() {
        SportClub savedSportClub = new SportClub("Dynamo");
        sportClubRepository.save(savedSportClub);
        SportClub newSportClub = sportClubRepository.findById(savedSportClub.getId()).orElseThrow();
        crudSportClubController.deleteSportClub(newSportClub.getId());
        List<SportClub> sportClubs = new ArrayList<>();

        assertEquals(sportClubs, sportClubRepository.findAll());
    }
}