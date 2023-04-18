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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = DbSportApplication.class, initializers = TestInitializer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PutRequestsTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowPuttingSportClubByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(put("/sportclub")
                .param("id", "1")
                .param("sport", "Dynamo")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowPuttingSportByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(put("/sport")
                .param("id", "1")
                .param("sport", "basketball")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowPuttingTrainerByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(put("/trainer")
                .param("id", "1")
                .param("firstName", "Pavel")
                .param("patronymic", "")
                .param("lastName", "Ivanov")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowPuttingSponsorByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(put("/sponsor")
                .param("id", "1")
                .param("name", "Petrov")
                .param("company", "")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelPuttingSportClubByUserShouldReturnFail() throws Exception {
        mockMvc.perform(put("/sportclub")
                .param("id", "1")
                .param("sport", "Dynamo")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelPuttingSportByUserShouldReturnFail() throws Exception {
        mockMvc.perform(put("/sport")
                .param("id", "1")
                .param("sport", "basketball")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelPuttingTrainerByUserShouldReturnFail() throws Exception {
        mockMvc.perform(put("/trainer")
                .param("id", "1")
                .param("firstName", "Pavel")
                .param("patronymic", "")
                .param("lastName", "Ivanov")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelPuttingSponsorByUserShouldReturnSuccess() throws Exception {
        mockMvc.perform(put("/sponsor")
                .param("id", "1")
                .param("name", "Petrov")
                .param("company", "")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }
}