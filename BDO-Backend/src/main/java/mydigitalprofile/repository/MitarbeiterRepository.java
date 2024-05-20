package mydigitalprofile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mydigitalprofile.model.Mitarbeiter;
import mydigitalprofile.model.dto.MitarbeiterDto;

@Repository
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Long> {


    public Mitarbeiter findByUsername(String username);


    @Query(value = "SELECT new mydigitalprofile.model.dto.MitarbeiterDto( "
            + "m.pnr, m.vorname, m.nachname, m.username, "
            + "a.strasse, a.hausNr, a.plz, a.ort, m.standort, m.rolle, m.karriereLevel) "
            + "FROM Mitarbeiter m "
            + "JOIN m.address a "
            + "WHERE m.username=:username")
    public MitarbeiterDto findDtoByUsername(@Param(value = "username") String username);

    @Query(value = "SELECT new mydigitalprofile.model.dto.MitarbeiterDto( "
            + "m.pnr, m.vorname, m.nachname, m.username, "
            + "a.strasse, a.hausNr, a.plz, a.ort, m.standort, m.rolle, m.karriereLevel) "
            + "FROM Mitarbeiter m "
            + "JOIN m.address a "
            + "WHERE m.maId=:id "
            + "AND m.username=:username")
    public MitarbeiterDto findByIdAndUsername(@Param(value = "username") String username, @Param(value = "id") long id);


    @Query(value = "SELECT new mydigitalprofile.model.dto.MitarbeiterDto( "
            + "m.pnr, m.vorname, m.nachname, m.username, "
            + "a.strasse, a.hausNr, a.plz, a.ort, m.standort, m.rolle, m.karriereLevel) "
            + "FROM Mitarbeiter m "
            + "JOIN m.address a")
    public List<MitarbeiterDto> findAllMitarbeiter();

}
