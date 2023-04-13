package db;

import db.api.rest_controllers.CrudSponsorController;
import db.entities.Sponsor;
import db.repository.SponsorRepository;
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
public class CrudSponsorControllerTests {
    @Autowired
    private SponsorRepository sponsorRepository;
    @Autowired
    private CrudSponsorController crudSponsorController;

    @BeforeEach
    void setUp() {
        sponsorRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testAddSponsorShouldAddRightValue() {
        crudSponsorController.addSponsor("Ivanov", "Google");

        List<Sponsor> sponsors = new ArrayList<>();

        Sponsor sponsor = new Sponsor();
        sponsor.setId(1L);
        sponsor.setName("Ivanov");
        sponsor.setCompany("Google");

        sponsors.add(sponsor);

        assertEquals(sponsors, sponsorRepository.findAll());
    }

    @Test
    @Order(2)
    public void testUpdateSponsorShouldUpdateName() {
        Sponsor savedSponsor = new Sponsor("Ivanov", "Google");
        sponsorRepository.save(savedSponsor);
        Sponsor newSponsor = sponsorRepository.findById(savedSponsor.getId()).orElseThrow();
        crudSponsorController.updateSponsor(newSponsor.getId(), "Petrov", "");

        List<Sponsor> sponsors = new ArrayList<>();

        Sponsor sponsor = new Sponsor();
        sponsor.setId(newSponsor.getId());
        sponsor.setName("Petrov");
        sponsor.setCompany("Google");

        sponsors.add(sponsor);

        assertEquals(sponsors, sponsorRepository.findAll());
    }

    @Test
    @Order(3)
    public void testUpdateSponsorShouldUpdateCompany() {
        Sponsor savedSponsor = new Sponsor("Ivanov", "Google");
        sponsorRepository.save(savedSponsor);
        Sponsor newSponsor = sponsorRepository.findById(savedSponsor.getId()).orElseThrow();
        crudSponsorController.updateSponsor(newSponsor.getId(), "", "Microsoft");

        List<Sponsor> sponsors = new ArrayList<>();

        Sponsor sponsor = new Sponsor();
        sponsor.setId(newSponsor.getId());
        sponsor.setName("Ivanov");
        sponsor.setCompany("Microsoft");

        sponsors.add(sponsor);

        assertEquals(sponsors, sponsorRepository.findAll());
    }

    @Test
    @Order(4)
    public void testUpdateSponsorShouldUpdateNameAndCompany() {
        Sponsor savedSponsor = new Sponsor("Ivanov", "Google");
        sponsorRepository.save(savedSponsor);
        Sponsor newSponsor = sponsorRepository.findById(savedSponsor.getId()).orElseThrow();
        crudSponsorController.updateSponsor(newSponsor.getId(), "Petrov", "Microsoft");

        List<Sponsor> sponsors = new ArrayList<>();

        Sponsor sponsor = new Sponsor();
        sponsor.setId(newSponsor.getId());
        sponsor.setName("Petrov");
        sponsor.setCompany("Microsoft");

        sponsors.add(sponsor);

        assertEquals(sponsors, sponsorRepository.findAll());
    }

    @Test
    @Order(5)
    public void testDeleteSportShouldDeleteRightValue() {
        Sponsor savedSponsor = new Sponsor("Ivanov", "Google");
        sponsorRepository.save(savedSponsor);
        Sponsor newSponsor = sponsorRepository.findById(savedSponsor.getId()).orElseThrow();
        crudSponsorController.deleteSponsor(newSponsor.getId());
        List<Sponsor> sponsors = new ArrayList<>();

        assertEquals(sponsors, sponsorRepository.findAll());
    }
}