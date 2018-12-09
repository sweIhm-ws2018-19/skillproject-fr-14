package tasche_packen.controller;

import tasche_packen.model.GoodbyeSentence;

import java.util.Optional;
import java.util.stream.Stream;

public class GoodbyeSentenceGenerator {
    private static final int START_INDEX = 1;
    private static final int END_INDEX = GoodbyeSentence.getGoodbyeSentencesSize();

    public static GoodbyeSentence generateGoodbyeSentence() {
        final int sentenceIndex = (int) (Math.random()*(END_INDEX ))+1;
        Optional<GoodbyeSentence> goodbyeSentence = Stream.of(GoodbyeSentence.values()).filter(sentence -> sentence.getId() == sentenceIndex).findFirst();
        return goodbyeSentence.isPresent() ? goodbyeSentence.get() : GoodbyeSentence.valueOf("HAVE_A_NICE_DAY");
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
