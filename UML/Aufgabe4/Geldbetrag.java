package UML_Uebungsblatt1;

//Geldbetrag ist laut dem UML-Diagramm eine eigene Klasse und kein int.
//Deswegen wurde eine zusätzliche Klasse erstellt und in der Klasse Konto mit Gettern
//und Settern auf den Betrag zugegriffen.


public class Geldbetrag {
    private  int betrag;

    public int getBetrag() {
        return betrag;
    }

    public void setBetrag(int betrag) {
        this.betrag = betrag;
    }
}
