package game.quest;

import java.io.Serializable;

public class Quest implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum QuestType {
        MAIN, SIDE, DAILY, RANDOM_EVENT, HIDDEN
    }

    private int id;
    private String name;
    private String description;
    private QuestType type;
    private String reward;
    private boolean isCompleted;

    public Quest(int id, String name, String description, QuestType type, String reward) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.reward = reward;
        this.isCompleted = false;
    }

    public void completeQuest() {
        this.isCompleted = true;
        // Logic to give reward to player
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public QuestType getType() {
        return type;
    }

    public String getReward() {
        return reward;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(QuestType type) {
        this.type = type;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}