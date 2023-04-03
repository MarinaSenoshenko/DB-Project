package db.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@AllArgsConstructor
public class MainPageController {
    @GetMapping("")
    public String getAll() {
        return "/pages/main";
    }

    @GetMapping("/main")
    public String toMainMenu() {
        return "/pages/main";
    }
}
