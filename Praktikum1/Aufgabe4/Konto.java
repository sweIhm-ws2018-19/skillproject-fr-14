package UML_Uebungsblatt1;

import java.util.ArrayList;



public class Konto {

    private ArrayList<Kunde> zeichnungsberechtigte;
    private final String bezeichnung;;
    private Geldbetrag geldbetrag;

    public Konto(String bezeichnung, ArrayList<Kunde> zeichnungsberechtigte) {
        if(zeichnungsberechtigte.isEmpty()) throw new IllegalArgumentException("Mindestens ein Kunde");
        this.zeichnungsberechtigte = zeichnungsberechtigte;
        this.bezeichnung = bezeichnung;
        geldbetrag.setBetrag(0);
    }

    public int saldo() {
        return geldbetrag.getBetrag();
    }

    public void einzahlen(Geldbetrag einzahlung) {
        geldbetrag.setBetrag(einzahlung.getBetrag() + geldbetrag.getBetrag());
    }

    public ArrayList<Kunde> getZeichnungsberechtigte() {
        return zeichnungsberechtigte;
    }

    public void setZeichnungsberechtigte(ArrayList<Kunde> zeichnungsberechtigte) {
        if(zeichnungsberechtigte == null) throw new NullPointerException();
        if(zeichnungsberechtigte.isEmpty()) throw new IllegalArgumentException("Mindestens ein Kunde");
        this.zeichnungsberechtigte = zeichnungsberechtigte;
    }

}
