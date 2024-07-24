// File: src/game/item/ItemUsage.java
package game.item;

import game.equipment.Equipment;
import game.player.Player;

import java.util.Map;
import java.util.Scanner;

public class ItemUsage {

    public static void useItem(Player player, Scanner scanner) {
        Map<String, Item> itemItems = ItemFilter.filterItems(player);
        Map<String, Equipment> equipmentItems = ItemFilter.filterEquipment(player);

        ItemDisplay.displayItemCategories(player, scanner, itemItems, equipmentItems);
    }

    public static void applyItemEffect(Player player, Item selectedItem) {
        ItemEffect.applyEffect(player, selectedItem);
    }
}