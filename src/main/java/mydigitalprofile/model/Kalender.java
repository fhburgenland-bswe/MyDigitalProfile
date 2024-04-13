package mydigitalprofile.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Represents a Kalender entity in the application.
 *
 * @author KhaledAlnahhas1
 */
@Entity
public class Kalender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long KalenderID;

    @Column
    private Date vonDatum;

    @Column
    private String Typ;

    @Column
    private Date bisDatum;

    @Column
    private String Beschreibung;

    @OneToMany(mappedBy = "kalender")
    private Set<Mitarbeiter> mitarbeiter;

    // Getters and setters

    public Long getKalenderID() {
        return KalenderID;
    }

    public void setKalenderID(Long kalenderID) {
        KalenderID = kalenderID;
    }

    public Date getVonDatum() {
        return vonDatum;
    }

    public void setVonDatum(Date vonDatum) {
        this.vonDatum = vonDatum;
    }

    public String getTyp() {
        return Typ;
    }

    public void setTyp(String typ) {
        Typ = typ;
    }

    public Date getBisDatum() {
        return bisDatum;
    }

    public void setBisDatum(Date bisDatum) {
        this.bisDatum = bisDatum;
    }

    public String getBeschreibung() {
        return Beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        Beschreibung = beschreibung;
    }

    public Set<Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Set<Mitarbeiter> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    // equals(), hashCode(), toString()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kalender kalender = (Kalender) o;

        return KalenderID != null ? KalenderID.equals(kalender.KalenderID) : kalender.KalenderID == null;
    }

    @Override
    public int hashCode() {
        return KalenderID != null ? KalenderID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Kalender{" +
                "KalenderID=" + KalenderID +
                ", vonDatum=" + vonDatum +
                ", Typ='" + Typ + '\'' +
                ", bisDatum=" + bisDatum +
                ", Beschreibung='" + Beschreibung + '\'' +
                ", mitarbeiter=" + mitarbeiter +
                '}';
    }
}