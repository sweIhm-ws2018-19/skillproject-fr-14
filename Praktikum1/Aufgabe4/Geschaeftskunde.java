package UML_Uebungsblatt1;

import java.util.ArrayList;

public class Geschaeftskunde extends Kunde{

    private final String firmenname;
    private Adresse domizilAdresse;

    public Geschaeftskunde(ArrayList<Konto> konten, String firmenname, Adresse adresse) {
        super(konten);
        if(adresse == null) throw new NullPointerException();
        this.firmenname = firmenname;
        this.domizilAdresse = adresse;
    }

    public void setDomizilAdresse(Adresse domizilAdresse) {
        if(domizilAdresse == null) throw new NullPointerException();
        this.domizilAdresse = domizilAdresse;
    }

    public Adresse getDomizilAdresse() {
        if(this.domizilAdresse == null)throw new RuntimeException("Dieser Geschäftskunde hat keine Adresse angegeben");
        return domizilAdresse;
    }

    /* @author of comment Chrysotomus
	/	- Im Konstruktor wird nicht geprüft, ob der String null ist
	/	- Die Abfrage bei @method getDomizilAdresse() ob die adresse = null ist, spielt kaum eine Rolle. Dieser Fall
	/	  kann nur eintreten, wenn die Adresse von außen bewusst manipuliert wird, da die Adressvariable nicht final
	/	  und die get-Methode public ist.
	*/

}
