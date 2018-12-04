package tasche_packen.model;

import java.util.*;

public class Subjects {
    private final String name;
    private final Set<Item> requiredItems;

    public Subjects(String name) {
        this.requiredItems = new HashSet<>();
        this.name = name;
    }

    public Subjects addItem(Item... items) {
        for(int i = 0; i < items.length; i++) {
            requiredItems.add(items[i]);
        }
        return this;
    }

    public void removeItem(Item item) {
        requiredItems.remove(item);
    }

    public Set<Item> getRequiredItems(){
        return requiredItems;
    }

    public Set<String> getItemsAsStringSet() {
        Set<String> items = new HashSet<>();
        for(Iterator<Item> iterator = requiredItems.iterator(); iterator.hasNext();) {
            items.add(iterator.next().getName());
        }
        return items;
    }

    public String getName() {
        return name;
    }

}



