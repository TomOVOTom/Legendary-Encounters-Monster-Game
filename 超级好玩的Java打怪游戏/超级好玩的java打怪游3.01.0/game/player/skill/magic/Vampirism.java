// File: src/game/player/skill/magic/Vampirism.java
package game.player.skill.magic;

import game.player.Player;
import game.monster.Monster;

public class Vampirism {
    public static void learnVampirism(Player player) {
        if (player.attributes.skillPoints >= 5) {
            player.attributes.vampirismLevel++;
            player.attributes.skillPoints -= 5;
            System.out.println("你学会了吸血术！当前等级：" + player.attributes.vampirismLevel);
        } else {
            System.out.println("技能点不足，无法学习吸血术！");
        }
    }

    public static void applyVampirism(Player player, Monster monster, int totalDamage) {
        if (player.attributes.vampirismLevel > 0 && player.attributes.stamina > 0 && totalDamage > 0) {
            int healAmount = (int) (totalDamage * player.attributes.vampirismLevel * 0.1);
            healAmount = Math.max(healAmount, 0);

            player.attributes.hp += healAmount;
            player.attributes.stamina -= 5;
            monster.attributes.hp -= healAmount;
            System.out.println("你吸取了 " + healAmount + " 点生命值！ 体力消耗了 5 点。");
        }
    }
}