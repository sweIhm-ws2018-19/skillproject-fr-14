package UML_Uebungsblatt1;

import java.util.ArrayList;

public class Adresse {

    private final String strasse;
    private final int hausnummer;
    private final String plz;
    private final String ort;
    private ArrayList<Kunde> kunden;

    public Adresse(ArrayList<Kunde> kunden, String strasse, int hausnummer, String plz, String ort) {
        this.kunden = kunden;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public ArrayList<Kunde> getKunden() {
        return kunden;
    }

    public void setKunden(ArrayList<Kunde> kunden) {
        this.kunden = kunden;
    }
}
