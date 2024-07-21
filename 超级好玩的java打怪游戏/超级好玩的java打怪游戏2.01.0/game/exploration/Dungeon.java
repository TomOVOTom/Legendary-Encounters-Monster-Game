// File: src/game/exploration/Dungeon.java
package game.exploration;

import game.equipment.Equipment;
import game.monster.Monster;

import java.util.List;

public class Dungeon {
    private String name;
    private int difficulty;
    private String description;
    private List<Monster> monsters;
    private Equipment reward;

    public Dungeon(String name, int difficulty, String description, List<Monster> monsters, Equipment reward) {
        this.name = name;
        this.difficulty = difficulty;
        this.description = description;
        this.monsters = monsters;
        this.reward = reward;
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getDescription() {
        return description;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Equipment getReward() {
        return reward;
    }
}