package db;

import db.api.rest_controllers.CrudSportController;
import db.entities.Sport;
import db.repository.SportRepository;
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
public class CrudSportControllerTests {
    @Autowired
    private SportRepository sportRepository;
    @Autowired
    private CrudSportController crudSportController;

    @BeforeEach
    void setUp() {
        sportRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testAddSportShouldAddRightValue() {
        crudSportController.addSport("football");

        List<Sport> sports = new ArrayList<>();

        Sport sport = new Sport();
        sport.setId(1L);
        sport.setValue("football");

        sports.add(sport);

        assertEquals(sports, sportRepository.findAll());
    }

    @Test
    @Order(2)
    public void testUpdateSportShouldUpdateRightValue() {
        sportRepository.save(new Sport("football"));
        Sport newSport = sportRepository.findByValue("football");
        crudSportController.updateSport(newSport.getId(), "basketball");

        List<Sport> sports = new ArrayList<>();

        Sport sport = new Sport();
        sport.setId(newSport.getId());
        sport.setValue("basketball");

        sports.add(sport);

        assertEquals(sports, sportRepository.findAll());
    }

    @Test
    @Order(3)
    public void testDeleteSportShouldDeleteRightValue() {
        sportRepository.save(new Sport("football"));
        Sport newSport = sportRepository.findByValue("football");
        crudSportController.deleteSport(newSport.getId());
        List<Sport> sports = new ArrayList<>();

        assertEquals(sports, sportRepository.findAll());
    }
}