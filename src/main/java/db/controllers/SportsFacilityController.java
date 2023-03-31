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
        return "/pages/facility/sportsfacility";
    }

    @GetMapping("/add")
    public String addFacility(Model model) {
        model.addAttribute("sportsfacilitytypes", sportsFacilityTypeRepository.findAll());
        return "/post/facility/add_sports_facility";
    }

    @GetMapping("/update")
    public String updateFacility(Model model) {
        model.addAttribute("sportsfacilitys", sportsFacilityRepository.findAll());
        model.addAttribute("sportsfacilitytypes", sportsFacilityTypeRepository.findAll());
        return "/update/facility/update_sports_facility";
    }

    @GetMapping("/delete")
    public String deleteFacility(Model model) {
        model.addAttribute("sportsfacilitys", sportsFacilityRepository.getNotUsedInArenaSportsFacilityId());
        return "/delete/facility/delete_sports_facility";
    }

    @GetMapping("/add/add_arena")
    public String addArenaParam() {
        return "/post/facility/add_arena";
    }

    @GetMapping("/update/update_arena")
    public String updateArenaParam(Model model) {
        model.addAttribute("arenas", arenaRepository.findAll());
        return "/update/facility/update_arena";
    }

    @GetMapping("/delete/delete_arena")
    public String deleteArenaParam(Model model) {
        model.addAttribute("arenas", arenaRepository.findAll());
        return "/delete/facility/delete_arena";
    }

    @GetMapping("/add/add_court")
    public String addCourtParam(Model model) {
        model.addAttribute("surfaces", courtSurfaceRepository.findAll());
        return "/post/facility/add_court";
    }

    @GetMapping("/update/update_court")
    public String updateCourtParam(Model model) {
        model.addAttribute("courts", courtRepository.findAll());
        model.addAttribute("surfaces", courtSurfaceRepository.findAll());
        return "/update/facility/update_court";
    }

    @GetMapping("/delete/delete_court")
    public String deleteCourtParam(Model model) {
        model.addAttribute("courts", courtRepository.findAll());
        return "/delete/facility/delete_court";
    }

    @GetMapping("/add/add_gym")
    public String addGymParam() {
        return "/post/facility/add_gym";
    }

    @GetMapping("/update/update_gym")
    public String updateGymParam(Model model) {
        model.addAttribute("gyms", gymRepository.findAll());
        return "/update/facility/update_gym";
    }

    @GetMapping("/delete/delete_gym")
    public String deleteGymParam(Model model) {
        model.addAttribute("gyms", gymRepository.findAll());
        return "/delete/facility/delete_gym";
    }

    @GetMapping("/add/add_stadium")
    public String addStadiumParam() {
        return "/post/facility/add_stadium";
    }

    @GetMapping("/update/update_stadium")
    public String updateStadiumParam(Model model) {
        model.addAttribute("stadiums", stadiumRepository.findAll());
        return "/update/facility/update_stadium";
    }

    @GetMapping("/delete/delete_stadium")
    public String deleteStadiumParam(Model model) {
        model.addAttribute("stadiums", stadiumRepository.findAll());
        return "/delete/facility/delete_stadium";
    }

    @GetMapping("/bycompetitionperiod/{startdate}/{enddate}")
    public String getSportsFacilityByCompetitionPeriod(@PathVariable("startdate") String startDate,
                                                       @PathVariable("enddate") String endDate, Model model) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("sportsfacilitys", sportsFacilityRepository
                .getSportsFacilityByCompetitionPeriod(dateFormat.parse(startDate), dateFormat.parse(endDate)));
        return "/pages/facility/sportsfacility";
    }

    @GetMapping("/court")
    public String getAllCourts(Model model) {
        model.addAttribute("courts", courtRepository.findAll());
        model.addAttribute("allcourts", courtRepository.findAll());
        return "/pages/facility/court";
    }

    @GetMapping("/court/{surface}")
    public String getCourtsBySurface(@PathVariable("surface") String surface, Model model) {
        model.addAttribute("courts", courtRepository.getCourtWithSurface(surface));
        model.addAttribute("allcourts", courtRepository.findAll());
        return "/pages/facility/court";
    }

    @GetMapping("/gym")
    public String getAllGyms(Model model) {
        model.addAttribute("gyms", gymRepository.findAll());
        model.addAttribute("allgyms", gymRepository.findAll());
        return "/pages/facility/gym";
    }

    @GetMapping("/gym/{floorArea}")
    public String getGymByFloorArea(@PathVariable("floorArea") Double floorArea, Model model) {
        model.addAttribute("gyms", gymRepository.getGymByFloorArea(floorArea));
        model.addAttribute("allgyms", gymRepository.findAll());
        return "/pages/facility/gym";
    }

    @GetMapping("/stadium")
    public String getAllStadiums(Model model) {
        model.addAttribute("stadiums", stadiumRepository.findAll());
        model.addAttribute("allstadiums", stadiumRepository.findAll());
        return "/pages/facility/stadium";
    }

    @GetMapping("/stadium/{capacity}")
    public String getStadiumByCapacity(@PathVariable("capacity") Long capacity, Model model) {
        model.addAttribute("stadiums", stadiumRepository.getStadiumByCapacity(capacity));
        model.addAttribute("allstadiums", stadiumRepository.findAll());
        return "/pages/facility/stadium";
    }

    @GetMapping("/arena")
    public String getAllArenas(Model model) {
        model.addAttribute("arenas", arenaRepository.findAll());
        model.addAttribute("allarenas", arenaRepository.findAll());
        return "/pages/facility/arena";
    }

    @GetMapping("/arena/{trackNumber}")
    public String getArenaByTrackNumber(@PathVariable("trackNumber") Long trackNumber, Model model) {
        model.addAttribute("arenas", arenaRepository.getArenaByTrackNumber(trackNumber));
        model.addAttribute("allarenas", arenaRepository.findAll());
        return "/pages/facility/arena";
    }
}
