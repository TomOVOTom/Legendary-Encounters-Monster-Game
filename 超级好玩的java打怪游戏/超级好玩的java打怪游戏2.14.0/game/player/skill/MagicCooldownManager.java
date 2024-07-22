// File: src/game/player/skill/MagicCooldownManager.java
package game.player.skill;

import game.monster.Monster;
import game.player.Player;

import java.util.HashMap;
import java.util.Map;

public class MagicCooldownManager {
    public static Map<Monster, Integer> dodgeReductionTurns = new HashMap<>();
    public static Map<Monster, Integer> originalDodgeValues = new HashMap<>();

    public static void reduceMagicCooldowns(Player player) {
        if (player.attributes.fireMagicCooldown > 0) {
            player.attributes.fireMagicCooldown--;
        }
        if (player.attributes.healMagicCooldown > 0) {
            player.attributes.healMagicCooldown--;
        }
        if (player.attributes.lightningMagicCooldown > 0) {
            player.attributes.lightningMagicCooldown--;
        }
        if (player.attributes.iceMagicCooldown > 0) {
            player.attributes.iceMagicCooldown--;
        }
        reduceDodgeReductionTurns();
    }

    public static void reduceDodgeReductionTurns() {
        for (Map.Entry<Monster, Integer> entry : dodgeReductionTurns.entrySet()) {
            Monster monster = entry.getKey();
            int turnsLeft = entry.getValue();
            if (turnsLeft > 0) {
                dodgeReductionTurns.put(monster, turnsLeft - 1);
            } else {
                monster.dodge = originalDodgeValues.get(monster);
                originalDodgeValues.remove(monster);
                dodgeReductionTurns.remove(monster);
                System.out.println(monster.name + " 的躲避值恢复到了正常水平。");
            }
        }
    }
}