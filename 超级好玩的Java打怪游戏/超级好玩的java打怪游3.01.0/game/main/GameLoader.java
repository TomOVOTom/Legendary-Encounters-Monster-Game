package game.main;

import game.player.Player;
import game.quest.QuestManager;

import java.util.Random;

public class GameLoader {
    public static Player loadGame() {
        GameLoaderGUI gameLoaderGUI = new GameLoaderGUI(new Random(),new QuestManager());
        return gameLoaderGUI.getPlayer();

    }
}