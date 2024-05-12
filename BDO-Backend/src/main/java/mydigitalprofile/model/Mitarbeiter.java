package mydigitalprofile.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Mitarbeiter entity in the application.
 *
 * @author Khaled Alnahhas
 */
@Entity
public class Mitarbeiter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long maId;

	@Column
	private String pnr;

	@Column
	private String vorname;

	@Column
	private String nachname;

	@Column(unique = true, nullable = false)
	private String username;

	@JsonIgnore
	@Column(nullable = false)
	private String passwort;

	@Column
	private Date geburtsdatum;

	@Column
	private String standort;

	@Enumerated(EnumType.STRING)
	private CareerLevel karriereLevel = CareerLevel.UNBEKANNT;

	@Enumerated(EnumType.STRING)
	private Rolle rolle = Rolle.ROLE_USER;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToMany(mappedBy = "mitarbeiter", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private Set<Skill> skills = new HashSet<>();

	@OneToMany(mappedBy = "mitarbeiter", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private Set<KalenderEvent> kalenderEvents = new HashSet<>();

	/**
	 *
	 */
	public Mitarbeiter() {
		super();
	}

	/**
	 * @param pnr
	 * @param vorname
	 * @param nachname
	 * @param username
	 * @param passwort
	 * @param geburtsdatum
	 * @param standort
	 * @param karriereLevel
	 * @param rolle
	 * @param address
	 * @param team
	 * @param skills
	 * @param kalenderEvents
	 */
	public Mitarbeiter(String pnr, String vorname, String nachname, String username, String passwort, Date geburtsdatum,
					   String standort, CareerLevel karriereLevel, Rolle rolle, Address address, Team team, Set<Skill> skills,
					   Set<KalenderEvent> kalenderEvents) {
		super();
		this.pnr = pnr;
		this.vorname = vorname;
		this.nachname = nachname;
		this.username = username;
		this.passwort = passwort;
		this.geburtsdatum = geburtsdatum;
		this.standort = standort;
		this.karriereLevel = karriereLevel;
		this.rolle = rolle;
		this.address = address;
		this.team = team;
		this.skills = skills;
		this.kalenderEvents = kalenderEvents;
	}




	/**
	 * Register constrctor.
	 *
	 * @param pnr
	 * @param vorname
	 * @param nachname
	 * @param username
	 * @param passwort
	 * @param geburtsdatum
	 * @param standort
	 * @param karriereLevel
	 * @param rolle
	 * @param address
	 */
	public Mitarbeiter(String pnr, String vorname, String nachname, String username, String passwort, Date geburtsdatum,
					   String standort, CareerLevel karriereLevel, Rolle rolle, Address address) {
		super();
		this.pnr = pnr;
		this.vorname = vorname;
		this.nachname = nachname;
		this.username = username;
		this.passwort = passwort;
		this.geburtsdatum = geburtsdatum;
		this.standort = standort;
		this.karriereLevel = karriereLevel;
		this.rolle = rolle;
		this.address = address;
	}

	public void addSkill(Skill skill) {
		skills.add(skill);
		skill.setMitarbeiter(this);
	}

	public void addSkills(Set<Skill> skillSet) {
		for (Skill skill : skillSet) {
			skills.add(skill);
			skill.setMitarbeiter(this);
		}
	}

	public void addKalenderEvent(KalenderEvent event) {
		kalenderEvents.add(event);
		event.setMitarbeiter(this);
	}

	public void removeKalenderEvent(KalenderEvent event) {
		kalenderEvents.remove(event);
		event.setMitarbeiter(null);
	}

	/**
	 * @return the maId
	 */
	public Long getMaId() {
		return maId;
	}

	/**
	 * @param maId the maId to set
	 */
	public void setMaId(Long maId) {
		this.maId = maId;
	}

	/**
	 * @return the pnr
	 */
	public String getPnr() {
		return pnr;
	}

	/**
	 * @param pnr the pnr to set
	 */
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * @param vorname the vorname to set
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * @param nachname the nachname to set
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * @return the geburtsdatum
	 */
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	/**
	 * @param geburtsdatum the geburtsdatum to set
	 */
	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	/**
	 * @return the standort
	 */
	public String getStandort() {
		return standort;
	}

	/**
	 * @param standort the standort to set
	 */
	public void setStandort(String standort) {
		this.standort = standort;
	}

	/**
	 * @return the karriereLevel
	 */
	public CareerLevel getKarriereLevel() {
		return karriereLevel;
	}

	/**
	 * @param karriereLevel the karriereLevel to set
	 */
	public void setKarriereLevel(CareerLevel karriereLevel) {
		this.karriereLevel = karriereLevel;
	}

	/**
	 * @return the rolle
	 */
	public Rolle getRolle() {
		return rolle;
	}

	/**
	 * @param rolle the rolle to set
	 */
	public void setRolle(Rolle rolle) {
		this.rolle = rolle;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @return the skills
	 */
	public Set<Skill> getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	/**
	 * @return the kalenderEvents
	 */
	public Set<KalenderEvent> getKalenderEvents() {
		return kalenderEvents;
	}

	/**
	 * @param kalenderEvents the kalenderEvents to set
	 */
	public void setKalenderEvents(Set<KalenderEvent> kalenderEvents) {
		this.kalenderEvents = kalenderEvents;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the passwort
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * @param passwort the passwort to set
	 */
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

}