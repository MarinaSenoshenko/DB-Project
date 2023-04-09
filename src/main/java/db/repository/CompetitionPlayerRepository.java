package db.repository;

import db.entities.CompetitionPlayer;
import db.entities.models.keys.CompetitionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionPlayerRepository extends JpaRepository<CompetitionPlayer, CompetitionKey> {
    @Query(name = "getCompetitionPlayerByAthleteAndCompetition", nativeQuery = true)
    CompetitionPlayer getCompetitionPlayerByAthleteAndCompetition(Long athleteId, Long competitionId);
    @Query(name = "getByFirstNameOrderByAscending", nativeQuery = true)
    Iterable<CompetitionPlayer> getByFirstNameOrderByAscending();
    @Query(name = "getByFirstNameOrderByDescending", nativeQuery = true)
    Iterable<CompetitionPlayer> getByFirstNameOrderByDescending();
    @Query(name = "getByLastNameOrderByAscending", nativeQuery = true)
    Iterable<CompetitionPlayer> getByLastNameOrderByAscending();
    @Query(name = "getByLastNameOrderByDescending", nativeQuery = true)
    Iterable<CompetitionPlayer> getByLastNameOrderByDescending();
    @Query(name = "getByPatronymicOrderByAscending", nativeQuery = true)
    Iterable<CompetitionPlayer> getByPatronymicOrderByAscending();
    @Query(name = "getByPatronymicOrderByDescending", nativeQuery = true)
    Iterable<CompetitionPlayer> getByPatronymicOrderByDescending();
    @Query(name = "getByCompetitionOrderByAscending", nativeQuery = true)
    Iterable<CompetitionPlayer> getByCompetitionOrderByAscending();
    @Query(name = "getByCompetitionOrderByDescending", nativeQuery = true)
    Iterable<CompetitionPlayer> getByCompetitionOrderByDescending();
    @Query(name = "getByClubOrderByAscending", nativeQuery = true)
    Iterable<CompetitionPlayer> getByClubOrderByAscending();
    @Query(name = "getByClubOrderByDescending", nativeQuery = true)
    Iterable<CompetitionPlayer> getByClubOrderByDescending();
}
