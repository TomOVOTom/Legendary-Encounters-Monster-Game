// game/base/BaseUpgrade.java
package game.base;

import game.player.Player;
import java.util.Scanner;

public class BaseUpgrade {
    public static void upgradeBase(Player player, Scanner scanner) {
        int upgradeCost = player.base.getGoldCost();
        System.out.println("升级基地需要 " + upgradeCost + " 金币。是否升级？(1. 是 2. 否)");
        int upgradeChoice = scanner.nextInt();
        if (upgradeChoice == 1) {
            if (player.attributes.gold < upgradeCost) {
                System.out.println("你的金币不足，无法升级基地！");
                return;
            }
            player.attributes.gold -= upgradeCost;
            player.base.upgrade();
            System.out.println("基地升级成功！你花费了 " + upgradeCost + " 金币，当前剩余金币：" + player.attributes.gold + "。当前基地等级：" + player.base.getLevel());
        } else {
            System.out.println("你选择了不升级基地。");
        }
    }
}