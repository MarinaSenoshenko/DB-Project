package db.controllers;

import db.entities.Competition;
import db.repository.CompetitionRepository;
import db.repository.SponsorRepository;
import db.repository.SportRepository;
import db.repository.SportsFacilityTypeRepository;
import db.repository.sports.SportsFacilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.*;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/main/competition")
@AllArgsConstructor
public class CompetitionController {
    private CompetitionRepository competitionRepository;
    private SponsorRepository sponsorRepository;
    private SportRepository sportRepository;
    private SportsFacilityRepository sportsFacilityRepository;
    private SportsFacilityTypeRepository sportsFacilityTypeRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("competitions", competitionRepository.findAll());
        addAttributesToModel(model);
        return "/pages/competition";
    }

    @GetMapping("/add")
    public String addCompetition(Model model) {
        addAttributesToModel(model);
        model.addAttribute("sponsors", sponsorRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        model.addAttribute("sports_facilitys", sportsFacilityRepository.findAll());
        return "/post/add_competition";
    }

    @GetMapping("/delete")
    public String deleteCompetition(Model model) {
        model.addAttribute("competitions", competitionRepository.getNotUsedInOtherTableCompetitionsId());
        return "/delete/delete_competition";
    }

    @GetMapping(value = {"/byperiod/{startdate}/{enddate}/{sponsorid}", "/byperiod/{startdate}/{enddate}"})
    public String getCompetitionByPeriod(@PathVariable("startdate") String startDate, @PathVariable("enddate")
    String endDate, @PathVariable("sponsorid") Optional<Long> sponsorId, Model model) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Competition> competitionsRes = competitionRepository.getCompetitionByPeriod(dateFormat.parse(startDate), dateFormat.parse(endDate));
        List<Competition> competitionsList = sponsorId.map(aLong -> competitionsRes.stream().filter(competition ->
                        competition.getSponsor().getId().equals(aLong)).collect(Collectors.toList())).orElse(competitionsRes);
        model.addAttribute("competitions", competitionsList);
        addAttributesToModel(model);
        return "/pages/competition";
    }

    @GetMapping(value = {"/byfacility/{facilityid}", "/byfacility/{facilityid}/{sport}"})
    public String getCompetitionByFacility(@PathVariable("facilityid") Long sportsFacilityId, @PathVariable("sport")
    Optional<String> sportValue, Model model) {
        List<Competition> competitionsRes = competitionRepository.getCompetitionByFacility(sportsFacilityId);
        List<Competition> competitionsList = sportValue.map(s -> competitionsRes.stream().filter(competition ->
                        competition.getSport().getValue().equals(s)).collect(Collectors.toList())).orElse(competitionsRes);
        model.addAttribute("competitions", competitionsList);
        addAttributesToModel(model);
        return "/pages/competition";
    }

    private void addAttributesToModel(Model model) {
        model.addAttribute("sponsors", sponsorRepository.findAll());
        model.addAttribute("sports", sportRepository.findAll());
        model.addAttribute("facilitys", sportsFacilityTypeRepository.findAll());
        model.addAttribute("allcompetitions", competitionRepository.findAll());
    }
}
