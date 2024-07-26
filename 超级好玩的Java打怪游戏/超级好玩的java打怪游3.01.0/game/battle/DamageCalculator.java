package game.battle;

import game.monster.Monster;
import game.player.Player;

import java.util.Random;

public class DamageCalculator {
    private Random random;
    private BattleManagerGUI gui;

    public DamageCalculator(Random random, BattleManagerGUI gui) {
        this.random = random;
        this.gui = gui;
    }

    public int calculateAttackDamage(int attackerAttack, int defenderDefense, boolean isCriticalHit, int criticalDamage) {
        int baseDamage = Math.max(attackerAttack - defenderDefense, 0); // 确保基础伤害不为负数
        if (isCriticalHit) {
            baseDamage = (baseDamage * criticalDamage); // 计算暴击伤害
        }
        return baseDamage;
    }

    public int applyArmorPenetration(Player attacker, Monster monster, int baseDamage) {
        if (attacker.attributes.armorPenetration > 0) {
            int reducedDefense = (int) (monster.attributes.defense * attacker.attributes.armorPenetration / 100);
            int newDamage = baseDamage + reducedDefense; // 计算穿透后的新伤害值
            gui.log("你的攻击穿透了 " + monster.attributes.name + " 的防御，造成了 " + reducedDefense + " 点伤害！");
            return newDamage;
        }
        return baseDamage;
    }

    public int applyDamageIncrease(Player attacker, Monster monster, int baseDamage) {
        if (attacker.attributes.damageIncrease > 0 && monster.attributes.hp <= monster.attributes.maxHp * 0.5) {
            int originalDamage = baseDamage;
            baseDamage *= (1 + attacker.attributes.damageIncrease / 100.0);
            int extraDamage = baseDamage - originalDamage;
            gui.log("你的攻击对生命值较低的 " + monster.attributes.name + " 造成额外伤害：" + extraDamage + " 点！");
            return baseDamage;
        }
        return baseDamage;
    }

    public int applyPiercingAttack(Player attacker, Monster monster, int baseDamage) {
        if (random.nextInt(100) < attacker.attributes.piercingAttackRate) {
            // 确保穿透伤害不会小于基础伤害
            int piercingDamage = Math.max(attacker.attributes.piercingAttackDamage, 0);
            gui.log("你的攻击穿透了 " + monster.attributes.name + " 的防御，造成固定伤害：" + piercingDamage + " 点！");
            return piercingDamage;
        }
        return baseDamage;
    }

    public int calculateDamage(Monster monster, int baseDamage) {
        if (random.nextInt(100) < monster.attributes.blockRate) {
            gui.log(monster.attributes.name + " 格挡了你的攻击！");
            return baseDamage / 2;
        }
        return baseDamage;
    }
}