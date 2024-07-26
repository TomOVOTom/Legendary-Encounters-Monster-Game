// File: src/game/item/ItemFilter.java
package game.item;

import game.equipment.Equipment;
import game.player.Player;

import java.util.Map;
import java.util.stream.Collectors;

public class ItemFilter {

    public static Map<String, Item> filterItems(Player player) {
        return player.getInventory().getInventory().entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Item)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (Item) entry.getValue()));
    }

    public static Map<String, Equipment> filterEquipment(Player player) {
        return player.getInventory().getInventory().entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Equipment)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (Equipment) entry.getValue()));
    }
}