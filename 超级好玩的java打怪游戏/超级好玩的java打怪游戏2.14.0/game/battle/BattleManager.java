// File: src/game/battle/BattleManager.java
package game.battle;

import game.audio.AudioPlayer;
import game.audio.VolumeController;
import game.item.ItemManager;
import game.item.ItemRegistry;
import game.monster.Monster;
import game.mount.Mount;
import game.mount.MountManager;
import game.player.Player;
import game.player.skill.MagicCooldownManager;

import java.util.*;

import static game.battle.BattleResult.handleBattleResult;
import static game.inventory.InventoryManager.useItem;
import static game.monsters.MonsterFactory.createMonster;
import static game.player.PlayerStatus.displayPlayerStatus;
import static game.player.skill.SkillManager.*;
import static game.shop.Shop.enterShop;

public class BattleManager {
    public static void startBattle(Player player, Random random, Scanner scanner) {
        System.out.println("你选择了开始战斗！");
        Monster monster = createMonster(random);

        System.out.println("你遇到了一个 " + monster.level + "级" + monster.name + "！");
        battleLoop(player, monster, random, scanner);
    }

    public static void battleLoop(Player player, Monster monster, Random random, Scanner scanner) {
        while (player.attributes.hp > 0 && monster.hp > 0) {
            ItemManager.currentMonster = monster;
            System.out.println("你的 HP：" + player.attributes.hp + "，" + monster.name + " 的 HP：" + monster.hp);
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
                        mounts.sort(Comparator.comparingInt(Mount::getPrice)); // 按价格排序
                        for (int i = 0; i < mounts.size(); i++) {
                            System.out.println((i + 1) + ". " + mounts.get(i).getName());
                        }
                        int mountIndex = scanner.nextInt() - 1;
                        if (mountIndex >= 0 && mountIndex < mounts.size()) {
                            Mount selectedMount = mounts.get(mountIndex);
                            if (MountManager.ownsMount(player, selectedMount)) {
                                MountManager.equipMount(player, selectedMount);
                            } else {
                                System.out.println("你没有这个坐骑！");
                            }
                        } else {
                            System.out.println("无效的选择！");
                        }
                        break;
                    case 2:
                        MountManager.unequipMount(player);
                        break;
                    case 3:
                        if (MountManager.getEquippedMount(player) != null) {
                            MountManager.getEquippedMount(player).ride(player);
                        } else {
                            System.out.println("你没有装备任何坐骑！");
                        }
                        break;
                    case 4:
                        if (MountManager.getEquippedMount(player) != null) {
                            MountManager.getEquippedMount(player).rest();
                        } else {
                            System.out.println("你没有装备任何坐骑！");
                        }
                        break;
                    case 5:
                        if (MountManager.getEquippedMount(player) != null) {
                            MountManager.getEquippedMount(player).displayMountInfo();
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
            MagicCooldownManager.reduceMagicCooldowns(player);

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