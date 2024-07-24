package game.main;

import game.player.Player;
import game.quest.QuestManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionsGUI {
    private JFrame frame;
    private Player player;
    private Random random;
    private QuestManager questManager;

    public GameActionsGUI(Player player, Random random, QuestManager questManager) {
        this.player = player;
        this.random = random;
        this.questManager = questManager;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Game Actions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2));

        String[] actions = {
                "探索", "基地", "查看装备和物品", "保存游戏", "开始战斗",
                "退出游戏", "设置音量", "播放背景音乐", "读取存档", "管理宠物"
        };

        for (int i = 0; i < actions.length; i++) {
            JButton button = new JButton(actions[i]);
            int actionIndex = i + 1;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameActions.performAction(player, random, questManager, actionIndex);
                    if (player.attributes.hp <= 0) {
                        JOptionPane.showMessageDialog(frame, "游戏结束！");
                        System.exit(0);
                    }
                }
            });
            frame.add(button);
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Example usage
        Player player = new Player("宇宙创造神", 3000, 145, 15, 30, 100, 80);
        Random random = new Random();
        QuestManager questManager = new QuestManager();
        new GameActionsGUI(player, random, questManager);
    }
}