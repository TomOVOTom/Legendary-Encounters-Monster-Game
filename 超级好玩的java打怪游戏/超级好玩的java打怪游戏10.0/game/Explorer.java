// Explorer.java
package game;

import java.util.Random;

public class Explorer {
    private static final String[] PET_NAMES = {
        "天龙", "风虎", "灵熊", "幻狼", "疾豹", "神鹰", "灵狐", "仙猫", "圣犬", "月兔",
        "火凤", "冰蛇", "雷狮", "土龟", "水鲤", "光鹿", "暗鸦", "风鹤", "火蜥", "冰狼"
    };

    public static void explore(Player player, Random random) {
        int explorationCost = 50; // Cost in gold to explore
        if (player.gold < explorationCost) {
            System.out.println("你的金币不足，无法进行探索。");
            return;
        }
        player.gold -= explorationCost;
        System.out.println("你开始探索周围的环境...");

        if (random.nextInt(100) < 15) { // 15%的几率找到宠物
            String petName = PET_NAMES[random.nextInt(PET_NAMES.length)];
            int hp = 50 + random.nextInt(151); // 50 to 200
            int attack = 30 + random.nextInt(31); // 30 to 60
            int defense = 5 + random.nextInt(6); // 5 to 10
            int intelligence = 10 + random.nextInt(11); // 10 to 20
            int stamina = 30 + random.nextInt(21); // 30 to 50
            int dodge = 55 + random.nextInt(55); // 55 to 110
            Pet newPet = new Pet(petName, hp, attack, defense, intelligence, stamina, dodge);
            System.out.println("你发现了一只宠物：" + newPet.name + "！");

            Pet existingPet = player.pets.stream().filter(p -> p.name.equals(petName)).findFirst().orElse(null);
            if (existingPet != null) {
                if (newPet.combatPower > existingPet.combatPower) {
                    player.gold += existingPet.combatPower;
                    player.pets.remove(existingPet);
                    player.pets.add(newPet);
                    System.out.println("你保留了新的宠物，旧的宠物被转换成了 " + existingPet.combatPower + " 金币。");
                } else {
                    player.gold += newPet.combatPower;
                    System.out.println("你保留了旧的宠物，新的宠物被转换成了 " + newPet.combatPower + " 金币。");
                }
            } else {
                player.pets.add(newPet);
            }
        } else {
            System.out.println("你没有发现任何有趣的东西。");
        }
    }
}