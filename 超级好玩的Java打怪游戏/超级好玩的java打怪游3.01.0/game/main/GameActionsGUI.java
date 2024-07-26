package game.main;

import game.player.Player;
import game.quest.QuestManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Scanner;

public class GameActionsGUI {
    private JFrame frame;
    private Player player;
    private Random random;
    private QuestManager questManager;
    private JLabel instructionLabel;
    private GameActions gameActions;

    public GameActionsGUI(Player player, Random random, QuestManager questManager) {
        this.player = player;
        this.random = random;
        this.questManager = questManager;
        this.gameActions = new GameActions(new Scanner(System.in)); // Initialize GameActions with a Scanner
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Game Actions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(5, 2));
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
                    performAction(actionIndex);
                }
            });
            buttonPanel.add(button);
        }

        instructionLabel = new JLabel("使用键盘输入数字选择选项：1-探索, 2-基地, 3-查看装备和物品, 4-保存游戏, 5-开始战斗, 6-退出游戏, 7-设置音量, 8-播放背景音乐, 9-读取存档, 0-管理宠物");
        frame.add(instructionLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key >= KeyEvent.VK_1 && key <= KeyEvent.VK_9) {
                    int actionIndex = key - KeyEvent.VK_1 + 1;
                    performAction(actionIndex);
                } else if (key == KeyEvent.VK_0) {
                    performAction(10);
                }
            }
        });

        frame.setFocusable(true);
        frame.requestFocusInWindow(); // Ensure the frame has focus to capture key events
        frame.setVisible(true);
    }

    private void performAction(int actionIndex) {
        gameActions.performAction(player, random, questManager, actionIndex);
        if (player.attributes.hp <= 0) {
            JOptionPane.showMessageDialog(frame, "游戏结束！");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        // Example usage
        Player player = new Player("宇宙创造神", 3000, 45, 15, 30, 100, 80);
        Random random = new Random();
        QuestManager questManager = new QuestManager();
        new GameActionsGUI(player, random, questManager);
    }
}