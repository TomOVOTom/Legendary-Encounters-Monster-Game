// File: src/game/mount/MountTrainer.java
package game.mount;

import game.player.Player;
import java.util.Scanner;

public class MountTrainer {
    public static void trainMount(Player player, Scanner scanner) {
        if (player.equippedMount == null) {
            System.out.println("你没有装备任何坐骑！");
            return;
        }

        Mount mount = player.equippedMount;
        System.out.println("开始训练 " + mount.name + "。");

        System.out.println("选择训练类型：1. 速度 2. 体力 3. 躲避 4. 攻击 5. 防御");
        int trainingChoice = scanner.nextInt();

        switch (trainingChoice) {
            case 1:
                mount.speed += 10;
                System.out.println(mount.name + " 的速度提升了！");
                break;
            case 2:
                mount.stamina += 20;
                System.out.println(mount.name + " 的体力提升了！");
                break;
            case 3:
                mount.dodgeBonus += 5;
                System.out.println(mount.name + " 的躲避值提升了！");
                break;
            case 4:
                mount.attackBonus += 5;
                System.out.println(mount.name + " 的攻击力提升了！");
                break;
            case 5:
                mount.defenseBonus += 5;
                System.out.println(mount.name + " 的防御力提升了！");
                break;
            default:
                System.out.println("无效的选择！");
                break;
        }
    }
}