// abilities/AbilityGroup1.java
package game.abilities;

import game.audio.AudioPlayer;
import game.monster.Monster;
import game.player.Player;
import game.status.StatusEffectManager;
import game.status.StatusEffectType;

import java.util.Random;

public class AbilityGroup1 {
    public static void useAbility(Monster monster, Player player, String abilityName) {
        int staminaCost;
        int damage;
        switch (abilityName) {
            case "毒素":
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\毒素.wav", false, false, -5);
                staminaCost = 5;
                if (monster.attributes.
                        stamina >= staminaCost) {
                    damage = 10 + monster.attributes.
                            level * 2;
                    player.attributes.hp -= damage;
                    System.out.println(monster.attributes.
                            name + " 使用了" + abilityName + " 对玩家造成了" + damage + "点伤害。");
                    player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.POISONED, 3, 5));
                    System.out.println(monster.attributes.
                            name + " 使用了毒素，对玩家造成了中毒效果。");
                    monster.attributes.
                            stamina -= staminaCost;
                }
                break;
            case "熔岩爆发":
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\熔岩爆发.wav", false, false, -5);
                staminaCost = 6;
                if (monster.attributes.
                        stamina >= staminaCost) {
                    damage = 30 + monster.attributes.
                            level * 5;
                    System.out.println(monster.attributes.
                            name + " 使用了 " + abilityName + " 对玩家造成了 " + damage + " 点伤害。");
                    player.attributes.hp -= damage;
                    if (new Random().nextInt(100) < 80) {
                        player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.BURNING, 3, 15 * player.attributes.level * 2));
                        System.out.println("玩家被点燃了！");
                    }
                    monster.attributes.
                            stamina -= staminaCost;
                }
                break;
            case "地震":
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\地震.wav", false, false, -5);
                staminaCost = 5;
                if (monster.attributes.
                        stamina >= staminaCost) {
                    damage = 50 + player.attributes.level * 8;
                    player.attributes.hp -= damage;
                    System.out.println(monster.attributes.
                            name + " 使用了地震，对玩家造成了 " + damage + " 点伤害。");
                    if (new Random().nextInt(100) < 90) {
                        player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.SLOWED, 4, -30));
                        System.out.println("玩家的速度降低了！");
                    }
                    monster.attributes.
                            stamina -= staminaCost;
                }
                break;
            case "暗影爆发":
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\暗影爆发.wav", false, false, -5);
                staminaCost = 10;
                if (monster.attributes.
                        stamina >= staminaCost) {
                    damage = 50 + monster.attributes.
                            level * 10;
                    System.out.println(monster.attributes.
                            name + " 使用了暗影爆发，对玩家造成了 " + damage + " 点伤害。");
                    player.attributes.hp -= damage;
                    player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.FEAR, 3 + monster.attributes.
                            level / 2, -10));
                    monster.attributes.
                            stamina -= staminaCost;
                }
                break;
        }
    }
}
