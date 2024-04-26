package mydigitalprofile.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Represents a Team entity in the application. Each team has a unique ID, a
 * project ID, a team leader, and a project.
 *
 * @author Khaled Alnahhas
 */
@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamID;

	@Column
	private String teamName;

	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "projekt_id")
	private Projekt projekt;

	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
	private Set<Mitarbeiter> mitarbeiters = new HashSet<>();

	/**
	 * 
	 */
	public Team() {
		super();
	}

	/**
	 * @param teamName
	 * @param projekt
	 * @param mitarbeiters
	 */
	public Team(String teamName, Projekt projekt, Set<Mitarbeiter> mitarbeiters) {
		super();
		this.teamName = teamName;
		this.projekt = projekt;
		this.mitarbeiters = mitarbeiters;
	}

	
	public void addMitarbeiter(Mitarbeiter mitarbeiter) {
		mitarbeiters.add(mitarbeiter);
		mitarbeiter.setTeam(this);
	}
	
	
	/**
	 * @return the teamID
	 */
	public Long getTeamID() {
		return teamID;
	}

	/**
	 * @param teamID the teamID to set
	 */
	public void setTeamID(Long teamID) {
		this.teamID = teamID;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the projekt
	 */
	public Projekt getProjekt() {
		return projekt;
	}

	/**
	 * @param projekt the projekt to set
	 */
	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}

	/**
	 * @return the mitarbeiters
	 */
	public Set<Mitarbeiter> getMitarbeiters() {
		return mitarbeiters;
	}

	/**
	 * @param mitarbeiters the mitarbeiters to set
	 */
	public void setMitarbeiters(Set<Mitarbeiter> mitarbeiters) {
		this.mitarbeiters = mitarbeiters;
	}

}