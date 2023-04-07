package db.entities;

import db.repository.SportClubRepository;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;


public class SportClubWithAthletes {
    Long athlts;
    SportClubRepository sportClubRepository;

}
