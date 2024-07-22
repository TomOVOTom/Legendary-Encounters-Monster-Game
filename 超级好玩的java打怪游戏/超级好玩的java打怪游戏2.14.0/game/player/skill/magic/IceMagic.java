// File: src/game/player/skill/magic/IceMagic.java
package game.player.skill.magic;

import game.monster.Monster;
import game.player.Player;
import game.player.skill.MagicCooldownManager;
import game.status.StatusEffectManager;
import game.status.StatusEffectType;

public class IceMagic {
    public static void useIceMagic(Player player, Monster monster) {
        if (player.attributes.iceMagicLevel > 0 && player.attributes.iceMagicCooldown == 0) {
            int damage = player.attributes.intelligence * player.attributes.iceMagicLevel;
            monster.hp -= damage;
            int dodgeReduction = 10 * player.attributes.iceMagicLevel;
            if (!MagicCooldownManager.originalDodgeValues.containsKey(monster)) {
                MagicCooldownManager.originalDodgeValues.put(monster, monster.dodge);
            }
            monster.dodge -= dodgeReduction;
            MagicCooldownManager.dodgeReductionTurns.put(monster, 3);
            monster.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.SLOWED, 3, 0));
            System.out.println("你使用了冰冻魔法，造成了 " + damage + " 点伤害，并且降低了怪物的速度！怪物的躲避值降低了 " + dodgeReduction + " 点。");
            player.attributes.iceMagicCooldown = 4;
        } else if (player.attributes.iceMagicCooldown > 0) {
            System.out.println("冰冻魔法正在冷却中，还需 " + player.attributes.iceMagicCooldown + " 回合。");
        } else {
            System.out.println("你还没有学习冰冻魔法！");
        }
    }

    public static void learnIceMagic(Player player) {
        if (player.attributes.skillPoints >= 5) {
            player.attributes.iceMagicLevel++;
            player.attributes.skillPoints -= 5;
            System.out.println("你学会了冰冻魔法！当前等级：" + player.attributes.iceMagicLevel);
        } else {
            System.out.println("技能点不足，无法学习冰冻魔法！");
        }
    }
}