package db.controllers;

import db.repository.CourtSurfaceRepository;
import db.repository.SportsFacilityTypeRepository;
import db.repository.sports.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.*;

@Controller
@RequestMapping("/main/sportsfacility")
@AllArgsConstructor
public class SportsFacilityController {
    private SportsFacilityRepository sportsFacilityRepository;
    private SportsFacilityTypeRepository sportsFacilityTypeRepository;
    private CourtRepository courtRepository;
    private GymRepository gymRepository;
    private StadiumRepository stadiumRepository;
    private ArenaRepository arenaRepository;
    private CourtSurfaceRepository courtSurfaceRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("sportsfacilitys", sportsFacilityRepository.findAll());
        return "sportsfacility";
    }

    @GetMapping("/add")
    public String addFacility(Model model) {
        model.addAttribute("sportsfacilitytypes", sportsFacilityTypeRepository.findAll());
        return "add_sports_facility";
    }

    @GetMapping("/delete")
    public String deleteFacility(Model model) {
        model.addAttribute("sportsfacilitys", sportsFacilityRepository.findAll());
        return "delete_sports_facility";
    }

    @GetMapping("/add/add_arena")
    public String addArenaParam() {
        return "add_arena";
    }

    @GetMapping("/add/add_court")
    public String addCourtParam(Model model) {
        model.addAttribute("surfaces", courtSurfaceRepository.findAll());
        return "add_court";
    }

    @GetMapping("/add/add_gym")
    public String addGymParam() {
        return "add_gym";
    }

    @GetMapping("/add/add_stadium")
    public String addStadiumParam() {
        return "add_stadium";
    }


    @GetMapping("/bycompetitionperiod/{startdate}/{enddate}")
    public String getSportsFacilityByCompetitionPeriod(@PathVariable("startdate") String startDate,
                                                       @PathVariable("enddate") String endDate, Model model) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("sportsfacilitys", sportsFacilityRepository
                .getSportsFacilityByCompetitionPeriod(dateFormat.parse(startDate), dateFormat.parse(endDate)));
        return "sportsfacility";
    }

    @GetMapping("/court")
    public String getAllCourts(Model model) {
        model.addAttribute("courts", courtRepository.findAll());
        model.addAttribute("allcourts", courtRepository.findAll());
        return "court";
    }

    @GetMapping("/court/{surface}")
    public String getCourtsBySurface(@PathVariable("surface") String surface, Model model) {
        model.addAttribute("courts", courtRepository.getCourtWithSurface(surface));
        model.addAttribute("allcourts", courtRepository.findAll());
        return "court";
    }

    @GetMapping("/gym")
    public String getAllGyms(Model model) {
        model.addAttribute("gyms", gymRepository.findAll());
        model.addAttribute("allgyms", gymRepository.findAll());
        return "gym";
    }

    @GetMapping("/gym/{floorArea}")
    public String getGymByFloorArea(@PathVariable("floorArea") Double floorArea, Model model) {
        model.addAttribute("gyms", gymRepository.getGymByFloorArea(floorArea));
        model.addAttribute("allgyms", gymRepository.findAll());
        return "gym";
    }

    @GetMapping("/stadium")
    public String getAllStadiums(Model model) {
        model.addAttribute("stadiums", stadiumRepository.findAll());
        model.addAttribute("allstadiums", stadiumRepository.findAll());
        return "stadium";
    }

    @GetMapping("/stadium/{capacity}")
    public String getStadiumByCapacity(@PathVariable("capacity") Long capacity, Model model) {
        model.addAttribute("stadiums", stadiumRepository.getStadiumByCapacity(capacity));
        model.addAttribute("allstadiums", stadiumRepository.findAll());
        return "stadium";
    }

    @GetMapping("/arena")
    public String getAllArenas(Model model) {
        model.addAttribute("arenas", arenaRepository.findAll());
        model.addAttribute("allarenas", arenaRepository.findAll());
        return "arena";
    }

    @GetMapping("/arena/{trackNumber}")
    public String getArenaByTrackNumber(@PathVariable("trackNumber") Long trackNumber, Model model) {
        model.addAttribute("arenas", arenaRepository.getArenaByTrackNumber(trackNumber));
        model.addAttribute("allarenas", arenaRepository.findAll());
        return "arena";
    }
}
