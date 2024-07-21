// PetUtils.java
package game.pet;

import game.monster.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetUtils {
    public static void initializeSkills(Pet pet) {
        pet.skills.add(new PetSkill("基础攻击", 10, 0, 0, 1.0, 0.0));
        pet.skills.add(new PetSkill("火焰喷射", 20, 3, 5, 1.2, 0.5));
        pet.skills.add(new PetSkill("冰霜打击", 15, 2, 4, 1.1, 0.4));
        pet.skills.add(new PetSkill("雷电冲击", 25, 4, 6, 1.3, 0.6));
        pet.skills.add(new PetSkill("风暴之怒", 30, 5, 7, 1.4, 0.7));
        pet.skills.add(new PetSkill("大地震击", 35, 6, 8, 1.5, 0.8));
    }

    public static void attack(Pet pet, Monster monster) {
        Random random = new Random();
        long currentTime = System.currentTimeMillis();
        List<PetSkill> availableSkills = new ArrayList<>();
        for (PetSkill skill : pet.skills) {
            if (currentTime - skill.getLastUsed() >= skill.getCooldown() * 1000 && pet.stamina >= skill.getStaminaCost()) {
                availableSkills.add(skill);
            }
        }
        if (availableSkills.isEmpty()) {
            System.out.println("所有技能都在冷却中或体力不足，无法攻击！");
            return;
        }
        PetSkill skill = availableSkills.get(random.nextInt(availableSkills.size()));
        skill.setLastUsed(currentTime);
        pet.stamina -= skill.getStaminaCost(); // 扣除体力
        if (random.nextInt(150) < monster.dodge) {
            System.out.println(monster.name + " 躲避了你的宠物 " + pet.name + " 的攻击！");
        } else {
            int skillDamage = (int) (skill.getBaseDamage() + pet.attack * skill.getAttackMultiplier() + pet.intelligence * skill.getIntelligenceMultiplier());
            int baseDamage = Math.max(skillDamage - monster.defense, 0);
            if (baseDamage > 0) {
                System.out.println("你的宠物 " + pet.name + " 使用 " + skill.getName() + " 攻击了 " + monster.name + "，造成了 " + baseDamage + " 点伤害！");
                monster.hp -= baseDamage;
            } else {
                System.out.println("你的宠物 " + pet.name + " 的攻击被 " + monster.name + " 防御住了！");
            }
        }
    }

    public static void gainExp(Pet pet, int exp) {
        pet.exp += exp;
        int requiredExp = (int) Math.pow(pet.level * 100, 1.2);
        if (pet.exp >= requiredExp) {
            pet.level++;
            pet.exp -= requiredExp;
            System.out.println("你的宠物 " + pet.name + " 升级了！当前等级：" + pet.level);
            // Increase pet attributes on level up
            pet.hp += 10;
            pet.attack += 2;
            pet.defense += 2;
            pet.intelligence += 2;
            pet.stamina += 5;
            pet.dodge += 1;
            calculateCombatPower(pet);
        }
    }

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