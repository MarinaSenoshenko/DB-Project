package db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = DbSportApplication.class, initializers = TestInitializer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PostRequestsTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAllowAddingSportClubShouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/sportclub")
                .param("sport", "Siberia")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldAllowAddingSportShouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/sport")
                .param("sport", "football")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldAllowAddingTrainerShouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/trainer")
                .param("firstName", "Ivan")
                .param("patronymic", "Ivanovich")
                .param("lastName", "Ivanov")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldAllowAddingSponsorShouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/sponsor")
                .param("name", "Ivanov")
                .param("company", "Google")
                .with(csrf())
        ).andExpect(status().isOk());
    }
}