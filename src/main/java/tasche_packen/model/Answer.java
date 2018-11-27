package main.java.tasche_packen.model;

public  enum Answer {
    Yes("ja"), No("nein");

    String name;

     Answer(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
