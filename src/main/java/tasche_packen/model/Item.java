package tasche_packen.model;

public enum Item {
    NOTEBOOK("NOTEBOOK"), PENCIL_CASE("Federmaeppchen"), PAD("Block"), CALCULATOR("Taschenrechner");

    private final String name;

    Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
