package mydigitalprofile.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents a Kalender entity in the application.
 *
 * @author KhaledAlnahhas1
 */
@Entity
public class KalenderEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long kalenderEventId;

	@Column
	private Date vonDatum;

	@Column
	private String Typ;

	@Column
	private Date bisDatum;

	@Column
	private String Beschreibung;

	@ManyToOne
	@JoinColumn(name = "mitarbeiter_id")
	private Mitarbeiter mitarbeiter;

	/**
	 * 
	 */
	public KalenderEvent() {
		super();
	}

	/**
	 * @param vonDatum
	 * @param typ
	 * @param bisDatum
	 * @param beschreibung
	 */
	public KalenderEvent(Date vonDatum, String typ, Date bisDatum, String beschreibung) {
		super();
		this.vonDatum = vonDatum;
		Typ = typ;
		this.bisDatum = bisDatum;
		Beschreibung = beschreibung;
	}

	/**
	 * @return the kalenderEventId
	 */
	public Long getKalenderEventId() {
		return kalenderEventId;
	}

	/**
	 * @param kalenderEventId the kalenderEventId to set
	 */
	public void setKalenderEventId(Long kalenderEventId) {
		this.kalenderEventId = kalenderEventId;
	}

	/**
	 * @return the vonDatum
	 */
	public Date getVonDatum() {
		return vonDatum;
	}

	/**
	 * @param vonDatum the vonDatum to set
	 */
	public void setVonDatum(Date vonDatum) {
		this.vonDatum = vonDatum;
	}

	/**
	 * @return the typ
	 */
	public String getTyp() {
		return Typ;
	}

	/**
	 * @param typ the typ to set
	 */
	public void setTyp(String typ) {
		Typ = typ;
	}

	/**
	 * @return the bisDatum
	 */
	public Date getBisDatum() {
		return bisDatum;
	}

	/**
	 * @param bisDatum the bisDatum to set
	 */
	public void setBisDatum(Date bisDatum) {
		this.bisDatum = bisDatum;
	}

	/**
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return Beschreibung;
	}

	/**
	 * @param beschreibung the beschreibung to set
	 */
	public void setBeschreibung(String beschreibung) {
		Beschreibung = beschreibung;
	}

	/**
	 * @return the mitarbeiter
	 */
	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	/**
	 * @param mitarbeiter the mitarbeiter to set
	 */
	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

}