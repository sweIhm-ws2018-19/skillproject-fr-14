package tasche_packen.model;

import java.util.ArrayList;

public class SubjectsToday {

    private Calendar today;
    private ArrayList<String> subjectsToday;

    public SubjectsToday() {
        today = new Calendar();
        this.subjectsToday = (ArrayList<String>) today.getTodayLectures();
    }

    public boolean removeSubject(String subject) {
        return this.subjectsToday.remove(subject);
    }

    public ArrayList<String> getSubjectsToday() {
        return subjectsToday;
    }

}
