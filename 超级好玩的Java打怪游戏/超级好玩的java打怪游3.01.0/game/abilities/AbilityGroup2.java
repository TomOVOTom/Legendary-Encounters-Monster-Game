package game.abilities;

import game.audio.AudioPlayer;
import game.monster.Monster;
import game.player.Player;
import game.status.StatusEffectManager;
import game.status.StatusEffectType;

public class AbilityGroup2 {
    public static void useAbility(Monster monster, Player player, String abilityName) {
        switch (abilityName) {
            case "冰封禁锢":
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\冰封禁锢.wav", false, false, -5);
                int staminaCostFrozen = 4;
                if (monster.attributes.stamina >= staminaCostFrozen) {
                    System.out.println(monster.attributes.name + " 使用了 " + monster.attributes.abilityName + "，玩家被冻结了一回合。");
                    player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.FROZEN, 1, 23));
                    player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.CHILLED, 4, 10));
                    monster.attributes.stamina -= staminaCostFrozen;
                }
                break;
            case "隐身攻击":
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\隐身攻击.wav", false, false, -5);
                int staminaCost = 3;
                if (monster.attributes.stamina >= staminaCost) {
                    monster.attributes.dodge += 20;
                    System.out.println(monster.attributes.name + " 使用了隐身攻击！躲避率暂时提高。");
                    monster.attributes.stamina -= staminaCost;
                }
                break;
            case "火焰吐息":
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\火焰吐息.wav", false, false, -5);
                staminaCost = 3;
                if (monster.attributes.stamina >= staminaCost) {
                    player.attributes.hp -= monster.attributes.intelligence * 2;
                    System.out.println(monster.attributes.name + " 使用了火焰吐息！你受到 " + (monster.attributes.intelligence * 2) + " 点伤害。");
                    monster.attributes.stamina -= staminaCost;
                }
                break;
            case "石化":
                staminaCost = 5;
                if (monster.attributes.stamina >= staminaCost) {
                    if (!player.isStone) {
                        player.originalAttack = player.attributes.attack;
                    }
                    player.attributes.attack /= 2;
                    player.isStone = true;
                    System.out.println(monster.attributes.name + " 使用了石化！你的攻击力降低 50%。");
                    monster.attributes.stamina -= staminaCost;
                }
                break;
        }
    }
}