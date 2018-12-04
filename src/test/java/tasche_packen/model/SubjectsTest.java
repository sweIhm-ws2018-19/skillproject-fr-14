package tasche_packen.model;


import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SubjectsTest {


    @Test(timeout = 1_000)
    public void removeItem() {
        // arrange
        Subjects sut = new Subjects("Operations Research");
        sut.addItem(Item.CALCULATOR);
        sut.addItem(Item.PAD);
        sut.addItem(Item.NOTEBOOK);
        sut.removeItem(Item.CALCULATOR);
        Set<Item> want = new HashSet<Item>();

        want.add(Item.PAD);
        want.add(Item.NOTEBOOK);
        // act
        Set<Item> have  = sut.getRequiredItems();
        // assert
        Assert.assertEquals(want, have);
    }


    @Test(timeout = 1_000)
    public void removeNotExistingItem() {
        // arrange
        Subjects sut = new Subjects("Operations Research");
        sut.addItem(Item.CALCULATOR);
        sut.addItem(Item.PAD);
        sut.removeItem(Item.PENCIL_CASE);
        Set<Item> want = new HashSet<Item>();
        want.add(Item.CALCULATOR);
        want.add(Item.PAD);

        // act
        Set<Item> have  = sut.getRequiredItems();
        // assert
        Assert.assertEquals(want, have);
    }

}
