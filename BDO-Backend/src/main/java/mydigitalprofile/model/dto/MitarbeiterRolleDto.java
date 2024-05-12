package mydigitalprofile.model.dto;

import mydigitalprofile.model.Rolle;

public class MitarbeiterRolleDto {

	private String username;
	private Rolle rolle = Rolle.ROLE_USER;

	/**
	 * 
	 */
	public MitarbeiterRolleDto() {
		super();
	}

	/**
	 * @param username
	 * @param rolle
	 */
	public MitarbeiterRolleDto(String username, Rolle rolle) {
		super();
		this.username = username;
		this.rolle = rolle;
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

}
