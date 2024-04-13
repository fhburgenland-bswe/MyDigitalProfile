package mydigitalprofile.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a Rolle entity in the application.
 *
 * @author Khaled Alnahhas
 */
@Entity
public class Rolle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String Berechtigung;

    @Column
    private String RolenBezeichnung;

    @OneToMany(mappedBy = "rolle")
    private Set<Benutzer> benutzer;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBerechtigung() {
        return Berechtigung;
    }

    public void setBerechtigung(String berechtigung) {
        Berechtigung = berechtigung;
    }

    public String getRolenBezeichnung() {
        return RolenBezeichnung;
    }

    public void setRolenBezeichnung(String rolenBezeichnung) {
        RolenBezeichnung = rolenBezeichnung;
    }

    public Set<Benutzer> getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Set<Benutzer> benutzer) {
        this.benutzer = benutzer;
    }

    // equals(), hashCode(), toString()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rolle rolle = (Rolle) o;

        return id != null ? id.equals(rolle.id) : rolle.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Rolle{" +
                "id=" + id +
                ", Berechtigung='" + Berechtigung + '\'' +
                ", RolenBezeichnung='" + RolenBezeichnung + '\'' +
                ", benutzer=" + benutzer +
                '}';
    }
}