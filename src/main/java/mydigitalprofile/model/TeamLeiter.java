package mydigitalprofile.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a TeamLeiter entity in the application.
 *
 * @author Khaled Alnahhas
 */
@Entity
public class TeamLeiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamLeiterID;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "teamleiter")
    private Set<Mitarbeiter> mitarbeiter;

    // Getters and setters

    public Long getTeamLeiterID() {
        return teamLeiterID;
    }

    public void setTeamLeiterID(Long teamLeiterID) {
        this.teamLeiterID = teamLeiterID;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Set<Mitarbeiter> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    // equals(), hashCode(), toString()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamLeiter that = (TeamLeiter) o;

        return teamLeiterID != null ? teamLeiterID.equals(that.teamLeiterID) : that.teamLeiterID == null;
    }

    @Override
    public int hashCode() {
        return teamLeiterID != null ? teamLeiterID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TeamLeiter{" +
                "teamLeiterID=" + teamLeiterID +
                ", team=" + team +
                ", mitarbeiter=" + mitarbeiter +
                '}';
    }
}