// File: src/game/battle/BattleManagerGUI.java
package game.battle;

import game.audio.AudioPlayer;
import game.audio.VolumeController;
import game.monster.Monster;
import game.pet.Pet;
import game.player.Player;
import game.player.skill.MagicCooldownManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import static game.inventory.InventoryManager.useItem;
import static game.player.PlayerStatus.displayPlayerStatus;
import static game.player.skill.SkillManager.useSkill;
import static game.shop.Shop.enterShop;

public class BattleManagerGUI {
    public JFrame frame;
    public JTextArea battleLog;
    public Player player;
    public Monster monster;
    public Random random;
    public Pet pet;
    public AttackHandler attackHandler;

    public BattleManagerGUI(Player player, Random random) {
        this.player = player;
        this.random = random;
        this.attackHandler = new AttackHandler(this); // Initialize AttackHandler with this GUI
        initialize();
    }

    public void initialize() {
        frame = new JFrame("Battle Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        battleLog = new JTextArea();
        battleLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(battleLog);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(2, 4));

        String[] actions = {"攻击", "使用技能", "使用道具", "查看状态", "商店", "学习技能", "设置音量", "管理坐骑"};
        for (String action : actions) {
            JButton button = new JButton(action);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleAction(action);
                }
            });
            actionPanel.add(button);
        }

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(actionPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void handleAction(String action) {
        switch (action) {
            case "攻击":
                if (player.canAttack()) {
                    AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\快速攻击音效.wav", false, false, -5);
                    attackHandler.attack(player, monster);
                } else {
                    log("你受到状态效果的影响，无法在这一回合攻击！");
                }
                break;
            case "使用技能":
                useSkill(player, monster, new Scanner(System.in)); // Replace Scanner with appropriate GUI input
                break;
            case "使用道具":
                useItem(player, new Scanner(System.in)); // Replace Scanner with appropriate GUI input
                break;
            case "查看状态":
                displayPlayerStatus(player, monster);
                break;
            case "商店":
                enterShop(player, new Scanner(System.in)); // Replace Scanner with appropriate GUI input
                break;
            case "学习技能":
                AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\魔法钢琴旋律循环.wav", true, true, -10);
                log("请选择要学习的技能：1. 火焰魔法 2. 治疗魔法 3. 吸血术 4. 闪电魔法 5. 冰冻魔法");
                // Implement skill learning logic with GUI input
                AudioPlayer.stopEffect();
                AudioPlayer.playSound("Music/刀酱-5_20AM.WAV", true, true, -15);
                break;
            case "设置音量":
                if (!VolumeController.setVolume(new Scanner(System.in))) { // Replace Scanner with appropriate GUI input
                    return;
                }
                break;
            case "管理坐骑":
                log("请选择你的坐骑操作：1. 装备坐骑 2. 卸下坐骑 3. 骑乘坐骑 4. 休息坐骑 5. 查看坐骑信息");
                // Implement mount management logic with GUI input
                break;
            default:
                log("无效的选项！");
        }
        MagicCooldownManager.reduceMagicCooldowns(player);

        if (monster.attributes.hp > 0) {
            AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\刀刺音效.wav", false, false, -10);
            attackHandler.attack(monster, player);
            monster.useAbility(player);
            player.updateStatusEffects();
        }
        updateGUI();
    }

    public void log(String message) {
        battleLog.append(message + "\n");
    }

    public int getUserChoice(int min, int max) {
        String input = JOptionPane.showInputDialog(frame, "请输入你的选择 (" + min + " - " + max + "):");
        try {
            int choice = Integer.parseInt(input);
            if (choice >= min && choice <= max) {
                return choice;
            } else {
                log("无效的选择，请输入一个在 " + min + " 和 " + max + " 之间的数字。");
                return getUserChoice(min, max);
            }
        } catch (NumberFormatException e) {
            log("无效的输入，请输入一个数字。");
            return getUserChoice(min, max);
        }
    }

    public void updateGUI() {
        frame.revalidate();
        frame.repaint();
    }

    public void closeWindow() {
        frame.dispose();
    }

    public void returnToMainMenu() {
        log("战斗结束，返回主菜单...");
        frame.dispose();
    }
}