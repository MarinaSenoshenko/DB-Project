package db.controllers;

import db.entities.Competition;
import db.repository.CompetitionRepository;
import db.repository.SponsorRepository;
import db.repository.SportRepository;
import db.repository.SportsFacilityTypeRepository;
import db.repository.sports.SportsFacilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
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
        model.addAttribute("competitions", competitionRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        addAttributesToModel(model);
        return "/pages/competition";
    }

    @GetMapping("/add")
    public String addCompetition(Model model) {
        addSortedAttributes(model);
        return "/edit/post/add_competition";
    }

    @GetMapping("/update")
    public String updateCompetition(Model model) {
        addSortedAttributes(model);
        return "/edit/update/update_competition";
    }

    @GetMapping("/delete")
    public String deleteCompetition(Model model) {
        model.addAttribute("competitions", competitionRepository.getNotUsedInOtherTableCompetitionsId());
        return "/edit/delete/delete_competition";
    }

    @GetMapping(value = {"/byperiod/{startdate}/{enddate}/{sponsorid}", "/byperiod/{startdate}/{enddate}"})
    public String getCompetitionByPeriod(@PathVariable("startdate") String startDate,
                                         @PathVariable("enddate") String endDate,
                                         @PathVariable("sponsorid") Optional<Long> sponsorId,
                                         Model model, Authentication authentication) throws ParseException {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            List<Competition> competitionsRes = competitionRepository.getCompetitionByPeriod(dateFormat.parse(startDate), dateFormat.parse(endDate));
            List<Competition> competitionsList = sponsorId.map(aLong -> competitionsRes.stream().filter(competition ->
                    competition.getSponsor().getId().equals(aLong)).collect(Collectors.toList())).orElse(competitionsRes);
            model.addAttribute("competitions", competitionsList);
            addAttributesToModel(model);
            return "/pages/competition";
        }
        throw new AccessDeniedException("Access denied");
    }

    @GetMapping(value = {"/byfacility/{facilityid}", "/byfacility/{facilityid}/{sport}"})
    public String getCompetitionByFacility(@PathVariable("facilityid") Long sportsFacilityId,
                                           @PathVariable("sport") Optional<String> sportValue,
                                           Model model, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            List<Competition> competitionsRes = competitionRepository.getCompetitionByFacility(sportsFacilityId);
            List<Competition> competitionsList = sportValue.map(s -> competitionsRes.stream().filter(competition ->
                    competition.getSport().getValue().equals(s)).collect(Collectors.toList())).orElse(competitionsRes);
            model.addAttribute("competitions", competitionsList);
            addAttributesToModel(model);
            return "/pages/competition";
        }
        throw new AccessDeniedException("Access denied");
    }

    private void addAttributesToModel(Model model) {
        model.addAttribute("sponsors", sponsorRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("sports", sportRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("facilitys", sportsFacilityTypeRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("allcompetitions", competitionRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
    }

    private void addSortedAttributes(Model model) {
        addAttributesToModel(model);
        model.addAttribute("sponsors", sponsorRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("sports", sportRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
        model.addAttribute("sports_facilitys", sportsFacilityRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ));
    }
}
