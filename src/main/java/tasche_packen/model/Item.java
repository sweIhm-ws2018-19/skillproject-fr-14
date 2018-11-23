package main.java.tasche_packen.model;

public enum Item {
    Notebook("Notebook"), PencilCase("Federmäppchen"), Pad("Block"), Calcuator("Taschenrechner");

    private final String name;

    Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
