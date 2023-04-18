package db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
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
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowAddingSportClubSByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/sportclub")
                .param("sport", "Siberia")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowAddingSportByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/sport")
                .param("sport", "football")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowAddingTrainerByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/trainer")
                .param("firstName", "Ivan")
                .param("patronymic", "Ivanovich")
                .param("lastName", "Ivanov")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowAddingSponsorByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/sponsor")
                .param("name", "Ivanov")
                .param("company", "Google")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelAddingSportClubByUserShouldReturnFail() throws Exception {
        mockMvc.perform(post("/sportclub")
                .param("sport", "Siberia")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelAddingSportByUserShouldReturnFail() throws Exception {
        mockMvc.perform(post("/sport")
                .param("sport", "football")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelAddingTrainerByUserShouldReturnFail() throws Exception {
        mockMvc.perform(post("/trainer")
                .param("firstName", "Ivan")
                .param("patronymic", "Ivanovich")
                .param("lastName", "Ivanov")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelAddingSponsorByUserShouldReturnFail() throws Exception {
        mockMvc.perform(post("/sponsor")
                .param("name", "Ivanov")
                .param("company", "Google")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }
}