package db.api.rest_controllers;

import db.api.service.user.CustomUserDetailsServiceImpl;
import db.entities.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class CrudUserController {
    private final CustomUserDetailsServiceImpl customUserDetailsService;

    @DeleteMapping("")
    public User deleteUser(@RequestParam("user") Long userId, Authentication authentication) {
        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
        return customUserDetailsService.deleteUser(userId);
    }

    @PostMapping("/user")
    public User addUser(@RequestParam("login") String login, @RequestParam("password") String password) {
        return customUserDetailsService.addUser(login, password);
    }

    @PostMapping("/athlete")
    public User addAthlete(@RequestParam("id") Long id, @RequestParam("login") String login,
                        @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                        @RequestParam("patronymic") String patronymic, @RequestParam("password") String password,
                           @RequestParam("code") int code) {
        return customUserDetailsService.addAthlete(id, login, firstName, lastName, patronymic, password, code);
    }

    @PostMapping("/trainer")
    public User addTrainer(@RequestParam("id") Long id, @RequestParam("login") String login,
                        @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                        @RequestParam("patronymic") String patronymic, @RequestParam("password") String password,
                           @RequestParam("code") int code) {
        return customUserDetailsService.addTrainer(id, login, firstName, lastName, patronymic, password, code);
    }
}
