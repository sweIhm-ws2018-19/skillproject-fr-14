package tasche_packen.model;

import java.util.ArrayList;
import java.util.List;

public class SubjectsToday {

    private Calendar today;
    private ArrayList<String> todaysSubjects;

    public SubjectsToday() {
        today = new Calendar();
        this.todaysSubjects = (ArrayList<String>) today.getTodayLectures();
    }

    public boolean removeSubject(String subject) {
        return this.todaysSubjects.remove(subject);
    }

    public List<String> getTodaysSubjects() {
        return todaysSubjects;
    }

}
