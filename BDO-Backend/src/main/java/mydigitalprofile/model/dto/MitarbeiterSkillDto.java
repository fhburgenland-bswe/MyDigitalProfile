package mydigitalprofile.model.dto;

import mydigitalprofile.model.CareerLevel;
import mydigitalprofile.model.Rolle;

import java.util.HashSet;
import java.util.Set;

public class MitarbeiterSkillDto extends MitarbeiterDto {
    private Set<String> skills= new HashSet<>();


    public MitarbeiterSkillDto() {
        super();
    }

    public MitarbeiterSkillDto(String pnr, String vorname, String nachname, String username, String passwort,
                               String geburtsdatum, String strasse, String hausNr, String plz, String ort, String standort, Rolle rolle,
                               CareerLevel karriereLevel, Set<String> skills) {
        super(pnr, vorname, nachname, username, passwort, geburtsdatum, strasse, hausNr, plz, ort, standort, rolle,
                karriereLevel);
        this.skills = skills;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}

