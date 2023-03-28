package db.controllers;

import db.repository.SportsFacilityTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/sportsfacilitytype")
@AllArgsConstructor
public class SportsFacilityTypeController {
    private SportsFacilityTypeRepository sportsFacilityTypeRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("sportsfacilitytypes", sportsFacilityTypeRepository.findAll());
        return "/pages/sportsfacilitytype";
    }
}
