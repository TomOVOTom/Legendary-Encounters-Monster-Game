// File: src/game/player/skill/magic/HealMagic.java
package game.player.skill.magic;

import game.player.Player;

public class HealMagic {
    public static void useHealMagic(Player player) {
        if (player.attributes.healMagicLevel > 0 && player.attributes.healMagicCooldown == 0) {
            player.attributes.hp += 50 * player.attributes.healMagicLevel;
            System.out.println("你使用了治疗魔法，恢复了 " + (50 * player.attributes.healMagicLevel) + " 点生命值！");
            player.attributes.healMagicCooldown = 3;
        } else if (player.attributes.healMagicCooldown > 0) {
            System.out.println("治疗魔法正在冷却中，还需 " + player.attributes.healMagicCooldown + " 回合。");
        } else {
            System.out.println("你还没有学习治疗魔法！");
        }
    }

    public static void learnHealMagic(Player player) {
        if (player.attributes.skillPoints >= 5) {
            player.attributes.healMagicLevel++;
            player.attributes.skillPoints -= 5;
            System.out.println("你学会了治疗魔法！当前等级：" + player.attributes.healMagicLevel);
        } else {
            System.out.println("技能点不足，无法学习治疗魔法！");
        }
    }
}