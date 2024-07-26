package game.base;

public class Facility {
    private String name;
    private String description;

    public Facility(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}