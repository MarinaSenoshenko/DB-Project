package db.api.service.user;

import db.entities.*;
import db.entities.user.*;
import db.repository.*;
import db.repository.user.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
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
        User user = new User(name, password, roles);
        userRepository.save(user);
        return user;
    }

    public User addAthlete(Long id, String name, String firstName, String lastName, String patronymic, String password) {
        Role role = roleRepository.findById(4L).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Athlete athlete = athleteRepository.findById(id).orElseThrow();
        return compareUserInfo(id, name, firstName, lastName, patronymic, password, roles,
                athlete.getPatronymic(), athlete.getFirstName(), athlete.getLastName(), athlete.getId());
    }

    public User addTrainer(Long id, String name, String firstName, String lastName, String patronymic, String password) {
        Role role = roleRepository.findById(3L).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Trainer trainer = trainerRepository.findById(id).orElseThrow();
        return compareUserInfo(id, name, firstName, lastName, patronymic, password, roles,
                trainer.getPatronymic(), trainer.getFirstName(), trainer.getLastName(), trainer.getId());
    }

    private User compareUserInfo(Long id, String name, String firstName, String lastName, String patronymic, String password,
                                 Set<Role> roles, String patronymicReal, String firstNameReal, String lastNameReal, Long idReal) {
        if (Objects.equals(patronymic, patronymicReal) &&
                Objects.equals(firstName, firstNameReal) &&
                Objects.equals(lastName, lastNameReal) &&
                id.equals(idReal)) {
            User user = new User(name, password, roles);
            userRepository.save(user);
            return user;
        }
        return null;
    }
}