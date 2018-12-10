package tasche_packen.model;

public  enum Answer {
    YES("ja"), NO("nein");

    String name;

     Answer(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
