// game/quest/QuestManager.java
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
        availableQuests.add(new Quest(1, "Main Quest 1", "Defeat the dragon", Quest.QuestType.MAIN, "100 XP, 50 Gold"));
        availableQuests.add(new Quest(2, "Main Quest 2", "Rescue the princess", Quest.QuestType.MAIN, "150 XP, 70 Gold"));
        availableQuests.add(new Quest(3, "Main Quest 3", "Find the ancient artifact", Quest.QuestType.MAIN, "200 XP, 100 Gold"));
        availableQuests.add(new Quest(4, "Side Quest 1", "Collect 10 herbs", Quest.QuestType.SIDE, "200 XP, 100 Gold"));
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