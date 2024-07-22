// game/exploration/Map.java
package game.exploration;

public class Map {
    private String name;
    private int difficulty;
    private String story;

    public Map(String name, int difficulty, String story) {
        this.name = name;
        this.difficulty = difficulty;
        this.story = story;
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getStory() {
        return story;
    }
}