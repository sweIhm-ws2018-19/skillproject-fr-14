package tasche_packen.controller;

import tasche_packen.model.GoodbyeSentence;

import java.util.Arrays;
import java.util.stream.Stream;

public class GoodbyeSentenceGenerator {
    private static final int START_INDEX = 1;
    private static final int END_INDEX = GoodbyeSentence.getGoodbyeSentencesSize();

    public static GoodbyeSentence generateGoodbyeSentence() {
        final int sentenceIndex = (int) (Math.random()*(END_INDEX ))+1;
        System.out.println(sentenceIndex);
        return Stream.of(GoodbyeSentence.values()).filter(sentence -> sentence.getId() == sentenceIndex).findFirst().get();
    }

public static String GoodbyeSentenceAsString() {
        return generateGoodbyeSentence().getText();
}

    public static int getStartIndex() {
        return START_INDEX;
    }

    public static int getEndIndex() {
        return END_INDEX;
    }
}
