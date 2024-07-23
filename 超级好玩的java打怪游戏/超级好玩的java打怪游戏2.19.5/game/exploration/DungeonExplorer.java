// File: src/game/exploration/DungeonExplorer.java
package game.exploration;

import game.battle.Attack;
import game.battle.BattleManager;
import game.battle.BattleResult;
import game.equipment.Equipment;
import game.monster.Monster;
import game.player.Player;
import game.quest.QuestManager;

import java.util.Random;
import java.util.Scanner;

public class DungeonExplorer {
    public static void exploreDungeon(Player player, Random random, QuestManager questManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择副本难度：1. 简单 2. 中等 3. 困难 4. 专家 5. 大师 6. 噩梦 7. 地狱 10. 终极");
        int difficultyChoice = scanner.nextInt();
        int selectedDifficulty = difficultyChoice; // 1 for easy, 2 for medium, 3 for hard, etc.

        Dungeon dungeon = DungeonRegistry.DUNGEONS.stream()
                .filter(d -> d.getDifficulty() == selectedDifficulty)
                .findAny()
                .orElse(DungeonRegistry.DUNGEONS.get(0)); // Default to the first dungeon if none match

        System.out.println("你进入了副本：" + dungeon.getName() + "。难度：" + dungeon.getDifficulty());
        System.out.println("描述：" + dungeon.getDescription());

        // 随机选择一个怪兽
        Monster monster = dungeon.getMonsters().get(random.nextInt(dungeon.getMonsters().size()));
        monster.maxHp = (monster.hp * dungeon.getDifficulty());
        monster.hp = monster.maxHp; // Ensure current HP is also updated
        monster.attack += dungeon.getDifficulty() * 2;
        monster.defense += dungeon.getDifficulty() * 2;
        System.out.println("你遇到了怪兽：" + monster.name + " (HP: " + monster.hp + ", 攻击: " + monster.attack + ", 防御: " + monster.defense + ")");
        System.out.println("请选择战斗模式：1. 自动战斗 2. 手动战斗");
        int battleMode = scanner.nextInt();

        if (battleMode == 1) {
            while (monster.hp > 0 && player.attributes.hp > 0) {
                System.out.println("你的 HP：" + player.attributes.hp + "，" + monster.name + " 的 HP：" + monster.hp);
                player.attack(monster);
                if (monster.hp > 0) {
                    new Attack(monster, player).attackRandomTarget();
                }
                if (monster.hp <= 0) {
                    System.out.println("怪物被击败了！");
                    BattleResult.dropItems(player,monster);
                    // 50% chance to drop the reward
                    if (random.nextInt(100) < 30) {
                        Equipment rewardItem = dungeon.getReward();
                        player.inventory.addToInventory(rewardItem,1);
                        System.out.println("你获得了战利品：" + rewardItem.getName());
                    } else {
                        System.out.println("很遗憾，没有获得战利品。");
                    }
                    if (monster.name.equals("幽灵")) {
                        questManager.completeQuest(1, player);
                    } else if (monster.name.equals("沙漠蝎子")) {
                        questManager.completeQuest(2, player);
                    } else if (monster.name.equals("火焰巨人")) {
                        questManager.completeQuest(3, player);
                    } else if (monster.name.equals("冰霜巨人")) {
                        questManager.completeQuest(4, player);
                    } else if (monster.name.equals("深海巨兽")) {
                        questManager.completeQuest(5, player);
                    } else if (monster.name.equals("龙")) {
                        questManager.completeQuest(6, player);
                    } else if (monster.name.equals("噩梦巨兽")) {
                        questManager.completeQuest(7, player);
                    } else if (monster.name.equals("地狱巨兽")) {
                        questManager.completeQuest(8, player);
                    } else if (monster.name.equals("终极巨兽")) {
                        questManager.completeQuest(9, player);
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
        monster.hp = monster.maxHp / dungeon.getDifficulty();
    }
}