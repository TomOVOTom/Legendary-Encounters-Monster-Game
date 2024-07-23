package game.battle;

import game.monster.Monster;
import game.player.Player;

import java.util.Random;

public class DamageCalculator {
    private Random random;

    public DamageCalculator(Random random) {
        this.random = random;
    }

    public int calculateAttackDamage(int attackerAttack, int defenderDefense, boolean isCriticalHit, int criticalDamage) {
        int baseDamage = attackerAttack - defenderDefense;
        if (isCriticalHit) {
            baseDamage *= criticalDamage;
        }
        return baseDamage;
    }

    public int applyArmorPenetration(Player attacker, Monster defender, int baseDamage) {
        if (attacker.attributes.armorPenetration > 0) {
            int reducedDefense = (int) (defender.defense * attacker.attributes.armorPenetration / 100);
            int newDamage = baseDamage + reducedDefense; // 计算穿透后的新伤害值
            System.out.println("你的攻击穿透了 " + defender.name + " 的防御，造成了 " + reducedDefense + " 点伤害！");
            return newDamage;
        }
        return baseDamage;
    }

    public int applyDamageIncrease(Player attacker, Monster defender, int baseDamage) {
        if (attacker.attributes.damageIncrease > 0 && defender.hp <= defender.maxHp * 0.5) {
            int originalDamage = baseDamage;
            baseDamage *= (1 + attacker.attributes.damageIncrease / 100);
            int extraDamage = baseDamage - originalDamage;
            System.out.println("你的攻击对生命值较低的 " + defender.name + " 造成额外伤害：" + extraDamage + " 点！");
            return baseDamage;
        }
        return baseDamage;
    }


    public int applyPiercingAttack(Player attacker, Monster defender, int baseDamage) {
        if (random.nextInt(100) < attacker.attributes.piercingAttackRate) {
            // 确保穿透伤害不会小于基础伤害
            int piercingDamage = Math.max(attacker.attributes.piercingAttackDamage, baseDamage);
            System.out.println("你的攻击穿透了 " + defender.name + " 的防御，造成固定伤害：" + piercingDamage +  " 点！");
            return piercingDamage;
        }
        return baseDamage;
    }

    public int calculateDamage(Monster defender, int baseDamage) {
        if (random.nextInt(100) < defender.blockRate) {
            System.out.println(defender.name + " 格挡了你的攻击！");
            return baseDamage / 2;
        }
        return baseDamage;
    }
}