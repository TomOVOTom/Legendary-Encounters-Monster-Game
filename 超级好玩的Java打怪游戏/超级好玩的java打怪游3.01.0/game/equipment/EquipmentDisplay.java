package game.equipment;

public class EquipmentDisplay {

    public static String getEquipmentEffectDescription(Equipment equipment) {
        StringBuilder effectDescription = new StringBuilder();
        if (equipment.attackBonus > 0) effectDescription.append("攻击力提升：").append(equipment.attackBonus).append("\n");
        if (equipment.defenseBonus > 0) effectDescription.append("防御力提升：").append(equipment.defenseBonus).append("\n");
        if (equipment.hpBonus > 0) effectDescription.append("生命值提升：").append(equipment.hpBonus).append("\n");
        if (equipment.intelligenceBonus > 0) effectDescription.append("智力提升：").append(equipment.intelligenceBonus).append("\n");
        if (equipment.staminaBonus > 0) effectDescription.append("体力提升：").append(equipment.staminaBonus).append("\n");
        if (equipment.dodgeBonus > 0) effectDescription.append("闪避提升：").append(equipment.dodgeBonus).append("\n");
        return effectDescription.toString();
    }
}