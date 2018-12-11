package tasche_packen.model;

import java.util.ArrayList;
import java.util.List;

public class SubjectsToday {

    private Day today;
    private ArrayList<String> todaysSubjects;

    public SubjectsToday() {
        today = new Day();
        this.todaysSubjects = (ArrayList<String>) today.getLectures();
    }

    public boolean removeSubject(String subject) {
        return this.todaysSubjects.remove(subject);
    }

    public List<String> getTodaysSubjects() {
        return todaysSubjects;
    }

}
