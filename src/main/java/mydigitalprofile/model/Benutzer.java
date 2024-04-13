package mydigitalprofile.model;

import javax.persistence.*;

/**
 * Represents a Benutzer entity in the application.
 *
 * @author Khaled Alnahhas
 */
@Entity
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BenutzerID;

    @Column
    private String Email;

    @Column
    private String Passwort;

    @OneToOne
    @JoinColumn(name = "mitarbeiter_id")
    private Mitarbeiter mitarbeiter;

    @OneToOne
    @JoinColumn(name = "rolle_id")
    private Rolle rolle;

    // Getters and setters

    public Long getBenutzerID() {
        return BenutzerID;
    }

    public void setBenutzerID(Long benutzerID) {
        BenutzerID = benutzerID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPasswort() {
        return Passwort;
    }

    public void setPasswort(String passwort) {
        Passwort = passwort;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    // equals(), hashCode(), toString()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Benutzer benutzer = (Benutzer) o;

        return BenutzerID != null ? BenutzerID.equals(benutzer.BenutzerID) : benutzer.BenutzerID == null;
    }

    @Override
    public int hashCode() {
        return BenutzerID != null ? BenutzerID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Benutzer{" +
                "BenutzerID=" + BenutzerID +
                ", Email='" + Email + '\'' +
                ", Passwort='" + Passwort + '\'' +
                ", mitarbeiter=" + mitarbeiter +
                ", rolle=" + rolle +
                '}';
    }
}