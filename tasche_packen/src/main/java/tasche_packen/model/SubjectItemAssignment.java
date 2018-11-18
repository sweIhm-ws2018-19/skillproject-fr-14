package main.java.tasche_packen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectItemAssignment {
    //These values are for test purposes. Later the subjects  will be pulled from zpa and the assigned items will be fetched from database


    private List<Subject> subjectsVisited = new ArrayList<>();
    private final List<Subject> subjectsToday = new ArrayList<>();

    public SubjectItemAssignment() {
        Subject algorithmsAndDatastructures = new Subject("Algorithmen und Datenstrukturen");
        Subject operationsResearch = new Subject("Operations Research");
        Subject statistics = new Subject("Wahrscheinlichkeitsrechnung und Statistik");
        subjectsVisited.add(algorithmsAndDatastructures);
        subjectsVisited.add(operationsResearch);
        subjectsVisited.add(statistics);
        algorithmsAndDatastructures.addItem(Item.Pad);
        operationsResearch.addItem(Item.Calcuator);
        statistics.addItem(Item.PencilCase);
        subjectsToday.add(algorithmsAndDatastructures);
        subjectsToday.add(operationsResearch);
        subjectsToday.add(statistics);
    }


    public List<Item> getTodayRequiredItems() {
        return subjectsVisited.stream().map(subject -> subject.getRequiredItems())
                .flatMap(itemList -> itemList.stream())
                .distinct().collect(Collectors.toList());
    }

    public String getTodayRequiredItemsAsString() {
        return getTodayRequiredItems().stream()
                .map(item -> getTodayRequiredItems().indexOf(item))
                .map(i -> i == getTodayRequiredItems().size() - 1 ? " und " + getTodayRequiredItems().get(i).getName() : getTodayRequiredItems().get(i).getName())
                .reduce("", (o, n) -> o + " " + n);
    }


    public void deleteNotVisitedSubjects(String removeSubject) {
        subjectsVisited = subjectsVisited.stream().filter(subject -> !subject.getName().equals(removeSubject)).collect(Collectors.toList());
    }

    public List<Subject> getSubjectsVisited() {
        return subjectsVisited;
    }


    public String getSubjectsVisitedAsString() {
        return     subjectsVisited.stream().map(s -> subjectsVisited.indexOf(s))
                .map(i -> i == subjectsVisited.size() - 1 ? " and " + subjectsVisited.get(i).getName() : subjectsVisited.get(i).getName())
                .reduce("", (o, n) -> o + " " + n);

    }


    public List<Subject> getSubjectsToday() {
        return subjectsToday;
    }


    public String getSubjectsTodayAsString() {
                   return     subjectsToday.stream().map(s -> subjectsToday.indexOf(s))
                                .map(i -> i == subjectsToday.size() - 1 ? " and " + subjectsToday.get(i).getName() : subjectsToday.get(i).getName())
                                .reduce("", (o, n) -> o + " " + n);

    }

}
