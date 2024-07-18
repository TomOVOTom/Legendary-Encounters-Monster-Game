// EquipmentManager.java
package game;

public class EquipmentManager {
    public static void equip(Player player, Equipment equipment) {
        switch (equipment.type) {
            case "Weapon":
                if (player.equippedWeapon != null && player.equippedWeapon.equals(equipment)) {
                    System.out.println("你已经装备了 " + equipment.name + "！");
                    return;
                }
                unequip(player, player.equippedWeapon); // 如果有，先卸下当前武器
                player.equippedWeapon = equipment;
                break;
            case "Armor":
                if (player.equippedArmor != null && player.equippedArmor.equals(equipment)) {
                    System.out.println("你已经装备了 " + equipment.name + "！");
                    return;
                }
                unequip(player, player.equippedArmor); // 如果有，先卸下当前护甲
                player.equippedArmor = equipment;
                break;
            case "Boots":
                if (player.equippedBoots != null && player.equippedBoots.equals(equipment)) {
                    System.out.println("你已经装备了 " + equipment.name + "！");
                    return;
                }
                unequip(player, player.equippedBoots); // 如果有，先卸下当前靴子
                player.equippedBoots = equipment;
                break;
            default:
                System.out.println("未知的装备类型！");
                return;
        }
        // Apply all attribute bonuses
        player.attack += equipment.attackBonus;
        player.defense += equipment.defenseBonus;
        player.hp += equipment.hpBonus;
        player.intelligence += equipment.intelligenceBonus;
        player.stamina += equipment.staminaBonus;
        player.dodge += equipment.dodgeBonus;
        System.out.println("你装备了 " + equipment.name + "！");
        player.displayAttributeBonuses(equipment);
    }

    public static void unequip(Player player, Equipment equipment) {
        if (equipment == null) return; // 如果没有传递装备，直接返回
        switch (equipment.type) {
            case "Weapon":
                if (player.equippedWeapon != null && player.equippedWeapon.equals(equipment)) {
                    player.equippedWeapon = null;
                }
                break;
            case "Armor":
                if (player.equippedArmor != null && player.equippedArmor.equals(equipment)) {
                    player.equippedArmor = null;
                }
                break;
            case "Boots":
                if (player.equippedBoots != null && player.equippedBoots.equals(equipment)) {
                    player.equippedBoots = null;
                }
                break;
            default:
                System.out.println("未知的装备类型！");
                return;
        }
        // Remove all attribute bonuses
        player.attack -= equipment.attackBonus;
        player.defense -= equipment.defenseBonus;
        player.hp -= equipment.hpBonus;
        player.intelligence -= equipment.intelligenceBonus;
        player.stamina -= equipment.staminaBonus;
        player.dodge -= equipment.dodgeBonus;
        System.out.println("你卸下了 " + equipment.name + "！");
    }
}