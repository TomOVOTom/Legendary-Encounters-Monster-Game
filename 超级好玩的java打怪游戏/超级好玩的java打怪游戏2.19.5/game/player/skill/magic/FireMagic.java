// File: src/game/player/skill/magic/FireMagic.java
package game.player.skill.magic;

import game.monster.Monster;
import game.player.Player;

public class FireMagic {
    public static void useFireMagic(Player player, Monster monster) {
        if (player.attributes.fireMagicLevel > 0 && player.attributes.fireMagicCooldown == 0) {
            int damage = player.attributes.intelligence * player.attributes.fireMagicLevel;
            monster.hp -= damage;
            System.out.println("你使用了火焰魔法，造成了 " + damage + " 点伤害！");
            player.attributes.fireMagicCooldown = 3;
        } else if (player.attributes.fireMagicCooldown > 0) {
            System.out.println("火焰魔法正在冷却中，还需 " + player.attributes.fireMagicCooldown + " 回合。");
        } else {
            System.out.println("你还没有学习火焰魔法！");
        }
    }

    public static void learnFireMagic(Player player) {
        if (player.attributes.skillPoints >= 5) {
            player.attributes.fireMagicLevel++;
            player.attributes.skillPoints -= 5;
            System.out.println("你学会了火焰魔法！当前等级：" + player.attributes.fireMagicLevel);
        } else {
            System.out.println("技能点不足，无法学习火焰魔法！");
        }
    }
}