package tasche_packen.model;

public enum GoodbyeSentence {
    GOODBYE(1, "Auf Wiedersehen"), UNTIL_TOMORROW(2, "Bis Morgen"), HAVE_A_GOOD_TIME(3, "Machs gut"), HAVE_A_NICE_DAY(4, "Ich wuensche dir einen schoenen Tag");;

    private static final int GOODBYE_SENTENCES_SIZE = GoodbyeSentence.values().length;
    private final int id;
    private final String text;
    GoodbyeSentence(int id, String text) {
        this.id = id;
        this.text = text;
    }


    public static int getGoodbyeSentencesSize() {
        return GOODBYE_SENTENCES_SIZE;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
