package mydigitalprofile.model.dto;

import java.util.HashSet;
import java.util.Set;

public class TeamDto {

    private String teamName;
    private Set<Long> mitarbeiterIds = new HashSet<>();

    /**
     *
     */
    public TeamDto() {
        super();
    }

    /**
     * @param teamName
     * @param mitarbeiterIds
     */
    public TeamDto(String teamName, Set<Long> mitarbeiterIds) {
        super();
        this.teamName = teamName;
        this.mitarbeiterIds = mitarbeiterIds;
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
     * @return the mitarbeiterIds
     */
    public Set<Long> getMitarbeiterIds() {
        return mitarbeiterIds;
    }

    /**
     * @param mitarbeiterIds the mitarbeiterIds to set
     */
    public void setMitarbeiterIds(Set<Long> mitarbeiterIds) {
        this.mitarbeiterIds = mitarbeiterIds;
    }

}