package tasche_packen.model;


import tasche_packen.model.Item;
import tasche_packen.model.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SubjectTest {


    @Test(timeout = 1_000)
    public void removeItem() {
        // arrange
        Subject sut = new Subject("Operations Research");
        sut.addItem(Item.Calculator);
        sut.addItem(Item.Pad);
        sut.addItem(Item.Notebook);
        sut.removeItem(Item.Calculator);
        List<Item> want = new ArrayList<Item>();

        want.add(Item.Pad);
        want.add(Item.Notebook);
        // act
        List<Item> have  = sut.getRequiredItems();
        // assert
        Assert.assertEquals(want, have);
    }


    @Test(timeout = 1_000)
    public void removeNotExistingItem() {
        // arrange
        Subject sut = new Subject("Operations Research");
        sut.addItem(Item.Calculator);
        sut.addItem(Item.Pad);
        sut.removeItem(Item.PencilCase);
        List<Item> want = new ArrayList<Item>();
        want.add(Item.Calculator);
        want.add(Item.Pad);

        // act
        List<Item> have  = sut.getRequiredItems();
        // assert
        Assert.assertEquals(want, have);
    }

}
