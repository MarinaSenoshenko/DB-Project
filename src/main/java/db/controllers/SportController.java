package db.controllers;

import db.repository.SportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/sport")
@AllArgsConstructor
public class SportController {
    private SportRepository sportRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("sports", sportRepository.findAll());
        return "sport";
    }

    @GetMapping("/add")
    public String addRank() {
        return "add_sport";
    }
}
