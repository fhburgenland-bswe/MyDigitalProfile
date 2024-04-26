package mydigitalprofile.model.dto;

public class MitarbeiterDto {

	private String pnr;
	private String vorname;
	private String nachname;
	private String username;
	private String passwort;
	private String geburtsdatum;
	private String strasse;
	private String hausNr;
	private String plz;
	private String ort;

	/**
	 * 
	 */
	public MitarbeiterDto() {
		super();
	}

	/**
	 * @param pnr
	 * @param vorname
	 * @param nachname
	 * @param username
	 * @param passwort
	 * @param geburtsdatum
	 * @param strasse
	 * @param hausNr
	 * @param plz
	 * @param ort
	 */
	public MitarbeiterDto(String pnr, String vorname, String nachname, String username, String passwort,
			String geburtsdatum, String strasse, String hausNr, String plz, String ort) {
		super();
		this.pnr = pnr;
		this.vorname = vorname;
		this.nachname = nachname;
		this.username = username;
		this.passwort = passwort;
		this.geburtsdatum = geburtsdatum;
		this.strasse = strasse;
		this.hausNr = hausNr;
		this.plz = plz;
		this.ort = ort;
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

	/**
	 * @return the geburtsdatum
	 */
	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	/**
	 * @param geburtsdatum the geburtsdatum to set
	 */
	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	/**
	 * @return the strasse
	 */
	public String getStrasse() {
		return strasse;
	}

	/**
	 * @param strasse the strasse to set
	 */
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	/**
	 * @return the hausNr
	 */
	public String getHausNr() {
		return hausNr;
	}

	/**
	 * @param hausNr the hausNr to set
	 */
	public void setHausNr(String hausNr) {
		this.hausNr = hausNr;
	}

	/**
	 * @return the plz
	 */
	public String getPlz() {
		return plz;
	}

	/**
	 * @param plz the plz to set
	 */
	public void setPlz(String plz) {
		this.plz = plz;
	}

	/**
	 * @return the ort
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * @param ort the ort to set
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}

}
