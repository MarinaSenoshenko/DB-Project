package db.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import db.repository.user.UserRepository;

@Controller
@RequestMapping("/main/users")
@AllArgsConstructor
public class UserController {
    private UserRepository userRepository;
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/pages/list_of_users";
    }

    @GetMapping("/delete")
    public String deleteUser(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/edit/delete/delete_user";
    }
}
