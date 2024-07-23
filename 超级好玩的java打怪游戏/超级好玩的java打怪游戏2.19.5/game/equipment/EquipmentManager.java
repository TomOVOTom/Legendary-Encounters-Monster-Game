// File: src/game/equipment/EquipmentManager.java
package game.equipment;

import game.player.Player;

import java.util.Scanner;

public class EquipmentManager {

    public static void equip(Player player, Equipment equipment) {
        EquipmentActions.equip(player, equipment);
    }


    public static void upgradeEquipment(Player player, Scanner scanner) {
        // Implement equipment upgrade logic here
        System.out.println("装备升级功能尚未实现。");
    }
}