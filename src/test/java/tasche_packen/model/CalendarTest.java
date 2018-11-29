package tasche_packen.model;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CalendarTest {

    @Test (timeout = 1_000)
    public void getTodayLectures() {
        // arrange
        List<String> want = new ArrayList<>();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int day = calendar.get(java.util.Calendar.DAY_OF_WEEK);
        switch(day) {
            case java.util.Calendar.MONDAY:
                want.add("Netzwerke");
                want.add("Datenbanksysteme");
                break;
            case java.util.Calendar.TUESDAY:
                want.add("Software Engineering");
                want.add("Numerische Mathematik");
                want.add("Datenbanksysteme");
                break;
            case java.util.Calendar.WEDNESDAY:
                want.add("Numerische Mathematik");
                break;
            case java.util.Calendar.THURSDAY:
                want.add("Algorithmen und Datenstrukturen");
                want.add("Wahrscheinlichkeitstheorie und Statistik");
                break;
            case java.util.Calendar.FRIDAY:
                want.add("Software Engineering");
                want.add("Wahrscheinlichkeitstheorie und Statistik");
                want.add("Algorithmen und Datenstrukturen");
                break;
        }
        // act
        List<String> have  = new Calendar().getTodayLectures();
        // assert
        Assert.assertEquals(want, have);
    }


}
