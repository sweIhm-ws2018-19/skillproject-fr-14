package tasche_packen.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtitlitiesTest {

    @Test
    public void checkSubjectToBeChangedIsNull() {
        assertNull(Utitlities.getSubjectToBeChanged());
    }

    @Test
    public void testSubjectMapperAlgodat() {
        String want = "Algorithmen und Datenstrukturen";
        String have = Utitlities.subjectMapper("Algorithmen");
        assertEquals(want, have);
    }

    @Test
    public void testSubjectMapperStatistik() {
        String want = "Wahrscheinlichkeitsrechnung und Statistik";
        String have = Utitlities.subjectMapper("Wahrscheinlichkeitsrechnung");
        assertEquals(want, have);
    }

    @Test
    public void testSubjectMapperOperations() {
        String want = "Operations Research";
        String have = Utitlities.subjectMapper("Operations");
        assertEquals(want, have);
    }

    @Test
    public void testSubjectMapperDefault() {
        String want = "Netzwerke";
        String have = Utitlities.subjectMapper("Netzwerke");
        assertEquals(want, have);
    }




}