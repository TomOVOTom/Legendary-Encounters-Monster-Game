// File: src/game/main/GameLoader.java
package game.main;

import game.player.Player;
import game.save.GameSaveManager;

import java.util.List;
import java.util.Scanner;

public class GameLoader {
    public static Player loadGame(Scanner scanner) {
        Player player = null;
        while (true) {
            System.out.println("请选择你的行动：1. 新游戏 2. 读取存档 3. 删除存档");
            int initialChoice = scanner.nextInt();
            if (initialChoice == 1) {
                player = new Player("宇宙创造神", 300, 45, 15, 30, 100, 80);
                break;
            } else if (initialChoice == 2) {
                List<String> saves = GameSaveManager.listSaves();
                if (saves.isEmpty()) {
                    System.out.println("没有可用的存档。");
                    player = new Player("宇宙创造神", 300, 45, 15, 30, 100, 80);
                } else {
                    System.out.println("请选择一个存档：");
                    for (int i = 0; i < saves.size(); i++) {
                        System.out.println((i + 1) + ". " + saves.get(i));
                    }
                    int saveChoice = scanner.nextInt();
                    player = GameSaveManager.loadGame(saves.get(saveChoice - 1));
                    if (player == null) {
                        player = new Player("宇宙创造神", 200, 35, 15, 30, 100, 80);
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
        return player;
    }
}