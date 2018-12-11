package tasche_packen.model;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class SubjectTest {

    @Test
    public void testName(){
        Subject sut = new Subject("subject");
        String want = "subject";
        String have = sut.getName();
        assertEquals(want, have);
    }

    @Test
    public void testAdd() {
        Subject sut = new Subject("subject");
        HashSet<String> want = new HashSet<>();
        want.add("first");
        want.add("second");
        sut.addItem("first", "second");
        assertEquals(want, sut.getItems());
    }

    @Test
    public void testRemove() {
        Subject sut = new Subject("subject");
        sut.addItem("first");
        sut.removeItem("first");
        assertTrue(sut.getItems().isEmpty());
    }


}