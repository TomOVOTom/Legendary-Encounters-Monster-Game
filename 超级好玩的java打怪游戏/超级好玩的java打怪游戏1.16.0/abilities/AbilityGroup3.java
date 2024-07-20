// abilities/AbilityGroup3.java
package game.abilities;

import game.*;

public class AbilityGroup3 {
    public static void useAbility(Monster monster, Player player, String abilityName) {
        switch (abilityName) {
            case "治疗":
                int staminaCost = 5;
                if (monster.stamina >= staminaCost) {
                    monster.hp += monster.stamina * 0.5;
                    System.out.println(monster.name + " 使用了治疗！恢复了 " + (monster.stamina * 0.5) + " 点生命值。");
                    monster.stamina -= staminaCost;
                }
                break;
            case "狂暴":
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\狂暴.wav", false, false, -5);
                staminaCost = 5;
                if (monster.stamina >= staminaCost) {
                    monster.attack *= 1.3;
                    System.out.println(monster.name + " 使用了狂暴！攻击力提高了 30%。");
                    monster.stamina -= staminaCost;
                }
                break;
            case "护盾":
                staminaCost = 2;
                if (monster.stamina >= staminaCost) {
                    monster.defense *= 1.5;
                    System.out.println(monster.name + " 使用了护盾！防御力提高了 50%。");
                    monster.stamina -= staminaCost;
                }
                break;
            case "召唤":
                staminaCost = 30;
                if (monster.stamina >= staminaCost) {
                    System.out.println(monster.name + " 使用了召唤！生成了一个新的怪物。");
                    monster.stamina -= staminaCost;
                }
                break;
        }
    }
}