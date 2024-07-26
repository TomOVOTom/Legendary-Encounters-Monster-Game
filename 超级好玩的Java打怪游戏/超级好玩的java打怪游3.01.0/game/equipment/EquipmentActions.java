package game.equipment;

import game.player.Player;

public class EquipmentActions {

    public static void equip(Player player, Equipment equipment) {
        EquipmentHandler.equip(player, equipment);
    }

    public static void unequip(Player player, Equipment equipment) {
        EquipmentHandler.unequip(player, equipment);
    }

    public static boolean isEquipped(Player player, Equipment equipment) {
        return EquipmentHandler.isEquipped(player, equipment);
    }
}