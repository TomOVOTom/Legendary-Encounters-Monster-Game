// File: src/game/equipment/EquipmentManager.java
package game.equipment;

import game.player.Player;

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
            case "Ring":
                if (player.equippedRing != null && player.equippedRing.equals(equipment)) {
                    System.out.println("你已经装备了 " + equipment.name + "！");
                    return;
                }
                unequip(player, player.equippedRing); // 如果有，先卸下当前戒指
                player.equippedRing = equipment;
                break;
            case "Amulet":
                if (player.equippedAmulet != null && player.equippedAmulet.equals(equipment)) {
                    System.out.println("你已经装备了 " + equipment.name + "！");
                    return;
                }
                unequip(player, player.equippedAmulet); // 如果有，先卸下当前护身符
                player.equippedAmulet = equipment;
                break;
            default:
                System.out.println("未知的装备类型！");
                return;
        }
        // Apply all attribute bonuses
        player.attributes.attack += equipment.attackBonus;
        player.attributes.defense += equipment.defenseBonus;
        player.attributes.hp += equipment.hpBonus;
        player.attributes.intelligence += equipment.intelligenceBonus;
        player.attributes.stamina += equipment.staminaBonus;
        player.attributes.dodge += equipment.dodgeBonus;
        System.out.println("你装备了 " + equipment.name + "！");
        displayAttributeBonuses(equipment);
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
            case "Ring":
                if (player.equippedRing != null && player.equippedRing.equals(equipment)) {
                    player.equippedRing = null;
                }
                break;
            case "Amulet":
                if (player.equippedAmulet != null && player.equippedAmulet.equals(equipment)) {
                    player.equippedAmulet = null;
                }
                break;
            default:
                System.out.println("未知的装备类型！");
                return;
        }
        // Remove all attribute bonuses
        player.attributes.attack -= equipment.attackBonus;
        player.attributes.defense -= equipment.defenseBonus;
        player.attributes.hp -= equipment.hpBonus;
        player.attributes.intelligence -= equipment.intelligenceBonus;
        player.attributes.stamina -= equipment.staminaBonus;
        player.attributes.dodge -= equipment.dodgeBonus;
        System.out.println("你卸下了 " + equipment.name + "！");
    }

    public static void displayAttributeBonuses(Equipment equipment) {
        System.out.println("装备属性提升：");
        if (equipment.attackBonus > 0) System.out.println("攻击力提升：" + equipment.attackBonus);
        if (equipment.defenseBonus > 0) System.out.println("防御力提升：" + equipment.defenseBonus);
        if (equipment.hpBonus > 0) System.out.println("生命值提升：" + equipment.hpBonus);
        if (equipment.intelligenceBonus > 0) System.out.println("智力提升：" + equipment.intelligenceBonus);
        if (equipment.staminaBonus > 0) System.out.println("体力提升：" + equipment.staminaBonus);
        if (equipment.dodgeBonus > 0) System.out.println("闪避提升：" + equipment.dodgeBonus);
    }
}