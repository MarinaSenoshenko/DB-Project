package db.api.service.user;

import db.entities.*;
import db.entities.user.*;
import db.repository.*;
import db.repository.user.RoleRepository;
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
        Role role = roleRepository.findByName("USER");
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

    public User addAthlete(Long id, String name, String firstName, String lastName,
                           String patronymic, String password, int code) throws UsernameNotFoundException {
        Athlete athlete;
        Role role = roleRepository.findByName("ATHLETE");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        try {
            athlete = athleteRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("Incorrect data");
        }
        if (userRepository.findByUsername(name) != null) {
            throw new UsernameNotFoundException("User already exists");
        }
        else {
            return compareUserInfo(id, name, firstName, lastName, patronymic, password, roles, code,
                    athlete.getPatronymic(), athlete.getFirstName(), athlete.getLastName(), athlete.getId(), athlete.getCode());
        }
    }

    public User addTrainer(Long id, String name, String firstName, String lastName,
                           String patronymic, String password, int code) throws UsernameNotFoundException {
        Trainer trainer;
        Role role = roleRepository.findByName("TRAINER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        try {
            trainer = trainerRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("Incorrect data");
        }
        if (userRepository.findByUsername(name) != null) {
            throw new UsernameNotFoundException("User already exists");
        }
        else {
            return compareUserInfo(id, name, firstName, lastName, patronymic, password, roles, code,
                    trainer.getPatronymic(), trainer.getFirstName(), trainer.getLastName(), trainer.getId(), trainer.getCode());
        }
    }

    private User compareUserInfo(Long id, String name, String firstName, String lastName, String patronymic,
                                 String password, Set<Role> roles, int code, String patronymicReal, String firstNameReal,
                                 String lastNameReal, Long idReal, int codeReal) {
        if (Objects.equals(patronymic, patronymicReal) &&
                Objects.equals(firstName, firstNameReal) &&
                Objects.equals(lastName, lastNameReal) &&
                id.equals(idReal) && code == codeReal) {
            User user = new User(name, BCrypt.hashpw(password, BCrypt.gensalt()), roles);
            userRepository.save(user);
            return user;
        }
        return null;
    }
}