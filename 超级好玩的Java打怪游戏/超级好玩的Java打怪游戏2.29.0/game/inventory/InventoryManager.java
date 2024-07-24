package game.inventory;

import game.equipment.Equipment;
import game.equipment.EquipmentDisplay;
import game.item.ItemManager;
import game.mount.Mount;
import game.player.Player;

import java.util.Map;
import java.util.Scanner;

public class InventoryManager {

    public static void useItem(Player player, Scanner scanner) {
        ItemManager.useItem(player, scanner);
    }

    public static void displayInventory(Player player) {
        System.out.println("当前装备：");
        if (player.equippedWeapon != null) {
            System.out.println("武器: " + player.equippedWeapon.name);
            System.out.println("效果：\n" + EquipmentDisplay.getEquipmentEffectDescription(player.equippedWeapon));
        }
        if (player.equippedArmor != null) {
            System.out.println("护甲: " + player.equippedArmor.name);
            System.out.println("效果：\n" + EquipmentDisplay.getEquipmentEffectDescription(player.equippedArmor));
        }
        if (player.equippedBoots != null) {
            System.out.println("靴子: " + player.equippedBoots.name);
            System.out.println("效果：\n" + EquipmentDisplay.getEquipmentEffectDescription(player.equippedBoots));
        }

        ItemManager.displayInventory(player);

        System.out.println("持有的装备：");
        for (Map.Entry<String, Object> entry : player.getInventory().getInventory().entrySet()) {
            if (entry.getValue() instanceof Equipment) {
                Equipment equipment = (Equipment) entry.getValue();
                System.out.println(entry.getKey() + " (装备) - 效果：\n" + EquipmentDisplay.getEquipmentEffectDescription(equipment));
            } else if (entry.getValue() instanceof Mount) {
                System.out.println(entry.getKey() + " (坐骑)");
            }
        }
    }
}