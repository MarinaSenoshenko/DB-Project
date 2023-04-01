package db.api.rest_controllers;

import db.api.service.SportClubService;
import db.entities.SportClub;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/sportclub")
public class EditSportClubController {
    private final SportClubService sportClubService;

    @PostMapping("")
    public SportClub addSportClub(@RequestParam("sport") String value) {
        return sportClubService.addSportClub(value);
    }

    @DeleteMapping("")
    public SportClub deleteSportClub(@RequestParam("sport") Long sportClubId) {
        return sportClubService.deleteSportClub(sportClubId);
    }

    @PutMapping("")
    public SportClub updateSportClub(@RequestParam("id") Long id, @RequestParam("sport") String value) {
        return sportClubService.updateSportClub(id, value);
    }
}