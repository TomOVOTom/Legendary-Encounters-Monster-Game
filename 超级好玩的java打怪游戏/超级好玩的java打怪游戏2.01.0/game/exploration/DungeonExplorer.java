// File: src/game/exploration/DungeonExplorer.java
package game.exploration;

import game.battle.Attack;
import game.battle.BattleManager;
import game.equipment.Equipment;
import game.monster.Monster;
import game.player.Player;
import game.quest.QuestManager;

import java.util.Random;
import java.util.Scanner;

public class DungeonExplorer {
    public static void exploreDungeon(Player player, Random random, QuestManager questManager) {
        Scanner scanner = new Scanner(System.in);
        Dungeon dungeon = DungeonRegistry.DUNGEONS.get(random.nextInt(DungeonRegistry.DUNGEONS.size()));
        System.out.println("你进入了副本：" + dungeon.getName() + "。难度：" + dungeon.getDifficulty());
        System.out.println("描述：" + dungeon.getDescription());

        // 随机选择一个怪兽
        Monster monster = dungeon.getMonsters().get(random.nextInt(dungeon.getMonsters().size()));
        monster.maxHp = monster.hp;
        System.out.println("你遇到了怪兽：" + monster.name);
        System.out.println("请选择战斗模式：1. 自动战斗 2. 手动战斗");
        int battleMode = scanner.nextInt();
        Attack attack = new Attack(player);
        if (battleMode == 1) {
            while (monster.hp > 0 && player.attributes.hp > 0) {
                System.out.println("你的 HP：" + player.attributes.hp + "，" + monster.name + " 的 HP：" + monster.hp);
                player.attack(monster);
                if (monster.hp > 0) {
                    new Attack(monster, player).attackRandomTarget();
                }
                if (monster.hp <= 0) {
                    System.out.println("怪物被击败了！你成功通过了副本，获得了奖励：" + dungeon.getReward().getName());
                    Equipment rewardItem = dungeon.getReward();
                    player.inventory.addToInventory(rewardItem);
                    System.out.println("你获得了装备：" + rewardItem.getName());
                    if (monster.name.equals("龙")) {
                        questManager.completeQuest(1, player);
                    }
                }
            }
        } else if (battleMode == 2) {
            BattleManager.battleLoop(player, monster, random, scanner);
        }
        if (player.attributes.hp <= 0) {
            System.out.println("你被击败了，副本探索失败！");
            return;
        }
        monster.hp = monster.maxHp;
    }
}