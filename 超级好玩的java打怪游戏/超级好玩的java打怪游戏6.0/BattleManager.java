// BattleManager.java
package game;

import java.util.Random;
import java.util.Scanner;

import static game.BattleResul.handleBattleResult;
import static game.InventoryManager.useItem;
import static game.MonsterFactory.createMonster;
import static game.PlayerStatus.displayPlayerStatus;
import static game.Shop.enterShop;
import static game.SkillManager.useSkill;

public class BattleManager {
    public static void startBattle(Player player, Random random, Scanner scanner) {
        System.out.println("你选择了开始战斗！");
        game.Monster monster = createMonster(random); // 创建怪物

        // 在战斗开始的方法中
        Game.currentMonster = monster;

        System.out.println("你遇到了一个 " + monster.level + "级" + monster.name + "！");

        while (player.hp > 0 && monster.hp > 0) {
            // 玩家回合
            System.out.println("你的 HP：" + player.hp + "，" + monster.name + " 的 HP：" + monster.hp);
            System.out.println("请选择你的行动：1. 攻击 2. 使用技能 3. 使用道具 4. 查看状态 5. 商店 6. 学习技能 7. 设置音量");
            int choice = -1;
            while (choice < 1 || choice > 7) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 7) {
                        System.out.println("无效的选项，请输入一个有效的数字（1-7）：");
                    }
                } else {
                    System.out.println("无效的输入，请输入一个数字：");
                    scanner.next(); // 清除无效输入
                }
            }
            if (choice == 1) {
                if (player.canAttack()) {
                    // 播放攻击音效，确保不循环播放
                    AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\快速攻击音效.wav", false, false, -5);
                    // 玩家攻击
                    player.attack(monster);
                } else {
                    System.out.println("你受到恐惧效果的影响，无法在这一回合攻击！");
                }
            } else if (choice == 2) {
                // 玩家使用技能
                useSkill(player, monster, scanner);
            } else if (choice == 3) {
                // 玩家使用道具
                useItem(player, scanner);
            } else if (choice == 4) {
                // 查看状态
                displayPlayerStatus(player, monster);
            } else if (choice == 5) {
                // 进入商店
                enterShop(player, scanner);
            } else if (choice == 6) {
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\魔法钢琴旋律循环.wav", true, true, -10);
                // 学习技能
                System.out.println("请选择要学习的技能：1. 火焰魔法 2. 治疗魔法 3. 吸血术");
                int skillChoice = scanner.nextInt();
                player.learnSkill(skillChoice);
                AudioPlayer.stopEffect(); // 假设这会停止当前的效果音
                AudioPlayer.playSound("E:\\BaiduNetdiskDownload\\2023年短视频热歌\\精卫.WAV", true, true, -10);
            } else if (choice == 7) {
                if (!VolumeController.setVolume(scanner)) {
                    continue; // 继续当前回合
                }
            } else {
                System.out.println("无效的选项！");
            }
            player.reduceMagicCooldowns();

            // 怪物回合
            if (monster.hp > 0) {
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\刀刺音效.wav", false, false, -10);
                monster.attack(player);
                monster.useAbility(player); // 怪物使用技能
                player.updateStatusEffects(); // 再次更新玩家状态效果
            }
        }

        handleBattleResult(player, monster); // 处理战斗结果
    }
}