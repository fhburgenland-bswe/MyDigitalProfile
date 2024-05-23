package mydigitalprofile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mydigitalprofile.model.Team;
import mydigitalprofile.model.dto.MitarbeiterDto;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    public Team findByTeamName(String teamName);


    @Query(value = "SELECT t.teamID FROM Team t "
            + "JOIN t.mitarbeiters m "
            + "WHERE m.username = :username")
    public Long findTeamIdByUsername(@Param(value = "username") String username);


    @Query(value = "SELECT new mydigitalprofile.model.dto.MitarbeiterDto( "
            + "m.pnr, m.vorname, m.nachname, m.username, "
            + "a.strasse, a.hausNr, a.plz, a.ort, m.standort, m.rolle, m.karriereLevel) "
            + "FROM Mitarbeiter m "
            + "JOIN m.address a "
            + "JOIN m.team t "
            + "WHERE t.teamID = :id")
    public List<MitarbeiterDto> findAllMitarbeiterByTeamId(@Param(value = "id") Long id);



}