// File: src/game/exploration/Explorer.java
package game.exploration;

import game.pet.Pet;
import game.player.Player;
import game.quest.QuestManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Explorer {
    private static final String[] PET_NAMES = {
            "天龙", "风虎", "灵熊", "幻狼", "疾豹", "神鹰", "灵狐", "仙猫", "圣犬", "月兔",
            "火凤", "冰蛇", "雷狮", "土龟", "水鲤", "光鹿", "暗鸦", "风鹤", "火蜥", "冰狼"
    };

    private static final List<Map> MAPS = Arrays.asList(
            new Map("绿野仙踪", 1, "一个充满神秘生物的绿色森林。"),
            new Map("沙漠遗迹", 2, "古老的沙漠遗迹，隐藏着无数宝藏。"),
            new Map("冰封之地", 3, "寒冷的冰封之地，只有最强者才能生存。")
    );

    public static void explore(Player player, Random random, QuestManager questManager) {
        int explorationCost = 500; // Cost in gold to explore
        if (player.attributes.gold < explorationCost) {
            System.out.println("你的金币不足，无法进行探索。");

            return;
        }
        player.attributes.gold -= explorationCost;
        System.out.println("你开始探索周围的环境...");

        if (random.nextInt(100) < 50) { // 50%的几率找到宠物
            String petName = PET_NAMES[random.nextInt(PET_NAMES.length)];
            int hp = 150 + random.nextInt(351); // 150 to 500
            int attack = 30 + random.nextInt(51); // 30 to 70
            int defense = 15 + random.nextInt(36); // 15 to 50
            int intelligence = 10 + random.nextInt(51); // 10 to 20
            int stamina = 300 + random.nextInt(201); // 30 to 50
            int dodge = 55 + random.nextInt(55); // 55 to 110
            Pet newPet = new Pet(petName, hp, attack, defense, intelligence, stamina, dodge);
            System.out.println("你发现了一只宠物：" + newPet.name + "！");

            Pet existingPet = player.pets.stream().filter(p -> p.name.equals(petName)).findFirst().orElse(null);
            if (existingPet != null) {
                if (newPet.combatPower > existingPet.combatPower) {
                    player.attributes.gold += existingPet.combatPower;
                    player.pets.remove(existingPet);
                    player.pets.add(newPet);
                    System.out.println("你保留了新的宠物，旧的宠物被转换成了 " + existingPet.combatPower + " 金币。");
                } else {
                    player.attributes.gold += newPet.combatPower;
                    System.out.println("你保留了旧的宠物，新的宠物被转换成了 " + newPet.combatPower + " 金币。");
                }
            } else {
                player.pets.add(newPet);
            }
        } else {
            System.out.println("你没有发现任何有趣的东西。");
        }
        // Add dungeon exploration option
        if (random.nextInt(100) < 30) { // 30% chance to find a dungeon
            DungeonExplorer.exploreDungeon(player, random, questManager);
        }
    }
}