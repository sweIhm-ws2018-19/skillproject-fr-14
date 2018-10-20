package UML_Uebungsblatt1;
import java.util.ArrayList;

public class Privatkunde extends Kunde {

    private final String vorname;
    private final String nachname;
    private Adresse postAdresse;

    public Privatkunde(ArrayList<Konto> konten, String vorname, String nachname, Adresse adresse) {
        super(konten);
        if(adresse == null) throw new NullPointerException();
        this.vorname = vorname;
        this.nachname = nachname;
        this.postAdresse = adresse;
    }

    public void setPostAdresse(Adresse postAdresse) {
        if(postAdresse == null) throw new NullPointerException();
        this.postAdresse = postAdresse;
    }

    public Adresse getPostAdresse() {
        return postAdresse;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    /* @author of comment Chrysotomus
	/	Der Nachname kann sich im Fall einer Heirat ändern - sollte also womöglich änderbar sein. Schließlich
	/	ist die Adresse auch änderbar. Das hängt allerdings von der gewünschten Implementierung ab.
	/	Hier ist die postAdresse außerdem anfällig gegenüber von äußeren Veränderungen, da die Referenz der
	/	Variable (nicht final) in einer public methode rausgegeben wird. Ansonsten passt der Code soweit.
	*/

}
