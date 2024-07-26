// File: src/game/battle/AttackHandlerGUI.java
package game.battle;

import game.monster.Monster;
import game.pet.Pet;
import game.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AttackHandlerGUI {
    public JFrame frame;
    public static JTextArea logArea;
    public Player player;
    public Monster monster;
    public Pet pet;
    public AttackHandler attackHandler;

    public AttackHandlerGUI(Player player, Monster monster, Pet pet) {
        this.player = player;
        this.monster = monster;
        this.pet = pet;
        Random random = new Random();
        BattleManagerGUI battleManagerGUI = new BattleManagerGUI(player, random);
        this.attackHandler = new AttackHandler(battleManagerGUI);
        initialize();
    }

    public void initialize() {
        frame = new JFrame("Attack Handler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        frame.setVisible(true);
    }

    public static void log(String message) {
        logArea.append(message + "\n");
    }

    public void updateLog() {
        // This method should update the log area with the latest attack results.
        // For simplicity, we assume the AttackHandler class prints results to the console.
        // You can redirect these prints to the logArea if needed.
    }

    public void main(String[] args) {
        // Example usage
//        Monster monster = new Monster();
//        Pet pet = new Pet();
//        new AttackHandlerGUI(player, monster, pet);
    }
}