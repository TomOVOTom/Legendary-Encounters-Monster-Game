//// File: src/game/main/MonsterGame.java
//package game.main;
//
//import game.initializer.GameInitializer;
//import game.player.Player;
//import game.quest.QuestManager;
//
//
//import java.util.Random;
//import java.util.Scanner;
//
//public class MonsterGame {
//
//    public static void main(String[] args) {
//        GameInitializer.registerAll();
//        System.out.println("游戏启动中...");
//        System.out.println("欢迎来到怪物游戏！");
//        Scanner scanner = new Scanner(System.in);
//        Random random = new Random();
//        Player player = GameLoader.loadGame(scanner);
//        QuestManager questManager = new QuestManager();
//
//        while (player.attributes.hp > 0) {
//            GameActions.performAction(player, random, scanner, questManager);
//        }
//        System.out.println("游戏结束！");
//        scanner.close();
//    }
//}
// File: src/game/main/MonsterGame.java
package game.main;

import game.initializer.GameInitializer;
import game.quest.QuestManager;

import java.util.Random;

public class MonsterGame {

    public static void main(String[] args) {
        GameInitializer.registerAll();
        System.out.println("游戏启动中...");
        System.out.println("欢迎来到怪物游戏！");
        Random random = new Random();
        QuestManager questManager = new QuestManager();

        // Initialize and display the GameLoaderGUI
        new GameLoaderGUI(random, questManager);
    }
}