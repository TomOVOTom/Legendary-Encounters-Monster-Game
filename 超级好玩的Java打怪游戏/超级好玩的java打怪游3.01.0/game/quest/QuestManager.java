package game.quest;

import game.monster.Monster;
import game.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestManager {
    private List<Quest> availableQuests = new ArrayList<>();
    private Random random = new Random();

    public QuestManager() {
        QuestGenerator.generateQuests(availableQuests);
    }

    public List<Quest> getAvailableQuests() {
        return availableQuests;
    }

    public void completeQuest(int questId, Player player) {
        for (Quest quest : availableQuests) {
            if (quest.getId() == questId && (!quest.isCompleted() )) {
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
                System.out.println("任务完成: " + quest.getName() + "。奖励: " + quest.getReward());
                break;
            }
        }
    }

    public void completeQuestForMonster(Player player, Monster monster) {
        QuestCompleter.completeQuestForMonster(this, player, monster);
    }
}