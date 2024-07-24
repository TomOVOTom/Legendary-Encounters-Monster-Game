// File: src/game/player/skill/SkillManager.java
package game.player.skill;

import game.audio.AudioPlayer;
import game.monster.Monster;
import game.player.Player;

import java.util.Scanner;

public class SkillManager {
    public static void useSkill(Player player, Monster monster, Scanner scanner) {
        System.out.println("请选择你的技能：1. 治疗 2. 火焰魔法 3. 吸血术 4. 闪电魔法 5. 冰冻魔法");
        int skillChoice = scanner.nextInt();
        switch (skillChoice) {
            case 1:
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\治疗魔法.wav", false, false, -5);
                MagicSkill.useHealMagic(player);
                break;
            case 2:
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\火焰魔法.wav", false, false, -5);
                MagicSkill.useFireMagic(player, monster);
                break;
            case 3:
                player.attack(monster);
                System.out.println("你使用了吸血术！");
                break;
            case 4:
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\闪电魔法.wav", false, false, -5);
                MagicSkill.useLightningMagic(player, monster);
                break;
            case 5:
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\冰冻魔法.wav", false, false, -5);
                MagicSkill.useIceMagic(player, monster);
                break;
            default:
                System.out.println("无效的技能选择！");
        }
    }

    public static void learnSkill(Player player, int skillChoice) {
        switch (skillChoice) {
            case 1:
                MagicSkill.learnFireMagic(player);
                break;
            case 2:
                MagicSkill.learnHealMagic(player);
                break;
            case 3:
                MagicSkill.learnVampirism(player);
                break;
            case 4:
                MagicSkill.learnLightningMagic(player);
                break;
            case 5:
                MagicSkill.learnIceMagic(player);
                break;
            default:
                System.out.println("无效的技能选择！");
        }
    }
}