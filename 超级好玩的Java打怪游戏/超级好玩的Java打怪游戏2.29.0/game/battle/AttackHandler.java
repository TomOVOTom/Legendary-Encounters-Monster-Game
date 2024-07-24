package game.battle;

import game.monster.Monster;
import game.pet.Pet;
import game.player.Player;
import game.player.skill.magic.Vampirism;

import java.util.Random;

public class AttackHandler {
    private Random random = new Random();
    private DamageCalculator damageCalculator = new DamageCalculator(random);

    // 玩家攻击怪物
    public void attack(Player player, Monster monster) {
        // 检查怪物是否躲避攻击
        if (isDodge(monster.attributes.dodge)) {
            System.out.println(monster.attributes.name + " 躲避了你的攻击！");
            resetCombo(player);
            return;
        }

        // 检查玩家是否命中怪物
        if (!isHit(player.attributes.hitRate)) {
            System.out.println("你的攻击没有击中 " + monster.attributes.name + "！");
            resetCombo(player);
            return;
        }

        // 计算基础伤害并显示攻击结果
        int baseDamage = calculateBaseDamage(player, monster);
        applyEffectsAndDamage(player, monster, baseDamage);
    }

    // 怪物攻击玩家
    public void attack(Monster monster, Player player) {
        // 检查玩家是否躲避攻击
        if (isDodge(player.attributes.dodge)) {
            System.out.println("你躲避了 " + monster.attributes.name + " 的攻击！");
            return;
        }

        // 计算基础伤害并显示攻击结果
        int baseDamage = calculateBaseDamage(monster, player);
        player.attributes.hp -= baseDamage;
    }

    // 怪物攻击宠物
    public void attack(Monster monster, Pet pet) {
        // 检查宠物是否躲避攻击
        if (isDodge(pet.dodge)) {
            System.out.println(pet.name + " 躲避了 " + monster.attributes.name + " 的攻击！");
            return;
        }

        // 计算基础伤害并显示攻击结果
        int baseDamage = calculateBaseDamage(monster, pet);
        pet.hp -= baseDamage;
        if (pet.hp <= 0) {
            System.out.println(pet.name + " 被击败了！");
        }
    }

    // 检查是否躲避攻击
    private boolean isDodge(int dodgeRate) {
        return random.nextInt(200) < dodgeRate;
    }

    // 检查是否命中目标
    private boolean isHit(int hitRate) {
        return random.nextInt(100) < hitRate;
    }

    // 计算玩家对怪物的基础伤害
    private int calculateBaseDamage(Player player, Monster monster) {
        boolean isCriticalHit = random.nextInt(100) < player.attributes.criticalRate;
        int baseDamage = damageCalculator.calculateAttackDamage(player.attributes.attack, monster.attributes.defense, isCriticalHit, player.attributes.criticalDamage);
        displayAttackOutcome(player, monster, baseDamage, isCriticalHit);
        return baseDamage;
    }

    // 计算怪物对玩家的基础伤害
    private int calculateBaseDamage(Monster monster, Player player) {
        boolean isCriticalHit = random.nextInt(100) < monster.attributes.criticalRate;
        int baseDamage = damageCalculator.calculateAttackDamage(monster.attributes.attack, player.attributes.defense, isCriticalHit, monster.attributes.criticalDamage);
        displayAttackOutcome(monster, player, baseDamage, isCriticalHit);
        return baseDamage;
    }

    // 计算怪物对宠物的基础伤害
    private int calculateBaseDamage(Monster monster, Pet pet) {
        boolean isCriticalHit = random.nextInt(100) < monster.attributes.criticalRate;
        int baseDamage = damageCalculator.calculateAttackDamage(monster.attributes.attack, pet.defense, isCriticalHit, monster.attributes.criticalDamage);
        displayAttackOutcome(monster, pet, baseDamage, isCriticalHit);
        return baseDamage;
    }

    // 显示玩家攻击怪物的结果
    private void displayAttackOutcome(Player player, Monster monster, int baseDamage, boolean isCriticalHit) {
        if (isCriticalHit) {
            System.out.println("你发动了暴击！攻击了 " + monster.attributes.name + "，造成了 " + baseDamage + " 点伤害！");
        } else if (baseDamage > 0) {
            System.out.println("你攻击了 " + monster.attributes.name + "，造成了 " + baseDamage + " 点伤害！");
        } else {
            System.out.println("你的攻击被 " + monster.attributes.name + " 防御住了！");
            resetCombo(player);
        }
    }

    // 显示怪物攻击玩家的结果
    private void displayAttackOutcome(Monster monster, Player player, int baseDamage, boolean isCriticalHit) {
        if (isCriticalHit) {
            System.out.println(monster.attributes.name + " 发动了暴击！攻击了你，造成了 " + baseDamage + " 点伤害！");
        } else if (baseDamage > 0) {
            System.out.println(monster.attributes.name + " 攻击了你，造成了 " + baseDamage + " 点伤害！");
        } else {
            System.out.println(monster.attributes.name + " 的攻击被你防御住了！");
        }
    }

    // 显示怪物攻击宠物的结果
    private void displayAttackOutcome(Monster monster, Pet pet, int baseDamage, boolean isCriticalHit) {
        if (isCriticalHit) {
            System.out.println(monster.attributes.name + " 发动了暴击！攻击了 " + pet.name + "，造成了 " + baseDamage + " 点伤害！");
        } else if (baseDamage > 0) {
            System.out.println(monster.attributes.name + " 攻击了 " + pet.name + "，造成了 " + baseDamage + " 点伤害！");
        } else {
            System.out.println(monster.attributes.name + " 的攻击被 " + pet.name + " 防御住了！");
        }
    }

    // 应用各种效果并计算最终伤害
    private void applyEffectsAndDamage(Player player, Monster monster, int baseDamage) {
        // 应用护甲穿透效果
        baseDamage = damageCalculator.applyArmorPenetration(player, monster, baseDamage);
        // 应用伤害增加效果
        baseDamage = damageCalculator.applyDamageIncrease(player, monster, baseDamage);

        // 单独计算穿透伤害
        int piercingDamage = damageCalculator.applyPiercingAttack(player, monster, baseDamage);
        // 将穿透伤害添加到基础伤害中
        baseDamage += piercingDamage;

        // 考虑怪物可能的格挡，计算最终伤害
        int finalDamage = damageCalculator.calculateDamage(monster, baseDamage);

        // 应用连击倍数
        double comboMultiplier = 1 + player.comboCounter * 0.4;
        finalDamage = (int) (finalDamage * comboMultiplier);

        // 对怪物应用最终伤害并增加玩家的连击计数
        monster.attributes.hp -= finalDamage;
        player.comboCounter++;

        // 应用吸血鬼效果
        Vampirism.applyVampirism(player, monster, finalDamage);

        // 如果连击次数大于1，报告额外伤害
        if (player.comboCounter > 1) {
            int extraDamage = finalDamage - baseDamage;
            System.out.println("连击！造成额外伤害：" + extraDamage);
        }

        // 显示最终伤害结果
        System.out.println("你攻击了 " + monster.attributes.name + "，造成了 " + finalDamage + " 点伤害！");
    }

    // 重置玩家的连击计数
    private void resetCombo(Player player) {
        player.comboCounter = 0;
    }
}