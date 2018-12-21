package tasche_packen.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilitiesTest {

    @Test
    public void checkSubjectToBeChangedIsNull() {
        assertNull(Utilities.getSubjectToBeChanged());
    }

    @Test
    public void testSubjectMapperAlgodat() {
        String want = "Algorithmen und Datenstrukturen";
        String have = Utilities.subjectMapper("Algorithmen");
        assertEquals(want, have);
    }

    @Test
    public void testSubjectMapperStatistik() {
        String want = "Wahrscheinlichkeitsrechnung und Statistik";
        String have = Utilities.subjectMapper("Wahrscheinlichkeitsrechnung");
        assertEquals(want, have);
    }

    @Test
    public void testSubjectMapperOperations() {
        String want = "Operations Research";
        String have = Utilities.subjectMapper("Operations");
        assertEquals(want, have);
    }

    @Test
    public void testSubjectMapperDefault() {
        String want = "Netzwerke";
        String have = Utilities.subjectMapper("Netzwerke");
        assertEquals(want, have);
    }


}