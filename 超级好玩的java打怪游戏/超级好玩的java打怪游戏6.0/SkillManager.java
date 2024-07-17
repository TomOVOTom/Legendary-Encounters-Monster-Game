package game;

import java.util.Scanner;

public class SkillManager {
    // 使用技能的方法
    public static void useSkill(Player player, Monster monster, Scanner scanner) {
        System.out.println("请选择你的技能：1. 治疗 2. 火焰魔法 3. 吸血术");
        int skillChoice = scanner.nextInt();
        if (skillChoice == 1) {
            player.useHealMagic();
        } else if (skillChoice == 2) {
            player.useFireMagic(monster);
        } else if (skillChoice == 3) {
            // 使用吸血术，直接调用 attack 方法
            player.attack(monster); //  在攻击的同时吸血
            System.out.println("你使用了吸血术！"); // 打印提示
        } else {
            System.out.println("无效的技能选择！");
        }
    }
}
