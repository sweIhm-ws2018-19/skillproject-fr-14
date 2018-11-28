package main.java.tasche_packen.model;

public enum Item {
    Notebook("Notebook"), PencilCase("Federmaeppchen"), Pad("Block"), Calculator("Taschenrechner");

    private final String name;

    Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
