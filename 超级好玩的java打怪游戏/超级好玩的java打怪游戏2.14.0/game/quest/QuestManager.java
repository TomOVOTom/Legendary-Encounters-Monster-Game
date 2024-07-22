// File: src/game/quest/QuestManager.java
package game.quest;

import game.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestManager {
    private List<Quest> availableQuests = new ArrayList<>();
    private Random random = new Random();

    public QuestManager() {
        generateQuests();
    }

    private void generateQuests() {
        availableQuests.add(new Quest(1, "Main Quest 1", "Defeat the ghost", Quest.QuestType.MAIN, "100 XP, 50 Gold"));
        availableQuests.add(new Quest(2, "Main Quest 2", "Defeat the desert scorpion", Quest.QuestType.MAIN, "150 XP, 70 Gold"));
        availableQuests.add(new Quest(3, "Main Quest 3", "Defeat the fire giant", Quest.QuestType.MAIN, "200 XP, 100 Gold"));
        availableQuests.add(new Quest(4, "Main Quest 4", "Defeat the frost giant", Quest.QuestType.MAIN, "250 XP, 120 Gold"));
        availableQuests.add(new Quest(5, "Main Quest 5", "Defeat the deep sea beast", Quest.QuestType.MAIN, "300 XP, 150 Gold"));
        availableQuests.add(new Quest(6, "Main Quest 6", "Defeat the dragon", Quest.QuestType.MAIN, "350 XP, 200 Gold"));
        availableQuests.add(new Quest(7, "Main Quest 7", "Defeat the nightmare beast", Quest.QuestType.MAIN, "400 XP, 250 Gold"));
        availableQuests.add(new Quest(8, "Main Quest 8", "Defeat the hell beast", Quest.QuestType.MAIN, "450 XP, 300 Gold"));
        availableQuests.add(new Quest(9, "Main Quest 9", "Defeat the ultimate beast", Quest.QuestType.MAIN, "500 XP, 350 Gold"));
        availableQuests.add(new Quest(10, "Side Quest 1", "Collect 10 herbs", Quest.QuestType.SIDE, "200 XP, 100 Gold"));
        // Add more quests
    }

    public List<Quest> getAvailableQuests() {
        return availableQuests;
    }

    public void completeQuest(int questId, Player player) {
        for (Quest quest : availableQuests) {
            if (quest.getId() == questId && !quest.isCompleted()) {
                quest.completeQuest();
                // Logic to give reward to player
                String reward = quest.getReward();
                String[] rewards = reward.split(", ");
                for (String r : rewards) {
                    if (r.endsWith("XP")) {
                        int xp = Integer.parseInt(r.split(" ")[0]);
                        player.gainExp(xp);
                    } else if (r.endsWith("Gold")) {
                        int gold = Integer.parseInt(r.split(" ")[0]);
                        player.attributes.gold += gold;
                    }
                }
                System.out.println("Quest completed: " + quest.getName() + ". Reward: " + quest.getReward());
                break;
            }
        }
    }
}