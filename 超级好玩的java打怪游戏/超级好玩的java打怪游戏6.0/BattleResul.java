package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleResul {
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
            player.gold += goldGain;
            player.skillPoints += skillPointsGain;
            player.hp += 10; // 战斗胜利基础生命值恢复

            // 在这里还原玩家的攻击力
            if (player.isStone) {
                player.attack = player.originalAttack;  // 恢复到石化前的攻击力
                System.out.println("石化效果解除！你的攻击力恢复正常。");
                player.isStone = false; // 解除石化状态
            }


            dropItems(player);


            // 特殊事件触发逻辑（示例）
            if (monster.name.equals("黑暗巨龙")) {
                System.out.println("你解锁了黑暗森林的秘密入口！");
//                 unlockSecretEntrance(); // 假设的方法，用于解锁新地图或事件
            }

        } else {
            System.out.println("你被 " + monster.name + " 打败了！");
        }
    }


    // 掉落物品逻辑
    private static void dropItems(Player player) {
        List<Item> possibleDrops = new ArrayList<>();
        possibleDrops.add(new Item("治疗药水", "healing", 50, 10, 1));
        possibleDrops.add(new Item("防御药水", "defense", 10, 15, 1));
        possibleDrops.add(new Item("体力药水", "stamina", 20, 15, 1));
        Random random = new Random();
        for (Item item : possibleDrops) {
            // 假设每个物品有50%的掉落概率
            if (random.nextDouble() < 0.5) {
                player.addToInventory(item);
                System.out.println("掉落了物品：" + item.getName());
            }
        }
    }
}
