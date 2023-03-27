package db.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/main")
@AllArgsConstructor
public class MainPageController {
    @GetMapping("")
    public String getAll() {
        return "main";
    }
}
