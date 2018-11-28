package main.java.tasche_packen.model;


import org.junit.Assert;
import org.junit.Test;

public class SubjectItemAssignmentTest {

    @Test (timeout = 1_000)
    public void getTodayRequiredItemsAsString() {
        // arrange
        Subject algorithmsAndDatastructures = new Subject("Algorithmen und Datenstrukturen");
        algorithmsAndDatastructures.addItem(Item.PencilCase);
        Subject operationsResearch = new Subject("Operations Research");
        operationsResearch.addItem(Item.Calculator);
        Subject statistics = new Subject("Wahrscheinlichkeitsrechnung und Statistik");
        statistics.addItem(Item.Notebook);
        SubjectItemAssignment sut = new SubjectItemAssignment(algorithmsAndDatastructures, operationsResearch, statistics);
        String  want = " Federmaeppchen Taschenrechner und Notebook";
        // act
        String have  = sut.getTodayRequiredItemsAsString();
        // assert
        Assert.assertEquals(want, have);
    }

    @Test (timeout = 1_000)
    public void getTodayRequiredItemsOneItemAsString() {
        // arrange
        Subject algorithmsAndDatastructures = new Subject("Algorithmen und Datenstrukturen");
        algorithmsAndDatastructures.addItem(Item.PencilCase);
        SubjectItemAssignment sut = new SubjectItemAssignment(algorithmsAndDatastructures);
        String  want = " Federmaeppchen";
        // act
        String have  = sut.getTodayRequiredItemsAsString();
        // assert
        Assert.assertEquals(want, have);
    }


    @Test (timeout = 1_000)
    public void getTodayRequiredItemsAsStringDoubleItem() {
        // arrange
        Subject algorithmsAndDatastructures = new Subject("Algorithmen und Datenstrukturen");
        algorithmsAndDatastructures.addItem(Item.PencilCase);
        Subject operationsResearch = new Subject("Operations Research");
        operationsResearch.addItem(Item.Calculator);
        Subject statistics = new Subject("Wahrscheinlichkeitsrechnung und Statistik");
        statistics.addItem(Item.Calculator);
        SubjectItemAssignment sut = new SubjectItemAssignment(algorithmsAndDatastructures, operationsResearch, statistics);
        String  want = " Federmaeppchen und Taschenrechner";
        // act
        String have  = sut.getTodayRequiredItemsAsString();
        // assert
        Assert.assertEquals(want, have);
    }

    @Test (timeout = 1_000)
    public void deleteNotVisitedSubjects() {
        // arrange
        Subject algorithmsAndDatastructures = new Subject("Algorithmen und Datenstrukturen");
        algorithmsAndDatastructures.addItem(Item.PencilCase);
        Subject operationsResearch = new Subject("Operations Research");
        operationsResearch.addItem(Item.Calculator);
        Subject statistics = new Subject("Wahrscheinlichkeitsrechnung und Statistik");
        statistics.addItem(Item.Pad);
        SubjectItemAssignment sut = new SubjectItemAssignment(algorithmsAndDatastructures, operationsResearch, statistics);
        sut.deleteNotVisitedSubjects("Operations Research");
        String  want = " Federmaeppchen und Block";
        // act
        String have  = sut.getTodayRequiredItemsAsString();
        // assert
        Assert.assertEquals(want, have);
    }

    @Test (timeout = 1_000)
    public void GetSubjectsVisitedAsString() {
        // arrange
        Subject algorithmsAndDatastructures = new Subject("Algorithmen und Datenstrukturen");
        algorithmsAndDatastructures.addItem(Item.PencilCase);
        Subject operationsResearch = new Subject("Operations Research");
        operationsResearch.addItem(Item.Calculator);
        Subject statistics = new Subject("Wahrscheinlichkeitsrechnung und Statistik");
        statistics.addItem(Item.Pad);
        SubjectItemAssignment sut = new SubjectItemAssignment(algorithmsAndDatastructures, operationsResearch, statistics);
        sut.deleteNotVisitedSubjects("Operations Research");
        String  want = " Algorithmen und Datenstrukturen und Wahrscheinlichkeitsrechnung und Statistik";
        // act
        String have  = sut.getSubjectsVisitedAsString();
        // assert
        Assert.assertEquals(want, have);
    }


    @Test (timeout = 1_000)
    public void GetSubjectsVisitedOnlyOneSubjectAsString() {
        // arrange
        Subject algorithmsAndDatastructures = new Subject("Algorithmen und Datenstrukturen");
        algorithmsAndDatastructures.addItem(Item.PencilCase);
        SubjectItemAssignment sut = new SubjectItemAssignment(algorithmsAndDatastructures);;
        String  want = " Algorithmen und Datenstrukturen";
        // act
        String have  = sut.getSubjectsVisitedAsString();
        // assert
        Assert.assertEquals(want, have);
    }

    @Test (timeout = 1_000)
    public void GetSubjectsTodayAsString() {
        // arrange
        Subject algorithmsAndDatastructures = new Subject("Algorithmen und Datenstrukturen");
        algorithmsAndDatastructures.addItem(Item.PencilCase);
        Subject operationsResearch = new Subject("Operations Research");
        operationsResearch.addItem(Item.Calculator);
        Subject statistics = new Subject("Wahrscheinlichkeitsrechnung und Statistik");
        statistics.addItem(Item.Pad);
        SubjectItemAssignment sut = new SubjectItemAssignment(algorithmsAndDatastructures, operationsResearch, statistics);
        sut.deleteNotVisitedSubjects("Operations Research");
        String  want = " Algorithmen und Datenstrukturen Operations Research und Wahrscheinlichkeitsrechnung und Statistik";
        // act
        String have  = sut.getSubjectsTodayAsString();
        // assert
        Assert.assertEquals(want, have);
    }


    @Test (timeout = 1_000)
    public void GetSubjectsTodayOnlyOneSubjectAsString() {
        // arrange
        Subject algorithmsAndDatastructures = new Subject("Algorithmen und Datenstrukturen");
        algorithmsAndDatastructures.addItem(Item.PencilCase);
        SubjectItemAssignment sut = new SubjectItemAssignment(algorithmsAndDatastructures);
        sut.deleteNotVisitedSubjects("Algorithmen und Datenstrukturen");
        String  want = " Algorithmen und Datenstrukturen";
        // act
        String have  = sut.getSubjectsTodayAsString();
        // assert
        Assert.assertEquals(want, have);
    }

}
