package db;

import db.entities.user.*;
import db.repository.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = DbSportApplication.class, initializers = TestInitializer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = {"classpath:add-users.sql"})
    public void shouldAllowDeletingUserByAdminAndDeleteOnlyUser() throws Exception {
        mockMvc.perform(delete("/user")
                .param("user", "1")
                .with(user("test_admin").roles("ADMIN"))
                .with(csrf())
        ).andExpect(status().isOk());

        Optional<User> user = userRepository.findById(1L);
        assertEquals(Optional.empty(), user);

        User admin = userRepository.findById(2L).orElseThrow();
        assertEquals("test_admin", admin.getUsername());
    }

    @Test
    @Sql(scripts = {"classpath:add-users.sql"})
    public void testIfNotAuthorizedShouldNotGetPage() throws Exception {
        mockMvc.perform(get("/main/athlete"))
                .andExpect(status().is(302))
                .andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:add-users.sql"})
    public void testFindUserByIdShouldReturnRightUser() {
        User user = userRepository.findById(1L).orElseThrow();
        assertEquals("test_user", user.getUsername());
    }

    @Test
    @Sql(scripts = {"classpath:add-users.sql"})
    public void testFindUserRoleReturnRoleUser() {
        User user = userRepository.findById(1L).orElseThrow();
        Role userRole = (Role)user.getRoles().toArray()[0];
        assertEquals("USER", userRole.getName());
    }

    @Test
    @Sql(scripts = {"classpath:add-users.sql"})
    public void testFindUserRoleReturnRoleAdmin() {
        User user = userRepository.findById(2L).orElseThrow();
        Role userRole = (Role)user.getRoles().toArray()[0];
        assertEquals("ADMIN", userRole.getName());
    }

    @Test
    @Sql(scripts = {"classpath:add-users.sql"})
    public void testFindUserByIdShouldReturnNoUser() {
        Optional<User> user = userRepository.findById(3L);
        assertEquals(Optional.empty(), user);
    }
}