package mydigitalprofile.model.dto;


public class ProjektDto {

	private String projektName;
	private String volumina;
	private String startDate;
	private String endDate;
	private String beschreibung;

	/**
	 *
	 */
	public ProjektDto() {
		super();
	}

	/**
	 * @param projektName
	 * @param volumina
	 * @param startDate
	 * @param endDate
	 * @param beschreibung
	 */
	public ProjektDto(String projektName, String volumina, String startDate, String endDate, String beschreibung) {
		super();
		this.projektName = projektName;
		this.volumina = volumina;
		this.startDate = startDate;
		this.endDate = endDate;
		this.beschreibung = beschreibung;
	}



	/**
	 * @param volumina
	 * @param startDate
	 * @param endDate
	 */
	public ProjektDto(String volumina, String startDate, String endDate) {
		super();
		this.volumina = volumina;
		this.startDate = startDate;
		this.endDate = endDate;
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
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
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

}