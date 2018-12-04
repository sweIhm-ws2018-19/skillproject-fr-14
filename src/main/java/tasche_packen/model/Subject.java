package tasche_packen.model;

import java.util.HashSet;

public class Subject {

    private String name;
    private HashSet<String> items;

    public Subject(String name) {
        this.name = name;
        this.items = new HashSet<>();
    }

    public String getName(){
        return name;
    }

    public HashSet<String> getItems() {
        return items;
    }

    public Subject addItem(String... items) {
        for(int i = 0; i < items.length; i++) {
            this.items.add(items[i]);
        }
        return this;
    }

    public String removeItem(String item) {
        this.items.remove(item);
        return item;
    }
}
