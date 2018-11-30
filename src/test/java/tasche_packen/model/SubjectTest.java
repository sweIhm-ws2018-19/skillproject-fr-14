package tasche_packen.model;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SubjectTest {


    @Test(timeout = 1_000)
    public void removeItem() {
        // arrange
        Subject sut = new Subject("Operations Research");
        sut.addItem(Item.CALCULATOR);
        sut.addItem(Item.PAD);
        sut.addItem(Item.NOTEBOOK);
        sut.removeItem(Item.CALCULATOR);
        List<Item> want = new ArrayList<Item>();

        want.add(Item.PAD);
        want.add(Item.NOTEBOOK);
        // act
        List<Item> have  = sut.getRequiredItems();
        // assert
        Assert.assertEquals(want, have);
    }


    @Test(timeout = 1_000)
    public void removeNotExistingItem() {
        // arrange
        Subject sut = new Subject("Operations Research");
        sut.addItem(Item.CALCULATOR);
        sut.addItem(Item.PAD);
        sut.removeItem(Item.PENCIL_CASE);
        List<Item> want = new ArrayList<Item>();
        want.add(Item.CALCULATOR);
        want.add(Item.PAD);

        // act
        List<Item> have  = sut.getRequiredItems();
        // assert
        Assert.assertEquals(want, have);
    }

}
