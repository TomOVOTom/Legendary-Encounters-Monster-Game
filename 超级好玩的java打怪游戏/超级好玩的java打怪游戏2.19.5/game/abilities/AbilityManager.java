package game.abilities;

import game.monster.Monster;
import game.player.Player;

import java.util.Random;

public class AbilityManager {
    public static void useAbility(Monster monster, Player player) {
        Random random = new Random();
        int chance = random.nextInt(100) + 1;
        int threshold = 75;

        if (chance <= threshold) {
            if (monster.abilityCooldown <= 0) {
                switch (monster.abilityName) {
                    case "毒素":
                    case "熔岩爆发":
                    case "地震":
                    case "暗影爆发":
                        game.abilities.AbilityGroup1.useAbility(monster, player, monster.abilityName);
                        break;
                    case "冰封禁锢":
                    case "隐身攻击":
                    case "火焰吐息":
                    case "石化":
                        game.abilities.AbilityGroup2.useAbility(monster, player, monster.abilityName);
                        break;
                    case "治疗":
                    case "狂暴":
                    case "护盾":
                    case "召唤":
                        game.abilities.AbilityGroup3.useAbility(monster, player, monster.abilityName);
                        break;
                    case "雷霆一击":
                    case "风刃":
                    case "水疗":
                        game.abilities.AbilityGroup4.useAbility(monster, player, monster.abilityName);
                        break;
                }
                monster.abilityCooldown = monster.initialAbilityCooldown;
            }
        } else {
            System.out.println(monster.name + " 在这个回合选择了不使用任何技能。");
        }

        if (monster.abilityCooldown > 0) {
            monster.abilityCooldown--;
        }
    }
}