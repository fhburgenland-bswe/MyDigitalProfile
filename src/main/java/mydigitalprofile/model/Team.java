package mydigitalprofile.model;

import javax.persistence.*;

/**
 * Represents a Team entity in the application.
 * Each team has a unique ID, a project ID, a team leader, and a project.
 *
 * @author Khaled Alnahhas
 */
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamID;

    @Column
    private Long projektID;

    @ManyToOne
    @JoinColumn(name = "teamleiter_id")
    private TeamLeiter teamleiter;

    @ManyToOne
    @JoinColumn(name = "projekt_id")
    private Projekt projekt;

    // Getters and setters

    public Long getTeamID() {
        return teamID;
    }

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public Long getProjektID() {
        return projektID;
    }

    public void setProjektID(Long projektID) {
        this.projektID = projektID;
    }

    public TeamLeiter getTeamleiter() {
        return teamleiter;
    }

    public void setTeamleiter(TeamLeiter teamleiter) {
        this.teamleiter = teamleiter;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    // equals(), hashCode(), toString()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return teamID != null ? teamID.equals(team.teamID) : team.teamID == null;
    }

    @Override
    public int hashCode() {
        return teamID != null ? teamID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamID=" + teamID +
                ", projektID=" + projektID +
                ", teamleiter=" + teamleiter +
                ", projekt=" + projekt +
                '}';
    }
}