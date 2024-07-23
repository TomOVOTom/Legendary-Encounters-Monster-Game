// File: src/game/main/GameActions.java
package game.main;

import game.audio.AudioPlayer;
import game.base.BaseManager;
import game.exploration.Explorer;
import game.inventory.InventoryManager;
import game.player.Player;
import game.quest.QuestManager;
import game.save.GameSaveManager;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static game.audio.VolumeController.setVolume;
import static game.battle.BattleManager.startBattle;
import static game.pet.PetManager.managePets;

public class GameActions {
    public static void performAction(Player player, Random random, Scanner scanner, QuestManager questManager) {
        System.out.println("请选择你的行动：1. 探索 2. 基地 3. 查看装备和物品 4. 保存游戏 5. 开始战斗 6. 退出游戏 7. 设置音量 8. 播放背景音乐 9. 读取存档 10. 管理宠物");
        int actionChoice = scanner.nextInt();

        switch (actionChoice) {
            case 1:
                Explorer.explore(player, random, questManager);
                break;
            case 2:
                BaseManager.baseActions(player, scanner);
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
                AudioPlayer.playSound("Music/where did u go.WAV", true, true, -15);
                break;
            case 9:
                List<String> saves = GameSaveManager.listSaves();
                if (saves.isEmpty()) {
                    System.out.println("没有可用的存档。");
                } else {
                    System.out.println("请选择一个存档：");
                    for (int i = 0; i < saves.size(); i++) {
                        System.out.println((i + 1) + ". " + saves.get(i));
                    }
                    int saveChoice = scanner.nextInt();
                    Player loadedPlayer = GameSaveManager.loadGame(saves.get(saveChoice - 1));
                    if (loadedPlayer != null) {
                        player = loadedPlayer;
                        System.out.println("存档已加载。");
                    } else {
                        System.out.println("加载存档失败。");
                    }
                }
                break;
            case 10:
                managePets(player, scanner);
                break;
            default:
                System.out.println("无效的选择！");
        }
    }
}