// game/base/Base.java
package game.base;

import game.player.Player;
import game.quest.Quest;
import game.quest.QuestManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Base implements Serializable {
    private int level;
    private int goldCost;
    private Map<Integer, Facility[]> unlockedFeatures;
    private QuestManager questManager;

    public Base() {
        this.level = 1;
        this.goldCost = 200;
        this.unlockedFeatures = new HashMap<>();
        this.questManager = new QuestManager();
        initializeFeatures();
    }

    private void initializeFeatures() {
        unlockedFeatures.put(1, new Facility[]{new Facility("Rest", "Rest to restore health and stamina")});
        unlockedFeatures.put(2, new Facility[]{new Facility("Rest", "Rest to restore health and stamina"), new Facility("Storage", "Store items safely")});
        unlockedFeatures.put(3, new Facility[]{new Facility("Rest", "Rest to restore health and stamina"), new Facility("Storage", "Store items safely"), new Facility("Workshop", "Upgrade equipment and craft items")});
        unlockedFeatures.put(4, new Facility[]{new Facility("Rest", "Rest to restore health and stamina"), new Facility("Storage", "Store items safely"), new Facility("Workshop", "Upgrade equipment and craft items"), new Facility("Market", "Buy and sell items")});
        unlockedFeatures.put(5, new Facility[]{new Facility("Rest", "Rest to restore health and stamina"), new Facility("Storage", "Store items safely"), new Facility("Workshop", "Upgrade equipment and craft items"), new Facility("Market", "Buy and sell items"), new Facility("Training", "Train to improve skills")});
        unlockedFeatures.put(6, new Facility[]{new Facility("Rest", "Rest to restore health and stamina"), new Facility("Storage", "Store items safely"), new Facility("Workshop", "Upgrade equipment and craft items"), new Facility("Market", "Buy and sell items"), new Facility("Training", "Train to improve skills"), new Facility("Defense", "Defensive structures to protect the base")});
        unlockedFeatures.put(7, new Facility[]{new Facility("Rest", "Rest to restore health and stamina"), new Facility("Storage", "Store items safely"), new Facility("Workshop", "Upgrade equipment and craft items"), new Facility("Market", "Buy and sell items"), new Facility("Training", "Train to improve skills"), new Facility("Defense", "Defensive structures to protect the base"), new Facility("Research", "Research new technologies and abilities")});
        unlockedFeatures.put(8, new Facility[]{new Facility("Rest", "Rest to restore health and stamina"), new Facility("Storage", "Store items safely"), new Facility("Workshop", "Upgrade equipment and craft items"), new Facility("Market", "Buy and sell items"), new Facility("Training", "Train to improve skills"), new Facility("Defense", "Defensive structures to protect the base"), new Facility("Research", "Research new technologies and abilities"), new Facility("Pet Area", "Area for pet care and training")});
        unlockedFeatures.put(9, new Facility[]{new Facility("Rest", "Rest to restore health and stamina"), new Facility("Storage", "Store items safely"), new Facility("Workshop", "Upgrade equipment and craft items"), new Facility("Market", "Buy and sell items"), new Facility("Training", "Train to improve skills"), new Facility("Defense", "Defensive structures to protect the base"), new Facility("Research", "Research new technologies and abilities"), new Facility("Pet Area", "Area for pet care and training"), new Facility("Social Center", "Interact with other characters")});
        unlockedFeatures.put(10, new Facility[]{new Facility("Rest", "Rest to restore health and stamina"), new Facility("Storage", "Store items safely"), new Facility("Workshop", "Upgrade equipment and craft items"), new Facility("Market", "Buy and sell items"), new Facility("Training", "Train to improve skills"), new Facility("Defense", "Defensive structures to protect the base"), new Facility("Research", "Research new technologies and abilities"), new Facility("Pet Area", "Area for pet care and training"), new Facility("Social Center", "Interact with other characters"), new Facility("Quest Board", "Accept and complete quests")});
        unlockedFeatures.put(11, new Facility[]{new Facility("Rest", "Rest to restore health and stamina"), new Facility("Storage", "Store items safely"), new Facility("Workshop", "Upgrade equipment and craft items"), new Facility("Market", "Buy and sell items"), new Facility("Training", "Train to improve skills"), new Facility("Defense", "Defensive structures to protect the base"), new Facility("Research", "Research new technologies and abilities"), new Facility("Pet Area", "Area for pet care and training"), new Facility("Social Center", "Interact with other characters"), new Facility("Quest Board", "Accept and complete quests"), new Facility("Customization", "Customize the base with collected items")});
        unlockedFeatures.put(12, new Facility[]{new Facility("Rest", "Rest to restore health and stamina"), new Facility("Storage", "Store items safely"), new Facility("Workshop", "Upgrade equipment and craft items"), new Facility("Market", "Buy and sell items"), new Facility("Training", "Train to improve skills"), new Facility("Defense", "Defensive structures to protect the base"), new Facility("Research", "Research new technologies and abilities"), new Facility("Pet Area", "Area for pet care and training"), new Facility("Social Center", "Interact with other characters"), new Facility("Quest Board", "Accept and complete quests"), new Facility("Customization", "Customize the base with collected items"), new Facility("Production", "Produce resources regularly")});
    }

    public void handleQuests(Scanner scanner, Player player) {
        System.out.println("Available Quests:");
        for (Quest quest : questManager.getAvailableQuests()) {
            System.out.println(quest.getId() + ". " + quest.getName() + " - " + quest.getDescription());
        }
        System.out.println("Enter quest ID to complete or 0 to exit:");
        int questId = scanner.nextInt();
        if (questId != 0) {
            questManager.completeQuest(questId, player);
        }
    }

    public int getLevel() {
        return level;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public void upgrade() {
        this.level++;
        this.goldCost += 100 * level; // Increase cost for next level
    }

    public Facility[] getUnlockedFeatures() {
        return unlockedFeatures.getOrDefault(level, new Facility[]{});
    }

    public int getRestoredHp() {
        return 50 + (level - 1) * 10; // Increase HP restoration with level
    }

    public int getRestoredStamina() {
        return 20 + (level - 1) * 5; // Increase Stamina restoration with level
    }
}