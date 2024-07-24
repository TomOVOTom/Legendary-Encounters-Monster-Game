package game.abilities;

import game.audio.AudioPlayer;
import game.monster.Monster;
import game.player.Player;
import game.status.StatusEffectManager;
import game.status.StatusEffectType;

import java.util.Random;

public class AbilityGroup4 {
    public static void useAbility(Monster monster, Player player, String abilityName) {
        switch (abilityName) {
            case "雷霆一击":
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\雷霆一击.wav", false, false, -5);
                int staminaCost = 6;
                if (monster.attributes.stamina >= staminaCost) {
                    int damageThunderStrike = 40 + monster.attributes.level * 6;
                    System.out.println(monster.attributes.name + " 使用了 " + monster.attributes.abilityName + " 对玩家造成了 " + damageThunderStrike + " 点伤害。");
                    player.attributes.hp -= damageThunderStrike;
                    if (new Random().nextInt(100) < 90) {
                        player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.STUNNED, 3, 0));
                        System.out.println("玩家被震晕了！");
                    }
                    monster.attributes.stamina -= staminaCost;
                }
                break;
            case "风刃":
                AudioPlayer.playSound("Music/风刃.wav", false, false, -5);
                staminaCost = 4;
                if (monster.attributes.stamina >= staminaCost) {
                    int damageWindBlade = 20 + monster.attributes.level * 4;
                    System.out.println(monster.attributes.name + " 使用了风刃，对玩家造成了 " + damageWindBlade + " 点伤害。");
                    player.attributes.hp -= damageWindBlade;
                    monster.attributes.stamina -= staminaCost;
                }
                break;
            case "水疗":
                staminaCost = 5;
                if (monster.attributes.stamina >= staminaCost) {
                    monster.attributes.hp += monster.attributes.intelligence * 3;
                    System.out.println(monster.attributes.name + " 使用了水疗！恢复了 " + (monster.attributes.intelligence * 3) + " 点生命值。");
                    monster.attributes.stamina -= staminaCost;
                }
                break;
        }
    }
}