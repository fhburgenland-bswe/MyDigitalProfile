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

	@OneToOne(mappedBy = "projekt")
	private Team team;

	/**
	 *
	 */
	public Projekt() {
		super();
	}

	/**
	 * @param projektName
	 * @param volumina
	 * @param startDate
	 * @param endDate
	 * @param beschreibung
	 */
	public Projekt(String projektName, String volumina, Date startDate, Date endDate,
				   String beschreibung) {
		super();
		this.projektName = projektName;
		this.volumina = volumina;
		this.startDate = startDate;
		this.endDate = endDate;
		this.beschreibung = beschreibung;
	}


	public void removeTeam() {
		getTeam().setProjekt(null);
		setTeam(null);
	}
	/**
	 * @return the projektID
	 */
	public Long getProjektID() {
		return projektID;
	}

	/**
	 * @param projektID the projektID to set
	 */
	public void setProjektID(Long projektID) {
		this.projektID = projektID;
	}

	/**
	 * @return the projektName
	 */
	public String getProjektName() {
		return projektName;
	}

	/**
	 * @param projektName the projektName to set
	 */
	public void setProjektName(String projektName) {
		this.projektName = projektName;
	}

	/**
	 * @return the volumina
	 */
	public String getVolumina() {
		return volumina;
	}

	/**
	 * @param volumina the volumina to set
	 */
	public void setVolumina(String volumina) {
		this.volumina = volumina;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * @param beschreibung the beschreibung to set
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
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

}