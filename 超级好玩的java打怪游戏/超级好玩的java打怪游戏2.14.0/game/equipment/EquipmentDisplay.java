// File: src/game/equipment/EquipmentDisplay.java
package game.equipment;

public class EquipmentDisplay {

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