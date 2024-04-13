package mydigitalprofile.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents a Projekt entity in the application.
 *
 * @author KhaledAlnahhas1
 */
@Entity
public class Projekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projektID;

    @Column
    private String projektName;

    @Column
    private String volumina;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String beschreibung;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // Getters and setters

    public Long getProjektID() {
        return projektID;
    }

    public void setProjektID(Long projektID) {
        this.projektID = projektID;
    }

    public String getProjektName() {
        return projektName;
    }

    public void setProjektName(String projektName) {
        this.projektName = projektName;
    }

    public String getVolumina() {
        return volumina;
    }

    public void setVolumina(String volumina) {
        this.volumina = volumina;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // equals(), hashCode(), toString()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projekt projekt = (Projekt) o;

        return projektID != null ? projektID.equals(projekt.projektID) : projekt.projektID == null;
    }

    @Override
    public int hashCode() {
        return projektID != null ? projektID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Projekt{" +
                "projektID=" + projektID +
                ", projektName='" + projektName + '\'' +
                ", volumina='" + volumina + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", beschreibung='" + beschreibung + '\'' +
                ", team=" + team +
                '}';
    }
}