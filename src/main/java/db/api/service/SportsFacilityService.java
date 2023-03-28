package db.api.service;

import db.entities.CourtSurface;
import db.entities.SportsFacilityType;
import db.entities.models.surface.*;
import db.entities.models.surface.SportsFacility;
import db.repository.sports.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import db.repository.sports.*;

@Service
public class SportsFacilityService {
    private final SportsFacilityRepository sportsFacilityRepository;
    private final ArenaRepository arenaRepository;
    private final CourtRepository courtRepository;
    private final GymRepository gymRepository;
    private final StadiumRepository stadiumRepository;

    @Autowired
    public SportsFacilityService(SportsFacilityRepository sportsFacilityRepository,
                                 ArenaRepository arenaRepository,
                                 CourtRepository courtRepository,
                                 StadiumRepository stadiumRepository,
                                 GymRepository gymRepository) {
        this.sportsFacilityRepository = sportsFacilityRepository;
        this.arenaRepository = arenaRepository;
        this.courtRepository = courtRepository;
        this.stadiumRepository = stadiumRepository;
        this.gymRepository = gymRepository;
    }

    public SportsFacility addSportsFacility(String address, SportsFacilityType type) {
        SportsFacility sportsFacility = new SportsFacility(address, type);
        sportsFacilityRepository.save(sportsFacility);
        return sportsFacility;
    }

    public SportsFacility deleteSportsFacility(Long facilityId) {
        SportsFacility sportsFacility = sportsFacilityRepository.getSportsFacilityById(facilityId);
        sportsFacilityRepository.delete(sportsFacility);
        return sportsFacility;
    }

    public Arena addArenaParam(String param, SportsFacility sportsFacility) {
        Arena arena = new Arena(param, sportsFacility);
        arenaRepository.save(arena);
        return arena;
    }

    public Arena deleteArenaParam(Long arenaId) {
        Arena arena = arenaRepository.getArenaById(arenaId);
        arenaRepository.delete(arena);
        return arena;
    }

    public Court addCourtParam(CourtSurface surface, SportsFacility sportsFacility) {
        Court court = new Court(surface, sportsFacility);
        courtRepository.save(court);
        return court;
    }

    public Court deleteCourtParam(Long courtId) {
        Court court = courtRepository.getCourtById(courtId);
        courtRepository.delete(court);
        return court;
    }

    public Gym addGymParam(String param, SportsFacility sportsFacility) {
        Gym gym = new Gym(param, sportsFacility);
        gymRepository.save(gym);
        return gym;
    }

    public Gym deleteGymParam(Long gymId) {
        Gym gym = gymRepository.getGymById(gymId);
        gymRepository.delete(gym);
        return gym;
    }

    public Stadium addStadiumParam(String param, SportsFacility sportsFacility) {
        Stadium stadium = new Stadium(param, sportsFacility);
        stadiumRepository.save(stadium);
        return stadium;
    }

    public Stadium deleteStadiumParam(Long stadiumId) {
        Stadium stadium = stadiumRepository.getStadiumById(stadiumId);
        stadiumRepository.delete(stadium);
        return stadium;
    }
}