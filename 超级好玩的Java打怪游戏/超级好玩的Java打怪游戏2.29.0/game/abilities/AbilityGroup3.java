// abilities/AbilityGroup3.java
package game.abilities;

import game.audio.AudioPlayer;
import game.monster.Monster;
import game.player.Player;

public class AbilityGroup3 {
    public static void useAbility(Monster monster, Player player, String abilityName) {
        switch (abilityName) {
            case "治疗":
                int staminaCost = 5;
                if (monster.attributes.stamina >= staminaCost) {
                    monster.attributes.hp += monster.attributes.stamina * 0.5;
                    System.out.println(monster.attributes.name + " 使用了治疗！恢复了 " + (monster.attributes.stamina * 0.5) + " 点生命值。");
                    monster.attributes.stamina -= staminaCost;
                }
                break;
            case "狂暴":
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\狂暴.wav", false, false, -5);
                staminaCost = 5;
                if (monster.attributes.stamina >= staminaCost) {
                    monster.attributes.attack *= 1.3;
                    System.out.println(monster.attributes.name + " 使用了狂暴！攻击力提高了 30%。");
                    monster.attributes.stamina -= staminaCost;
                }
                break;
            case "护盾":
                staminaCost = 2;
                if (monster.attributes.stamina >= staminaCost) {
                    monster.attributes.defense*= 1.5;
                    System.out.println(monster.attributes.name + " 使用了护盾！防御力提高了 50%。");
                    monster.attributes.stamina -= staminaCost;
                }
                break;
            case "召唤":
                staminaCost = 30;
                if (monster.attributes.stamina >= staminaCost) {
                    System.out.println(monster.attributes.name + " 使用了召唤！生成了一个新的怪物。");
                    monster.attributes.stamina -= staminaCost;
                }
                break;
        }
    }
}