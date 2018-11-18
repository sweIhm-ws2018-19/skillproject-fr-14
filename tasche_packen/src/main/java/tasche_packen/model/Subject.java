package main.java.tasche_packen.model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final String name;
    private final List<Item> requiredItems;

    public Subject(String name) {
        requiredItems = new ArrayList<>();
        this.name = name;
    }

    public void addItem(Item item) {
        requiredItems.add(item);
    }

    public void removeItem(String item) {
        requiredItems.remove(item);
    }


    public List<Item> getRequiredItems() {
        return requiredItems;
    }

    public String getName() {
        return name;
    }

}



