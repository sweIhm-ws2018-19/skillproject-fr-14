package tasche_packen.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tasche_packen.model.GoodbyeSentence;
import tasche_packen.model.Item;
import tasche_packen.model.Subject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GoodbyeSentenceGeneratorTest {

    @Test(timeout = 1_000)
    public void randomNumberRange() {
        // arrange
        GoodbyeSentenceGenerator sut = new GoodbyeSentenceGenerator();

        // act
        GoodbyeSentence have = sut.generateGoodbyeSentence();
        // assert
        Assert.assertTrue(GoodbyeSentenceGenerator.getStartIndex() <= have.getId() && GoodbyeSentenceGenerator.getEndIndex() >= have.getId());

    }


    @Test(timeout = 1_000)
    public void getText() {
        // arrange
        GoodbyeSentenceGenerator sut = new GoodbyeSentenceGenerator();

        // act
        GoodbyeSentence sentence = sut.generateGoodbyeSentence();
        String have = sentence.getText();
        // assert
        Assert.assertEquals(have,
                sentence.getId() == 1 ? "Auf Wiedersehen" :
                        sentence.getId() == 2 ? "Bis Morgen" :
                                sentence.getId() == 3 ? "Machs gut" : "Ich wuensche dir einen schoenen Tag");


    }
}