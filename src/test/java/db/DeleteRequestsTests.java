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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = DbSportApplication.class, initializers = TestInitializer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class DeleteRequestsTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowDeletingSportClubByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(delete("/sportclub")
                .param("sport", "40")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowDeletingSportByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(delete("/sport")
                .param("sport", "40")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowDeletingTrainerByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(delete("/trainer")
                .param("trainer", "40")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_admin")
    public void shouldAllowDeletingSponsorByAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(delete("/sponsor")
                .param("sponsor", "40")
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelDeletingSportClubByUserShouldReturnFail() throws Exception {
        mockMvc.perform(delete("/sportclub")
                .param("sport", "40")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelDeletingSportByUserShouldReturnFail() throws Exception {
        mockMvc.perform(delete("/sport")
                .param("sport", "40")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelDeletingTrainerByUserShouldReturnFail() throws Exception {
        mockMvc.perform(delete("/trainer")
                .param("trainer", "40")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql", "classpath:sql/insert.sql"})
    @WithUserDetails("test_user")
    public void shouldCancelDeletingSponsorByUserShouldReturnFail() throws Exception {
        mockMvc.perform(delete("/sponsor")
                .param("sponsor", "40")
                .with(csrf())
        ).andExpect(status().isForbidden());
    }
}