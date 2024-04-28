package mydigitalprofile.model.dto;


public class KalenderEventDto {

    private String vonDatum;
    private String bisDatum;
    private String Typ;
    private String Beschreibung;

    /**
     *
     */
    public KalenderEventDto() {
        super();
    }

    /**
     * @param vonDatum
     * @param bisDatum
     * @param typ
     * @param beschreibung
     */
    public KalenderEventDto(String vonDatum, String bisDatum, String typ, String beschreibung) {
        super();
        this.vonDatum = vonDatum;
        this.bisDatum = bisDatum;
        Typ = typ;
        Beschreibung = beschreibung;
    }

    /**
     * @return the vonDatum
     */
    public String getVonDatum() {
        return vonDatum;
    }

    /**
     * @param vonDatum the vonDatum to set
     */
    public void setVonDatum(String vonDatum) {
        this.vonDatum = vonDatum;
    }

    /**
     * @return the typ
     */
    public String getTyp() {
        return Typ;
    }

    /**
     * @param typ the typ to set
     */
    public void setTyp(String typ) {
        Typ = typ;
    }

    /**
     * @return the bisDatum
     */
    public String getBisDatum() {
        return bisDatum;
    }

    /**
     * @param bisDatum the bisDatum to set
     */
    public void setBisDatum(String bisDatum) {
        this.bisDatum = bisDatum;
    }

    /**
     * @return the beschreibung
     */
    public String getBeschreibung() {
        return Beschreibung;
    }

    /**
     * @param beschreibung the beschreibung to set
     */
    public void setBeschreibung(String beschreibung) {
        Beschreibung = beschreibung;
    }

}
