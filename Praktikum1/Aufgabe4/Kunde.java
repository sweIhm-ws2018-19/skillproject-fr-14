package UML_Uebungsblatt1;

import java.util.ArrayList;

public abstract class Kunde {

    private ArrayList<Konto> konten;

    public Kunde(ArrayList<Konto> konten){
        if(konten.isEmpty()) throw new IllegalArgumentException("Mindestens ein Konto");
        this.konten = konten;
    }

    public ArrayList<Konto> getKonten() {
        return konten;
    }

    public void setKonten(ArrayList<Konto> konten) {
        if(konten == null) throw new NullPointerException();
        if(konten.isEmpty()) throw new IllegalArgumentException("Mindestens ein Konto");
        this.konten = konten;
    }

    /* @author of comment Chrysotomus
	/	Die Liste "konten" ist anfällig für Veränderungen von außen, da die Variable nicht final ist
	/	und eure set-methode public ist. Mein Vorschlag: Macht die Liste "konten" final und schreibt
	/	benennt die Methode "setKonten" in "addKOnten" um. Diese ersetzt dann nicht die Arrayliste,
	/	sondern fügt jedes Konto der übergebenen Liste den bereits gespeicherten Konten hinzu.
	/	Das realisiert sich einfacher, wenn Ihr eine simple "addKonto"-Methode hinzufügt.
	/	Dann kann eine Implementierung von einer "removeKonto"-Methode eigentlich auch nicht schaden.
	*/

}
