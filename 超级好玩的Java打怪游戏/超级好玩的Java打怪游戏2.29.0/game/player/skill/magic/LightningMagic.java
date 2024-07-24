// File: src/game/player/skill/magic/LightningMagic.java
package game.player.skill.magic;

import game.monster.Monster;
import game.player.Player;

public class LightningMagic {
    public static void useLightningMagic(Player player, Monster monster) {
        if (player.attributes.lightningMagicLevel > 0 && player.attributes.lightningMagicCooldown == 0) {
            int damage = player.attributes.intelligence * player.attributes.lightningMagicLevel * 2;
            monster.attributes.hp -= damage;
            System.out.println("你使用了闪电魔法，造成了 " + damage + " 点伤害！");
            player.attributes.lightningMagicCooldown = 5;
        } else if (player.attributes.lightningMagicCooldown > 0) {
            System.out.println("闪电魔法正在冷却中，还需 " + player.attributes.lightningMagicCooldown + " 回合。");
        } else {
            System.out.println("你还没有学习闪电魔法！");
        }
    }

    public static void learnLightningMagic(Player player) {
        if (player.attributes.skillPoints >= 5) {
            player.attributes.lightningMagicLevel++;
            player.attributes.skillPoints -= 5;
            System.out.println("你���会了闪电魔法！当前等级：" + player.attributes.lightningMagicLevel);
        } else {
            System.out.println("技能点不足，无法学习闪电魔法！");
        }
    }
}