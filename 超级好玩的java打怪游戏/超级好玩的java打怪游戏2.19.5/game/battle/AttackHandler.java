package game.battle;

import game.monster.Monster;
import game.pet.Pet;
import game.player.Player;
import game.player.skill.magic.Vampirism;

import java.util.Random;

public class AttackHandler {
    private Random random = new Random();
    private DamageCalculator damageCalculator = new DamageCalculator(random);

    public void attack(Player player, Monster monster) {
        if (isDodge(monster.dodge)) {
            System.out.println(monster.name + " 躲避了你的攻击！");
            resetCombo(player);
            return;
        }

        if (!isHit(player.attributes.hitRate)) {
            System.out.println("你的攻击没有击中 " + monster.name + "！");
            resetCombo(player);
            return;
        }

        int baseDamage = calculateBaseDamage(player, monster);
        displayAttackOutcome(player, monster, baseDamage);
        applyEffectsAndDamage(player, monster, baseDamage);
    }

    public void attack(Monster monster, Player player) {
        if (isDodge(player.attributes.dodge)) {
            System.out.println("你躲避了 " + monster.name + " 的攻击！");
            return;
        }

        int baseDamage = calculateBaseDamage(monster, player);
        displayAttackOutcome(monster, player, baseDamage);
        player.attributes.hp -= baseDamage;
    }

    public void attack(Monster monster, Pet pet) {
        if (isDodge(pet.dodge)) {
            System.out.println(pet.name + " 躲避了 " + monster.name + " 的攻击！");
            return;
        }

        int baseDamage = calculateBaseDamage(monster, pet);
        displayAttackOutcome(monster, pet, baseDamage);
        pet.hp -= baseDamage;
        if (pet.hp <= 0) {
            System.out.println(pet.name + " 被击败了！");
        }
    }
    private void displayAttackOutcome(Monster attacker, Player defender, int baseDamage) {
        if (baseDamage > 0) {
            System.out.println(attacker.name + " 攻击了你，造成了 " + baseDamage + " 点伤害！");
        } else {
            System.out.println(attacker.name + " 的攻击被你防御住了！");
        }
    }

    private void displayAttackOutcome(Monster attacker, Pet defender, int baseDamage) {
        if (baseDamage > 0) {
            System.out.println(attacker.name + " 攻击了 " + defender.name + "，造成了 " + baseDamage + " 点伤害！");
        } else {
            System.out.println(attacker.name + " 的攻击被 " + defender.name + " 防御住了！");
        }
    }

    private boolean isDodge(int dodgeRate) {
        return random.nextInt(200) < dodgeRate;
    }

    private boolean isHit(int hitRate) {
        return random.nextInt(100) < hitRate;
    }

    private int calculateBaseDamage(Player player, Monster monster) {
        boolean isCriticalHit = random.nextInt(100) < player.attributes.criticalRate;
        return damageCalculator.calculateAttackDamage(player.attributes.attack, monster.defense, isCriticalHit, player.attributes.criticalDamage);
    }

    private int calculateBaseDamage(Monster monster, Player player) {
        boolean isCriticalHit = random.nextInt(100) < monster.criticalRate;
        return damageCalculator.calculateAttackDamage(monster.attack, player.attributes.defense, isCriticalHit, monster.criticalDamage);
    }

    private int calculateBaseDamage(Monster monster, Pet pet) {
        boolean isCriticalHit = random.nextInt(100) < monster.criticalRate;
        return damageCalculator.calculateAttackDamage(monster.attack, pet.defense, isCriticalHit, monster.criticalDamage);
    }

    private void displayAttackOutcome(Player player, Monster monster, int baseDamage) {
        if (baseDamage > 0) {
            System.out.println("你攻击了 " + monster.name + "，造成了 " + baseDamage + " 点伤害！");
        } else {
            System.out.println("你的攻击被 " + monster.name + " 防御住了！");
            resetCombo(player);
        }
    }

    private void applyEffectsAndDamage(Player player, Monster monster, int baseDamage) {
        // 应用护甲穿透和伤害增加效果
        baseDamage = damageCalculator.applyArmorPenetration(player, monster, baseDamage);
        baseDamage = damageCalculator.applyDamageIncrease(player, monster, baseDamage);

        // 单独计算穿透伤害
        int piercingDamage = damageCalculator.applyPiercingAttack(player, monster, baseDamage);
        // 检查是否发生穿透攻击，并将其效果添加到基础伤害中
        baseDamage = piercingDamage + baseDamage; // 如果穿透伤害更高，使用穿透伤害作为当前基础伤害



        // 考虑怪物可能的格挡，计算最终伤害
        int finalDamage = damageCalculator.calculateDamage(monster, baseDamage);

        // 应用连击倍数
        double comboMultiplier = 1 + player.comboCounter * 0.4;
        finalDamage = (int) (finalDamage * comboMultiplier);

        // 对怪物应用最终伤害并增加玩家的连击计数
        monster.hp -= finalDamage;
        player.comboCounter++;

        // 应用吸血鬼效果
        Vampirism.applyVampirism(player, monster, finalDamage);

        // 如果连击次数大于1，报告额外伤害
        if (player.comboCounter > 1) {
            int extraDamage = finalDamage - baseDamage;
            System.out.println("连击！造成额外伤害：" + extraDamage);
        }
    }


    private void resetCombo(Player player) {
        player.comboCounter = 0;
    }
}