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

        if (isHit(player.attributes.hitRate)) {
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
        return random.nextInt(100) >= hitRate;
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
        baseDamage = damageCalculator.applyArmorPenetration(player, monster, baseDamage);
        baseDamage = damageCalculator.applyDamageIncrease(player, monster, baseDamage);
        baseDamage = damageCalculator.applyPiercingAttack(player, monster, baseDamage);
        int damage = damageCalculator.calculateDamage(monster, baseDamage);

        double comboMultiplier = 1 + player.comboCounter * 0.4;
        damage = (int) (damage * comboMultiplier);

        monster.hp -= damage;
        player.comboCounter++;

        Vampirism.applyVampirism(player, monster, damage);

        if (player.comboCounter > 0) {
            System.out.println("连击！造成额外伤害：" + (damage - baseDamage));
        }
    }

    private void resetCombo(Player player) {
        player.comboCounter = 0;
    }
}