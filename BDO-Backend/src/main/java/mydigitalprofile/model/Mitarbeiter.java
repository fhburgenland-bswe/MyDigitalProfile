package mydigitalprofile.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents a Mitarbeiter entity in the application.
 *
 * @author Khaled Alnahhas
 */
@Entity
public class Mitarbeiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MAID;

    @Column
    private String PNR;

    @Column
    private String Vorname;

    @Column
    private String Nachname;

    @Column
    private Date Geburtsdatum;

    @Column
    private String Standort;

    @Column
    private String Ort;

    @Column
    private String Straße;

    @Column
    private String HausNr;

    @Column
    private String Skills;

    @Column
    private String Karrierelevel;

    @Column
    private String PLZ;

    @OneToOne
    @JoinColumn(name = "benutzer_id")
    private Benutzer benutzer;

    @OneToOne
    @JoinColumn(name = "kalender_id")
    private Kalender kalender;

    @ManyToOne
    @JoinColumn(name = "teamleiter_id")
    private TeamLeiter teamleiter;

    // Getters and setters

    public Long getMAID() {
        return MAID;
    }

    public void setMAID(Long MAID) {
        this.MAID = MAID;
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String PNR) {
        this.PNR = PNR;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public Date getGeburtsdatum() {
        return Geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        Geburtsdatum = geburtsdatum;
    }

    public String getStandort() {
        return Standort;
    }

    public void setStandort(String standort) {
        Standort = standort;
    }

    public String getOrt() {
        return Ort;
    }

    public void setOrt(String ort) {
        Ort = ort;
    }

    public String getStraße() {
        return Straße;
    }

    public void setStraße(String straße) {
        Straße = straße;
    }

    public String getHausNr() {
        return HausNr;
    }

    public void setHausNr(String hausNr) {
        HausNr = hausNr;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }

    public String getKarrierelevel() {
        return Karrierelevel;
    }

    public void setKarrierelevel(String karrierelevel) {
        Karrierelevel = karrierelevel;
    }

    public String getPLZ() {
        return PLZ;
    }

    public void setPLZ(String PLZ) {
        this.PLZ = PLZ;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Kalender getKalender() {
        return kalender;
    }

    public void setKalender(Kalender kalender) {
        this.kalender = kalender;
    }

    public TeamLeiter getTeamleiter() {
        return teamleiter;
    }

    public void setTeamleiter(TeamLeiter teamleiter) {
        this.teamleiter = teamleiter;
    }

    // equals(), hashCode(), toString()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mitarbeiter that = (Mitarbeiter) o;

        return MAID != null ? MAID.equals(that.MAID) : that.MAID == null;
    }

    @Override
    public int hashCode() {
        return MAID != null ? MAID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                "MAID=" + MAID +
                ", PNR='" + PNR + '\'' +
                ", Vorname='" + Vorname + '\'' +
                ", Nachname='" + Nachname + '\'' +
                ", Geburtsdatum=" + Geburtsdatum +
                ", Standort='" + Standort + '\'' +
                ", Ort='" + Ort + '\'' +
                ", Straße='" + Straße + '\'' +
                ", HausNr='" + HausNr + '\'' +
                ", Skills='" + Skills + '\'' +
                ", Karrierelevel='" + Karrierelevel + '\'' +
                ", PLZ='" + PLZ + '\'' +
                ", benutzer=" + benutzer +
                ", kalender=" + kalender +
                ", teamleiter=" + teamleiter +
                '}';
    }
}