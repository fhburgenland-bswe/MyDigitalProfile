package mydigitalprofile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;

	@Column
	private String strasse;

	@Column
	private String hausNr;

	@Column
	private String plz;

	@Column
	private String ort;

	@OneToOne(mappedBy = "address")
	private Mitarbeiter mitarbeiter;

	/**
	 * 
	 */
	public Address() {
		super();
	}

	/**
	 * @param strasse
	 * @param hausNr
	 * @param plz
	 * @param ort
	 */
	public Address(String strasse, String hausNr, String plz, String ort) {
		super();
		this.strasse = strasse;
		this.hausNr = hausNr;
		this.plz = plz;
		this.ort = ort;
	}

	/**
	 * @return the addressId
	 */
	public long getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(long addressId) {
		this.addressId = addressId;
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
