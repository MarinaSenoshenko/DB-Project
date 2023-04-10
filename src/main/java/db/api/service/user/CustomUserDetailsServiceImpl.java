package db.api.service.user;

import db.entities.*;
import db.entities.user.*;
import db.repository.*;
import db.repository.user.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private final UserRepository userRepository;
    private final AthleteRepository athleteRepository;
    private final TrainerRepository trainerRepository;
    private final RoleRepository roleRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username)  {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Can't load user");
        }
        return new CustomUserDetails(user.getUsername(), user.getPassword(), user.getRoles());
    }

    public User deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        userRepository.delete(user);
        return user;
    }

    public User addUser(String name, String password) {
        Role role = roleRepository.findById(1L).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        if (userRepository.findByUsername(name) != null) {
            throw new UsernameNotFoundException("User already exists");
        }
        else {
            User user = new User(name, BCrypt.hashpw(password, BCrypt.gensalt()), roles);
            userRepository.save(user);
            return user;
        }
    }

    public User addAthlete(Long id, String name, String firstName, String lastName, String patronymic, String password) {
        Role role = roleRepository.findById(4L).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Athlete athlete = athleteRepository.findById(id).orElseThrow();
        if (userRepository.findByUsername(name) != null) {
            throw new UsernameNotFoundException("User already exists");
        }
        else {
            return compareUserInfo(id, name, firstName, lastName, patronymic, password, roles,
                    athlete.getPatronymic(), athlete.getFirstName(), athlete.getLastName(), athlete.getId());

        }
    }

    public User addTrainer(Long id, String name, String firstName, String lastName, String patronymic, String password) {
        Role role = roleRepository.findById(3L).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Trainer trainer = trainerRepository.findById(id).orElseThrow();
        if (userRepository.findByUsername(name) != null) {
            throw new UsernameNotFoundException("User already exists");
        }
        else {
            return compareUserInfo(id, name, firstName, lastName, patronymic, password, roles,
                    trainer.getPatronymic(), trainer.getFirstName(), trainer.getLastName(), trainer.getId());
        }
    }

    private User compareUserInfo(Long id, String name, String firstName, String lastName, String patronymic, String password,
                                 Set<Role> roles, String patronymicReal, String firstNameReal, String lastNameReal, Long idReal) {
        if (Objects.equals(patronymic, patronymicReal) &&
                Objects.equals(firstName, firstNameReal) &&
                Objects.equals(lastName, lastNameReal) &&
                id.equals(idReal)) {
            User user = new User(name, BCrypt.hashpw(password, BCrypt.gensalt()), roles);
            userRepository.save(user);
            return user;
        }
        return null;
    }
}