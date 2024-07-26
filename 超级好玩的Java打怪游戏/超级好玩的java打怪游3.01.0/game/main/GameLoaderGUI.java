package game.main;

import game.player.Player;
import game.quest.QuestManager;
import game.save.GameSaveManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class GameLoaderGUI {
    private JFrame frame;
    private Player player;
    private Random random;
    private QuestManager questManager;

    public GameLoaderGUI(Random random, QuestManager questManager) {
        this.random = random;
        this.questManager = questManager;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Game Loader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 1));

        JButton newGameButton = new JButton("新游戏");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player = new Player("宇宙创造神", 30000, 55, 15, 30, 100, 80);
                frame.dispose();
                new GameActionsGUI(player, random, questManager);
            }
        });

        JButton loadGameButton = new JButton("读取存档");
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> saves = GameSaveManager.listSaves();
                if (saves.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "没有可用的存档。");
                    player = new Player("宇宙创造神", 300, 45, 15, 30, 100, 80);
                } else {
                    String[] saveArray = saves.toArray(new String[0]);
                    String saveChoice = (String) JOptionPane.showInputDialog(frame, "请选择一个存档：", "读取存档", JOptionPane.QUESTION_MESSAGE, null, saveArray, saveArray[0]);
                    if (saveChoice != null) {
                        player = GameSaveManager.loadGame(saveChoice);
                        frame.dispose();
                        new GameActionsGUI(player, random, questManager);
                    }
                }
            }
        });

        JButton deleteSaveButton = new JButton("删除存档");
        deleteSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> saves = GameSaveManager.listSaves();
                if (saves.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "没有可用的存档。");
                } else {
                    String[] saveArray = saves.toArray(new String[0]);
                    String saveChoice = (String) JOptionPane.showInputDialog(frame, "请选择要删除的存档（输入0退出）：", "删除存档", JOptionPane.QUESTION_MESSAGE, null, saveArray, saveArray[0]);
                    if (saveChoice != null) {
                        GameSaveManager.deleteSave(saveChoice);
                    }
                }
            }
        });

        frame.add(newGameButton);
        frame.add(loadGameButton);
        frame.add(deleteSaveButton);

        frame.setVisible(true);
    }

    public Player getPlayer() {
        return player;
    }

    public static void main(String[] args) {
        Random random = new Random();
        QuestManager questManager = new QuestManager();
        new GameLoaderGUI(random, questManager);
    }
}