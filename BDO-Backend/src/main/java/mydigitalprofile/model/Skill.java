package mydigitalprofile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long skillId;

	@Column
	private String skillName;

	@ManyToOne
	@JoinColumn(name = "mitarbeiter_id")
	private Mitarbeiter mitarbeiter;
	
	

	/**
	 * 
	 */
	public Skill() {
		super();
	}

	

	/**
	 * @param skillName
	 */
	public Skill(String skillName) {
		super();
		this.skillName = skillName;
	}



	/**
	 * @return the skillId
	 */
	public Long getSkillId() {
		return skillId;
	}

	/**
	 * @param skillId the skillId to set
	 */
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	/**
	 * @return the skillName
	 */
	public String getSkillName() {
		return skillName;
	}

	/**
	 * @param skillName the skillName to set
	 */
	public void setSkillName(String skillName) {
		this.skillName = skillName;
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
