// GameSaveManager.java
package game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameSaveManager {
    private static String saveDir = "saves/";

    static {
        File dir = new File(saveDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void setSaveDir(String newSaveDir) {
        saveDir = newSaveDir;
        File dir = new File(saveDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void saveGame(Player player, String saveName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveDir + saveName + ".dat"))) {
            oos.writeObject(player);
            System.out.println("游戏进度已保存到 " + saveName + "...");
        } catch (IOException e) {
            System.out.println("保存游戏时出错：" + e.getMessage());
        }
    }

    public static Player loadGame(String saveName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveDir + saveName + ".dat"))) {
            return (Player) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("加载游戏时出错：" + e.getMessage());
            return null;
        }
    }

    public static List<String> listSaves() {
        File dir = new File(saveDir);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".dat"));
        List<String> saveNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                saveNames.add(file.getName().replace(".dat", ""));
            }
        }
        return saveNames;
    }
}