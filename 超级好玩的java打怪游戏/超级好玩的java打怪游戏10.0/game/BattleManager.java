// BattleManager.java
package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static game.BattleResult.handleBattleResult;
import static game.InventoryManager.useItem;
import static game.MonsterFactory.createMonster;
import static game.PlayerStatus.displayPlayerStatus;
import static game.Shop.enterShop;
import static game.SkillManager.*;

public class BattleManager {
    public static void startBattle(Player player, Random random, Scanner scanner) {
        System.out.println("你选择了开始战斗！");
        game.Monster monster = createMonster(random);

        Game.currentMonster = monster;

        System.out.println("你遇到了一个 " + monster.level + "级" + monster.name + "！");

        while (player.hp > 0 && monster.hp > 0) {
            System.out.println("你的 HP：" + player.hp + "，" + monster.name + " 的 HP：" + monster.hp);
            System.out.println("请选择你的行动：1. 攻击 2. 使用技能 3. 使用道具 4. 查看状态 5. 商店 6. 学习技能 7. 设置音量 8. 管理坐骑");
            int choice = -1;
            while (choice < 1 || choice > 8) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 8) {
                        System.out.println("无效的选项，请输入一个有效的数字（1-8）：");
                    }
                } else {
                    System.out.println("无效的输入，请输入一个数字：");
                    scanner.next();
                }
            }
            if (choice == 1) {
                if (player.canAttack()) {
                    AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\快速攻击音效.wav", false, false, -5);
                    player.attack(monster);
                } else {
                    System.out.println("你受到状态效果的影响，无法在这一回合攻击！");
                }
            } else if (choice == 2) {
                useSkill(player, monster, scanner);
            } else if (choice == 3) {
                useItem(player, scanner);
            } else if (choice == 4) {
                displayPlayerStatus(player, monster);
            } else if (choice == 5) {
                enterShop(player, scanner);
            } else if (choice == 6) {
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\魔法钢琴旋律循环.wav", true, true, -10);
                System.out.println("请选择要学习的技能：1. 火焰魔法 2. 治疗魔法 3. 吸血术 4. 闪电魔法 5. 冰冻魔法");
                int skillChoice = scanner.nextInt();
                learnSkill(player, skillChoice);
                AudioPlayer.stopEffect();
                AudioPlayer.playSound("Music/精卫.WAV", true, true, -15);
            } else if (choice == 7) {
                if (!VolumeController.setVolume(scanner)) {
                    continue;
                }
            } else if (choice == 8) {
                System.out.println("请选择你的坐骑操作：1. 装备坐骑 2. 卸下坐骑 3. 骑乘坐骑 4. 休息坐骑 5. 查看坐骑信息");
                int mountChoice = scanner.nextInt();
                switch (mountChoice) {
                    case 1:
                        System.out.println("请选择要装备的坐骑：");
                        List<Mount> mounts = new ArrayList<>(ItemRegistry.getAllMounts().values());
                        for (int i = 0; i < mounts.size(); i++) {
                            System.out.println((i + 1) + ". " + mounts.get(i).getName());
                        }
                        int mountIndex = scanner.nextInt() - 1;
                        if (mountIndex >= 0 && mountIndex < mounts.size()) {
                            Mount selectedMount = mounts.get(mountIndex);
                            if (player.ownsMount(selectedMount)) {
                                player.equipMount(selectedMount);
                            } else {
                                System.out.println("你没有这个坐骑！");
                            }
                        } else {
                            System.out.println("无效的选择！");
                        }
                        break;
                    case 2:
                        player.unequipMount();
                        break;
                    case 3:
                        if (player.getEquippedMount() != null) {
                            player.getEquippedMount().ride(player);
                        } else {
                            System.out.println("你没有装备任何坐骑！");
                        }
                        break;
                    case 4:
                        if (player.getEquippedMount() != null) {
                            player.getEquippedMount().rest();
                        } else {
                            System.out.println("���没有装备任何坐骑！");
                        }
                        break;
                    case 5:
                        if (player.getEquippedMount() != null) {
                            player.getEquippedMount().displayMountInfo();
                        } else {
                            System.out.println("你没有装备任何坐骑！");
                        }
                        break;
                    default:
                        System.out.println("无效的选择！");
                }
            } else {
                System.out.println("无效的选项！");
            }
            reduceMagicCooldowns(player);

            if (monster.hp > 0) {
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\刀刺音效.wav", false, false, -10);
                new Attack(monster, player).attackRandomTarget();
                monster.useAbility(player);
                player.updateStatusEffects();
            }
        }

        handleBattleResult(player, monster);
    }
}