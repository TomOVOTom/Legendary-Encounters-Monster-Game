// File: src/game/battle/BattleResult.java
package game.battle;

import game.item.Item;
import game.monster.Monster;
import game.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleResult {
    // 处理战斗结果的方法
    public static void handleBattleResult(Player player, Monster monster) {
        Random random = new Random();

        if (monster.hp <= 0) {
            System.out.println("你战胜了 " + monster.name + "！ ");
            // 动态调整经验值、金币和技能点的获得量
            int expGain = monster.level * 10 + random.nextInt(5); // 基础经验值加随机额外经验
            int goldGain = monster.gold; // 金币奖励
            int skillPointsGain = 5 + monster.level * 3; // 基础技能点加额外技能点

            player.gainExp(expGain);
            player.attributes.gold += goldGain;
            player.attributes.skillPoints += skillPointsGain;
            player.attributes.hp += 10; // 战斗胜利基础生命值恢复

            // 让宠物获得经验值
            if (player.equippedPet != null) {
                player.equippedPet.gainExp(expGain);
            }

            // 这里还原玩家的攻击力
            if (player.isStone) {
                player.attributes.attack = player.originalAttack;  // 恢复到石化前的攻击力
                System.out.println("石化效果解除！你的攻击力恢复正常。");
                player.isStone = false; // 解除石化状态
            }

            dropItems(player, monster);

        } else {
            System.out.println("你被 " + monster.name + " 打败了！");
        }
    }

    // 掉落物品逻辑
    public static void dropItems(Player player, Monster monster) {
        List<Item> possibleDrops = new ArrayList<>();
        possibleDrops.add(new Item("治疗药水", "healing", 50, 10, 1));
        possibleDrops.add(new Item("防御药水", "defense", 10, 15, 1));
        possibleDrops.add(new Item("体力药水", "stamina", 20, 15, 1));
        Random random = new Random();
        double combatPower = monster.calculateCombatPower();
        double dropRate = Math.min(1.0, combatPower / 1000); // 假设战斗力1000时掉落率为100%

        for (Item item : possibleDrops) {
            if (random.nextDouble() < dropRate) {
                player.inventory.addToInventory(item);
                System.out.println("掉落了物品：" + item.getName());
            }
        }
    }
}