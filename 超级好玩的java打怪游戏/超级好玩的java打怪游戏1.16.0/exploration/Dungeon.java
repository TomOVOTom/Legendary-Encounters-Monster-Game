// game/exploration/Dungeon.java
package game.exploration;

import game.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dungeon {
    private String name;
    private int difficulty;
    private String description;
    private List<Monster> monsters;
    private String reward;

    public Dungeon(String name, int difficulty, String description, List<Monster> monsters, String reward) {
        this.name = name;
        this.difficulty = difficulty;
        this.description = description;
        this.monsters = monsters;
        this.reward = reward;
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getDescription() {
        return description;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public String getReward() {
        return reward;
    }

    public static final List<Dungeon> DUNGEONS = Arrays.asList(
            new Dungeon("黑暗洞穴", 2, "一个充满危险生物的黑暗洞穴。", Arrays.asList(
                    new Monster(1, 100, 20, 10, 5, 15, 5, "洞穴蝙蝠", 10, "毒素", 3)
            ), "神秘宝箱"),
            new Dungeon("火焰山", 4, "炽热的火焰山，只有勇敢者才能挑战。", Arrays.asList(
                    new Monster(3, 200, 40, 20, 10, 30, 10, "火焰巨人", 50, "熔岩爆发", 4)
            ), "火焰之剑"),
            new Dungeon("龙之巢穴", 5, "传说中的龙之巢穴，充满了无尽的宝藏和危险。", Arrays.asList(
                    new Monster(5, 300, 60, 30, 15, 45, 15, "龙", 100, "龙息", 5)
            ), "龙之宝藏")
    );

    public static void exploreDungeon(Player player, Random random) {
        Scanner scanner = new Scanner(System.in);
        Dungeon dungeon = DUNGEONS.get(random.nextInt(DUNGEONS.size()));
        System.out.println("你进入了副本：" + dungeon.getName() + "。难度：" + dungeon.getDifficulty());
        System.out.println("描述：" + dungeon.getDescription());

        for (Monster monster : dungeon.getMonsters()) {
            monster.maxHp=monster.hp;
            System.out.println("你遇到了怪兽：" + monster.name);
            System.out.println("请选择战斗模式：1. 自动战斗 2. 手动战斗");
            int battleMode = scanner.nextInt();
            Attack attack = new Attack(player);
            if (battleMode == 1) {
                while (monster.hp > 0 && player.attributes.hp > 0) {
                    System.out.println("你的 HP：" + player.attributes.hp + "，" + monster.name + " 的 HP：" + monster.hp);
                    attack.attack(monster);
                    if (monster.hp > 0) {
                        attack.attack(player);
                    }
                    if (monster.hp <= 0) {
                        System.out.println("怪物被击败了！你成功通过了副本，获得了奖励：" + dungeon.getReward());
                        Item rewardItem = ItemRegistry.getItemByName(dungeon.getReward());
                        if (rewardItem != null) {
                            player.inventory.addToInventory(rewardItem);
                            System.out.println("你获得了道具：" + rewardItem.getName());
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
}