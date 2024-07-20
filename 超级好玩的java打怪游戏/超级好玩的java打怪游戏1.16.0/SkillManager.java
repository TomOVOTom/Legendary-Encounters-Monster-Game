// SkillManager.java
package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SkillManager {
    private static Map<Monster, Integer> dodgeReductionTurns = new HashMap<>();
    private static Map<Monster, Integer> originalDodgeValues = new HashMap<>();

    // 使用技能的方法
    public static void useSkill(Player player, Monster monster, Scanner scanner) {
        AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\使用技能.wav", false, true, -30);
        System.out.println("请选择你的技能：1. 治疗 2. 火焰魔法 3. 吸血术 4. 闪电魔法 5. 冰冻魔法");
        int skillChoice = scanner.nextInt();
        if (skillChoice == 1) {
            AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\治疗魔法.wav", false, false, -5);
            useHealMagic(player);
        } else if (skillChoice == 2) {
            AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\火焰魔法.wav", false, false, -5);
            useFireMagic(player, monster);
        } else if (skillChoice == 3) {
            player.attack(monster); // 在攻击的同时吸血
            System.out.println("你使用了吸血术！");
        } else if (skillChoice == 4) {
            AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\闪电魔法.wav", false, false, -5);
            useLightningMagic(player, monster);
        } else if (skillChoice == 5) {
            AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\冰冻魔法.wav", false, false, -5);
            useIceMagic(player, monster);
        } else {
            System.out.println("无效的技能选择！");
        }
    }

    // 学习技能的方法
    public static void learnSkill(Player player, int skillChoice) {
        if (skillChoice == 1) { // 学习火焰魔法
            if (player.attributes.skillPoints >= 5) {
                player.attributes.fireMagicLevel++;
                player.attributes.skillPoints -= 5;
                System.out.println("你学会了火焰魔法！当前等级：" + player.attributes.fireMagicLevel);
            } else {
                System.out.println("技能点不足，无法学习火焰魔法！");
            }
        } else if (skillChoice == 2) { // 学习治疗魔法
            if (player.attributes.skillPoints >= 5) {
                player.attributes.healMagicLevel++;
                player.attributes.skillPoints -= 5;
                System.out.println("你学会了治疗魔法！当前等级：" + player.attributes.healMagicLevel);
            } else {
                System.out.println("技能点不足，无法学习治疗魔法！");
            }
        } else if (skillChoice == 3) { // 学习吸血术
            if (player.attributes.skillPoints >= 5) {
                player.attributes.vampirismLevel++;
                player.attributes.skillPoints -= 5;
                System.out.println("你学会了吸血术！当前等级：" + player.attributes.vampirismLevel);
            } else {
                System.out.println("技能点不足，无法学习吸血术！");
            }
        } else if (skillChoice == 4) { // 学习闪电魔法
            if (player.attributes.skillPoints >= 5) {
                player.attributes.lightningMagicLevel++;
                player.attributes.skillPoints -= 5;
                System.out.println("你学会了闪电魔法！当前等级：" + player.attributes.lightningMagicLevel);
            } else {
                System.out.println("技能点不足，无法学习闪电魔法！");
            }
        } else if (skillChoice == 5) { // 学习冰冻魔法
            if (player.attributes.skillPoints >= 5) {
                player.attributes.iceMagicLevel++;
                player.attributes.skillPoints -= 5;
                System.out.println("你学会了冰冻魔法！当前等级：" + player.attributes.iceMagicLevel);
            } else {
                System.out.println("技能点不足，无法学习冰冻魔法！");
            }
        } else {
            System.out.println("无效的技能选择！");
        }
    }

    // 添加一个方法来减少所有魔法的冷却时间
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

    // 修改使用治疗���法的方法
    public static void useHealMagic(Player player) {
        if (player.attributes.healMagicLevel > 0 && player.attributes.healMagicCooldown == 0) {
            player.attributes.hp += 50 * player.attributes.healMagicLevel;
            System.out.println("你使用了治疗魔法，恢复了 " + (50 * player.attributes.healMagicLevel) + " 点生命值！");
            player.attributes.healMagicCooldown = 3; // 设置冷却时间
        } else if (player.attributes.healMagicCooldown > 0) {
            System.out.println("治疗魔法正在冷却中，还需 " + player.attributes.healMagicCooldown + " 回合。");
        } else {
            System.out.println("你还没有学习治疗魔法！");
        }
    }

    // 修改使用火焰魔法的方法
    public static void useFireMagic(Player player, Monster monster) {
        if (player.attributes.fireMagicLevel > 0 && player.attributes.fireMagicCooldown == 0) {
            int damage = player.attributes.intelligence * player.attributes.fireMagicLevel; // 智力影响魔法伤害
            monster.hp -= damage;
            System.out.println("你使用了火焰魔法，造成了 " + damage + " 点伤害！");
            player.attributes.fireMagicCooldown = 3; // 设置冷却时间
        } else if (player.attributes.fireMagicCooldown > 0) {
            System.out.println("火焰魔法正在冷却中，还需 " + player.attributes.fireMagicCooldown + " 回合。");
        } else {
            System.out.println("你还没有学习火焰魔法！");
        }
    }

    // 使用闪电魔法的方法
    public static void useLightningMagic(Player player, Monster monster) {
        if (player.attributes.lightningMagicLevel > 0 && player.attributes.lightningMagicCooldown == 0) {
            int damage = player.attributes.intelligence * player.attributes.lightningMagicLevel * 2; // 闪电魔法伤害更高
            monster.hp -= damage;
            System.out.println("你使用了闪电魔法，造成了 " + damage + " 点伤害！");
            player.attributes.lightningMagicCooldown = 5; // 设置冷却时间
        } else if (player.attributes.lightningMagicCooldown > 0) {
            System.out.println("闪电魔法正在冷却中，还需 " + player.attributes.lightningMagicCooldown + " 回合。");
        } else {
            System.out.println("你还没有学习闪电魔法！");
        }
    }

    // 使用冰冻魔法的方法
    public static void useIceMagic(Player player, Monster monster) {
        if (player.attributes.iceMagicLevel > 0 && player.attributes.iceMagicCooldown == 0) {
            int damage = player.attributes.intelligence * player.attributes.iceMagicLevel;
            monster.hp -= damage;
            int dodgeReduction = 10 * player.attributes.iceMagicLevel;
            if (!originalDodgeValues.containsKey(monster)) {
                originalDodgeValues.put(monster, monster.dodge);
            }
            monster.dodge -= dodgeReduction; // 降低怪物的躲避值
            dodgeReductionTurns.put(monster, 3); // 设置恢复时间为3回合
            monster.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.SLOWED, 3, 0)); // 降低怪物的移速，持续3回合
            System.out.println("你使用了冰冻魔法，造成了 " + damage + " 点伤害，并且降低了怪物的速度！怪物的躲避值降低了 " + dodgeReduction + " 点。");
            player.attributes.iceMagicCooldown = 4; // 设置冷却时间
        } else if (player.attributes.iceMagicCooldown > 0) {
            System.out.println("冰冻魔法正在冷却中，还需 " + player.attributes.iceMagicCooldown + " 回合。");
        } else {
            System.out.println("你还没有学习冰冻魔法！");
        }
    }

    // 减少怪物躲避值恢复时间的方法
    private static void reduceDodgeReductionTurns() {
        for (Map.Entry<Monster, Integer> entry : dodgeReductionTurns.entrySet()) {
            Monster monster = entry.getKey();
            int turnsLeft = entry.getValue();
            if (turnsLeft > 0) {
                dodgeReductionTurns.put(monster, turnsLeft - 1);
            } else {
                monster.dodge = originalDodgeValues.get(monster); // 恢复原始躲避值
                originalDodgeValues.remove(monster);
                dodgeReductionTurns.remove(monster);
                System.out.println(monster.name + " 的躲避值恢复到了正常水平。");
            }
        }
    }
}