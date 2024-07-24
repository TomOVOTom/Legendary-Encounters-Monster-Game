// File: src/game/item/ItemManager.java
package game.item;

import game.monster.Monster;
import game.player.Player;

import java.util.Scanner;

public class ItemManager {

    public static Monster currentMonster; // 假设有一个字段来跟踪战斗中的当前怪物

    public static Monster findCurrentMonster() {
        // 这个方法将返回玩家正在战斗的当前怪物
        return currentMonster;
    }

    public static void useItem(Player player, Scanner scanner) {
        ItemUsage.useItem(player, scanner);
    }

    public static void  displayInventory(Player player) {
        ItemDisplay.displayInventory(player);
    }
}