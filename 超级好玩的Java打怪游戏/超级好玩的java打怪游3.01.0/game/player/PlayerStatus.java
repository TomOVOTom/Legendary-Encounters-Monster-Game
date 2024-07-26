// File: src/game/player/PlayerStatus.java
package game.player;

import game.monster.Monster;

import java.util.Scanner;

public class PlayerStatus {
    // 查看状态的方法
    public static void displayPlayerStatus(Player player, Monster monster) {
        System.out.println("请选择要查看的状态：");
        System.out.println("1. 查看自己的状态");
        System.out.println("2. 查看怪物的状态");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("你的状态：");
            System.out.println("HP：" + player.attributes.hp);
            System.out.println("攻击力：" + player.attributes.attack);
            System.out.println("防御力：" + player.attributes.defense);
            System.out.println("智力：" + player.attributes.intelligence);
            System.out.println("体力：" + player.attributes.stamina);
            System.out.println("躲避：" + player.attributes.dodge);
            System.out.println("等级：" + player.attributes.level);
            System.out.println("经验值：" + player.attributes.exp);
            System.out.println("技能点：" + player.attributes.skillPoints);
            System.out.println("金币：" + player.attributes.gold);
        } else if (choice == 2) {
            System.out.println("怪物的状态：");
            System.out.println("HP：" + monster.attributes.
                    hp);
            System.out.println("攻击力：" + monster.attributes.
                    attack);
            System.out.println("防御力：" + monster.attributes.
                    defense);
            System.out.println("智力：" + monster.attributes.
                    intelligence);
            System.out.println("体力：" + monster.attributes.
                    stamina);
            System.out.println("躲避：" + monster.attributes.
                    dodge);
            System.out.println("等级：" + monster.attributes.
                    level);
        } else {
            System.out.println("无效的选项！");
        }
    }
}