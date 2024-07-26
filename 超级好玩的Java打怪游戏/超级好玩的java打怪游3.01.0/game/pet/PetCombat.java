package game.pet;

import game.monster.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetCombat {
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
        if (random.nextInt(150) < monster.attributes.dodge) {
            System.out.println(monster.attributes.name + " 躲避了你的宠物 " + pet.name + " 的攻击！");
        } else {
            int skillDamage = (int) (skill.getBaseDamage() + pet.attack * skill.getAttackMultiplier() + pet.intelligence * skill.getIntelligenceMultiplier());
            int baseDamage = Math.max(skillDamage - monster.attributes.defense, 0);
            if (baseDamage > 0) {
                System.out.println("你的宠物 " + pet.name + " 使用 " + skill.getName() + " 攻击了 " + monster.attributes.name + "，造成了 " + baseDamage + " 点伤害！");
                monster.attributes.hp -= baseDamage;
            } else {
                System.out.println("你的宠物 " + pet.name + " 的攻击被 " + monster.attributes.name + " 防御住了！");
            }
        }
    }
}