package mydigitalprofile.model.dto;

import java.util.HashSet;
import java.util.Set;

public class TeamDto {

    private String teamName;
    private Set<String> mitarbeiterUsernames = new HashSet<>();

    /**
     *
     */
    public TeamDto() {
        super();
    }

    /**
     * @param teamName
     * @param mitarbeiterUsernames
     */
    public TeamDto(String teamName, Set<String> mitarbeiterUsernames) {
        super();
        this.teamName = teamName;
        this.mitarbeiterUsernames = mitarbeiterUsernames;
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
     * @return the mitarbeiterUsernames
     */
    public Set<String> getMitarbeiterUsernames() {
        return mitarbeiterUsernames;
    }

    /**
     * @param mitarbeiterUsernames the mitarbeiterUsernames to set
     */
    public void setMitarbeiterUsernames(Set<String> mitarbeiterUsernames) {
        this.mitarbeiterUsernames = mitarbeiterUsernames;
    }

}