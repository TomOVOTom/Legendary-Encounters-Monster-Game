package game;

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
            System.out.println("HP：" + player.hp);
            System.out.println("攻击力：" + player.attack);
            System.out.println("防御力：" + player.defense);
            System.out.println("智力：" + player.intelligence);
            System.out.println("体力：" + player.stamina);
            System.out.println("躲避：" + player.dodge);
            System.out.println("等级：" + player.level);
            System.out.println("经验值：" + player.exp);
            System.out.println("技能点：" + player.skillPoints);
            System.out.println("金币：" + player.gold);
        } else if (choice == 2) {
            System.out.println("怪物的状态：");
            System.out.println("HP：" + monster.hp);
            System.out.println("攻击力：" + monster.attack);
            System.out.println("防御力：" + monster.defense);
            System.out.println("智力：" + monster.intelligence);
            System.out.println("体力：" + monster.stamina);
            System.out.println("躲避：" + monster.dodge);
            System.out.println("等级：" + monster.level);
        } else {
            System.out.println("无效的选项！");
        }
    }
}
