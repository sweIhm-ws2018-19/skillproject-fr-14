package tasche_packen.model;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectItemAssignment {
    //These values are for test purposes. Later the subjects  will be pulled from zpa and the assigned items will be fetched from database

    private List<Subjects> subjectsVisited = new ArrayList<>();
    private final List<Subjects> subjectsToday = new ArrayList<>();
    public SubjectItemAssignment(Subjects... subjects) {

        for (int i = 0; i < subjects.length; i++) {
            subjectsVisited.add(subjects[i]);
            subjectsToday.add(subjects[i]);
        }

    }


    //Test constructor
    public SubjectItemAssignment() {

        Subjects algorithmsAndDatastructures = new Subjects("algorithmen und datenstrukturen");
        Subjects operationsResearch = new Subjects("operations research");
        Subjects statistics = new Subjects("wahrscheinlichkeitsrechnung und statistik");

        subjectsVisited.add(algorithmsAndDatastructures);
        subjectsVisited.add(operationsResearch);
        subjectsVisited.add(statistics);

        subjectsToday.add(algorithmsAndDatastructures);
        subjectsToday.add(operationsResearch);
        subjectsToday.add(statistics);
            algorithmsAndDatastructures.addItem(Item.PAD);
            operationsResearch.addItem(Item.CALCULATOR);
            statistics.addItem(Item.PENCIL_CASE);
    }


    public List<Item> getTodayRequiredItems() {
        return subjectsVisited.stream().map(subject -> subject.getRequiredItems())
                .flatMap(itemList -> itemList.stream())
                .distinct().collect(Collectors.toList());
    }

    public String getTodayRequiredItemsAsString() {
        return getTodayRequiredItems().stream()
                .map(item -> getTodayRequiredItems().indexOf(item))
                .map(i -> i == getTodayRequiredItems().size() - 1  && getTodayRequiredItems().size() > 1? "und " + getTodayRequiredItems().get(i).getName() : getTodayRequiredItems().get(i).getName())
                .reduce("", (o, n) -> o + " " + n);
    }


    public void deleteNotVisitedSubjects(String removeSubject) {
        subjectsVisited = subjectsVisited.stream().filter(subject ->  !subject.getName().equals(removeSubject)).collect(Collectors.toList());
    }

    public List<Subjects> getSubjectsVisited() {
        return subjectsVisited;
    }


    public String getSubjectsVisitedAsString() {
        return     subjectsVisited.stream().map(s -> subjectsVisited.indexOf(s))
                .map(i -> i == subjectsVisited.size() - 1 && subjectsVisited.size() > 1 ? "und " + subjectsVisited.get(i).getName() : subjectsVisited.get(i).getName())
                .reduce("", (o, n) -> o + " " + n);
    }


    public List<Subjects> getSubjectsToday() {
        return subjectsToday;
    }


    public String getSubjectsTodayAsString() {
                   return     subjectsToday.stream().map(s -> subjectsToday.indexOf(s))
                                .map(i -> i == subjectsToday.size() - 1 && subjectsVisited.size() > 1 ? "und " + subjectsToday.get(i).getName() : subjectsToday.get(i).getName())
                                 .reduce("", (o, n) -> o + " " + n);

    }

}
