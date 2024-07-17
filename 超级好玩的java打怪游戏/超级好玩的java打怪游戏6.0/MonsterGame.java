// MonsterGame.java
package game;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static game.BattleManager.startBattle;
import static game.VolumeController.setVolume;

public class MonsterGame {

    public static void main(String[] args) {

        // 默认不播放背景音乐
        // AudioPlayer.playSound("E:\\BaiduNetdiskDownload\\2023年短视频热歌\\where did u go.WAV", true, true, -15);

        // 在游戏启动时注册所有道具和装备
        GameInitializer.registerAllItems();

        // 游戏的其他启动逻辑
        System.out.println("游戏启动中...");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Player player = null;
        Mount horse = new Mount("战马", 50, 100,10,10,6); // Initialize the mount here


        while (true) {
            System.out.println("请选择你的行动：1. 新游戏 2. 读取存档 3. 删除存档");
            int initialChoice = scanner.nextInt();
            if (initialChoice == 1) {
                player = new Player("宇宙创造神", 200, 35, 15, 30, 100, 80);  // 设置玩家属性
                break;
            } else if (initialChoice == 2) {
                List<String> saves = GameSaveManager.listSaves();
                if (saves.isEmpty()) {
                    System.out.println("没有可用的存档。");
                    player = new Player("宇宙创造神", 200, 35, 15, 30, 100, 80);  // 设置玩家属性
                } else {
                    System.out.println("请选择一个存档：");
                    for (int i = 0; i < saves.size(); i++) {
                        System.out.println((i + 1) + ". " + saves.get(i));
                    }
                    int saveChoice = scanner.nextInt();
                    player = GameSaveManager.loadGame(saves.get(saveChoice - 1));
                    if (player == null) {
                        player = new Player("宇宙创造神", 200, 35, 15, 30, 100, 80);  // 设置玩家属性
                    }
                }
                break;
            } else if (initialChoice == 3) {
                while (true) {
                    List<String> saves = GameSaveManager.listSaves();
                    if (saves.isEmpty()) {
                        System.out.println("没有可用的存档。");
                        break;
                    } else {
                        System.out.println("请选择要删除的存档（输入0退出）：");
                        for (int i = 0; i < saves.size(); i++) {
                            System.out.println((i + 1) + ". " + saves.get(i));
                        }
                        int saveChoice = scanner.nextInt();
                        if (saveChoice == 0) {
                            break;
                        } else {
                            GameSaveManager.deleteSave(saves.get(saveChoice - 1));
                        }
                    }
                }
            }
        }

        while (player.hp > 0) { // 当玩家还有生命值时，继续战斗

            System.out.println("请选择你的行动：1. 探索 2. 休息 3. 查看装备和物品 4. 保存游戏 5. 开始战斗 6. 退出游戏 7. 设置音量 8. 播放背景音乐 9. 管理坐骑");
            int actionChoice = scanner.nextInt();

            switch (actionChoice) {
                case 1:
                    // 实现探索逻辑
                    System.out.println("你开始探索周围的环境...");
                    break;
                case 2:
                    // 实现休息逻辑
                    player.rest();
                    break;
                case 3:
                    System.out.println("你查看了当前的装备和持有的物品...");
                    InventoryManager.displayInventory(player);
                    break;
                case 4:
                    System.out.println("请输入存档名称：");
                    String saveName = scanner.next();
                    GameSaveManager.saveGame(player, saveName);
                    break;
                case 5:
                    startBattle(player, random, scanner);
                    break;
                case 6:
                    System.out.println("你选择了退出游戏。");
                    System.exit(0);
                    break;
                case 7:
                    setVolume(scanner);
                    break;
                case 8:
                    AudioPlayer.playSound("E:\\BaiduNetdiskDownload\\2023年短视频热歌\\where did u go.WAV", true, true, -15);
                    break;


                case 9:
                    System.out.println("请选择你的坐骑操作：1. 装备坐骑 2. 卸下坐骑 3. 骑乘坐骑 4. 休息坐骑 5. 查看坐骑信息");
                    int mountChoice = scanner.nextInt();
                    switch (mountChoice) {
                        case 1:
                            player.equipMount(horse);
                            break;
                        case 2:
                            player.unequipMount();
                            break;
                        case 3:
                            player.rideMount();
                            break;
                        case 4:
                            player.restMount();
                            break;
                        case 5:
                            horse.displayMountInfo();
                            break;
                        default:
                            System.out.println("无效的选择！");
                    }
                    break;
            }

        }
        System.out.println("游戏结束！");
        scanner.close();
    }
}