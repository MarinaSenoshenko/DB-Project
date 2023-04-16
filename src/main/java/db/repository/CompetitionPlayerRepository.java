package db.repository;

import db.entities.CompetitionPlayer;
import db.entities.models.keys.CompetitionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
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

    @Query("SELECT cp FROM CompetitionPlayer cp WHERE (:result = -1L OR cp.result = :result) " +
            "AND (:club='all' OR cp.competitionKey.athlete.club.title = :club)" +
            "AND (:firstName='all' OR cp.competitionKey.athlete.firstName = :firstName)" +
            "AND (:lastName='all' OR cp.competitionKey.athlete.lastName = :lastName)" +
            "AND (:patronymic='all' OR cp.competitionKey.athlete.patronymic = :patronymic)" +
            "AND (:title='all' OR cp.competitionKey.competition.title = :title)" +
            "AND (:isWasAwarding='all' OR cp.wasAwarding = :wasAwarding)")
    Iterable<CompetitionPlayer> getFiltered(@Param("result") Long result, @Param("club") String club,
                                            @Param("firstName") String firstName, @Param("lastName") String lastName,
                                            @Param("patronymic") String patronymic, @Param("title") String title,
                                            @Param("wasAwarding") boolean wasAwarding, @Param("isWasAwarding")
                                            String isWasAwarding);
}
