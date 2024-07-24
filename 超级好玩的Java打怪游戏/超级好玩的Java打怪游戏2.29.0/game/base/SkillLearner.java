// game/base/SkillLearner.java
package game.base;

import game.player.Player;
import game.player.skill.SkillManager;

import java.util.Scanner;

public class SkillLearner {
    public static void learnSkills(Player player, Scanner scanner) {
        System.out.println("请选择要学习的技能：1. 火焰魔法 2. 治疗魔法 3. 吸血术 4. 闪电魔法 5. 冰冻魔法");
        int skillChoice = scanner.nextInt();
        SkillManager.learnSkill(player, skillChoice);
    }
}