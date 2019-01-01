package tasche_packen.model;

public class Utilities {

    private Utilities() {

    }

    public static final SubjectsToday SUBJECTS_TODAY = new SubjectsToday();

    //stores subject to/from which an item will be added/removed; will be set to null after being handled
    private static String subjectToBeChanged = null;

    public static String getSubjectToBeChanged() {
        return subjectToBeChanged;
    }

    public static void setSubjectToBeChanged(String subject) {
        subjectToBeChanged = subject;
    }

    //wird bei ausgabe verwendet
    public static String subjectMapper(String subjectID) {
        String subject;
        switch(subjectID) {
            case "Software":
                subject = "Software Engineering";
                break;
            case "Algorithmen":
                subject = "Algorithmen und Datenstrukturen";
                break;
            case "Wahrscheinlichkeitsrechnung":
                subject = "Wahrscheinlichkeitsrechnung und Statistik";
                break;
            case "Operations":
                subject = "Operations Research";
                break;
            default :
                subject = subjectID;
                break;
        }
        return subject;
    }

}
