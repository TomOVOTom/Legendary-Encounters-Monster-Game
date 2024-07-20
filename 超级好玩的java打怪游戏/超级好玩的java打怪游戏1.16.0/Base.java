package game.base;

import java.util.HashMap;
import java.util.Map;

public class Base {
    private int level;
    private int goldCost;
    private Map<Integer, String[]> unlockedFeatures;

    public Base() {
        this.level = 1;
        this.goldCost = 200;
        this.unlockedFeatures = new HashMap<>();
        initializeFeatures();
    }

    private void initializeFeatures() {
        unlockedFeatures.put(1, new String[]{"Rest"});
        unlockedFeatures.put(2, new String[]{"Rest", "Storage"});
        unlockedFeatures.put(3, new String[]{"Rest", "Storage", "Workshop"});
        unlockedFeatures.put(4, new String[]{"Rest", "Storage", "Workshop", "Market"});
        unlockedFeatures.put(5, new String[]{"Rest", "Storage", "Workshop", "Market", "Training"});
        unlockedFeatures.put(6, new String[]{"Rest", "Storage", "Workshop", "Market", "Training", "Defense"});
        unlockedFeatures.put(7, new String[]{"Rest", "Storage", "Workshop", "Market", "Training", "Defense", "Research"});
        unlockedFeatures.put(8, new String[]{"Rest", "Storage", "Workshop", "Market", "Training", "Defense", "Research", "Pet Area"});
        unlockedFeatures.put(9, new String[]{"Rest", "Storage", "Workshop", "Market", "Training", "Defense", "Research", "Pet Area", "Social Center"});
        unlockedFeatures.put(10, new String[]{"Rest", "Storage", "Workshop", "Market", "Training", "Defense", "Research", "Pet Area", "Social Center", "Quest Board"});
        unlockedFeatures.put(11, new String[]{"Rest", "Storage", "Workshop", "Market", "Training", "Defense", "Research", "Pet Area", "Social Center", "Quest Board", "Customization"});
        unlockedFeatures.put(12, new String[]{"Rest", "Storage", "Workshop", "Market", "Training", "Defense", "Research", "Pet Area", "Social Center", "Quest Board", "Customization", "Production"});
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

    public String[] getUnlockedFeatures() {
        return unlockedFeatures.getOrDefault(level, new String[]{});
    }

    public int getRestoredHp() {
        return 50 + (level - 1) * 10; // Increase HP restoration with level
    }

    public int getRestoredStamina() {
        return 20 + (level - 1) * 5; // Increase Stamina restoration with level
    }
}