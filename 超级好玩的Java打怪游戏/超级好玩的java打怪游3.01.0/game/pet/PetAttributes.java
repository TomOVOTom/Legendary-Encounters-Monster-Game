// File: src/game/pet/PetAttributes.java
package game.pet;

public class PetAttributes {
    public static void calculateCombatPower(Pet pet) {
        double skillImpact = pet.skills.stream().mapToDouble(skill -> skill.getBaseDamage() + skill.getAttackMultiplier() * pet.attack + skill.getIntelligenceMultiplier() * pet.intelligence).sum();
        pet.combatPower = (int) (pet.attack * 0.7 + pet.defense * 0.3 + pet.intelligence * 0.4 + pet.stamina * 0.02 + pet.dodge * 0.7 + skillImpact * 0.1);
    }

    public static void displayAttributes(Pet pet) {
        System.out.println("宠物名称: " + pet.name);
        System.out.println("生命值: " + pet.hp);
        System.out.println("攻击力: " + pet.attack);
        System.out.println("防御力: " + pet.defense);
        System.out.println("智力: " + pet.intelligence);
        System.out.println("体力: " + pet.stamina);
        System.out.println("躲避: " + pet.dodge);
        System.out.println("等级: " + pet.level);
        System.out.println("经验值: " + pet.exp);
        System.out.println("战斗力: " + pet.combatPower);
        System.out.println("技能: ");
        for (PetSkill skill : pet.skills) {
            System.out.println("  - " + skill.getName() + " (基础伤害: " + skill.getBaseDamage() + ", 冷却: " + skill.getCooldown() + ", 体力消耗: " + skill.getStaminaCost() + ", 攻击力加成: " + skill.getAttackMultiplier() + ", 智力加成: " + skill.getIntelligenceMultiplier() + ")");
        }
    }
}