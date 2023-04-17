package db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = DbSportApplication.class, initializers = TestInitializer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserRolesTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    public void shouldAllowDeletingUserShouldReturnSuccess() throws Exception {
        mockMvc.perform(delete("/user")
                .param("user", "1")
                .with(user("test_admin").roles("ADMIN"))
                .with(csrf())
        ).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void cannotGetCustomerIfNotAuthorized() throws Exception {
        mockMvc.perform(get("/main"))
                .andExpect(status().is(302));
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_admin")
    public void testGetPrivatePageWithAdminShouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/main/athlete/add"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_athlete")
    public void testGetPrivatePageWithAthleteShouldReturnFail() throws Exception {
        mockMvc.perform(get("/main/athlete/add"))
                .andExpect(status().is(403))
                .andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_user")
    public void testGetPrivatePageWithUserShouldReturnFail() throws Exception {
        mockMvc.perform(get("/main/athlete/add"))
                .andExpect(status().is(403))
                .andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    @WithUserDetails("test_user")
    public void testGetCommonPageWithUserShouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/main/athlete"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithAnonymousUser
    public void testIfNotAuthorizedShouldGetLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithAnonymousUser
    public void testIfNotAuthorizedShouldGetRegistrationPage() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:sql/add-users.sql"})
    public void shouldAllowRegistrationUserShouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/user/user")
                .param("id", "1")
                .param("login", "user_login")
                .param("firstName", "Ivan")
                .param("lastName", "Ivanov")
                .param("patronymic", "Ivanovich")
                .param("password", "123")
                .param("user", "user")
                .with(csrf())
        ).andExpect(status().isOk());
    }
}