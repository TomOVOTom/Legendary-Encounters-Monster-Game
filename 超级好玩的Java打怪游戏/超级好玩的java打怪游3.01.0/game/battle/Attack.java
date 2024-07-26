package game.battle;

import game.monster.Monster;
import game.pet.Pet;
import game.player.Player;

import java.util.Random;

public class Attack {
    public Player player;
    public Monster monster;
    public Pet pet;
//    public AttackHandlerGUI attackHandlerGUI = new AttackHandlerGUI(player, monster, pet);

    public game.battle.BattleManagerGUI BattleManagerGUI;
    BattleManagerGUI battleManagerGUI=new BattleManagerGUI(player, new Random());
    public AttackHandler attackHandler = new AttackHandler(battleManagerGUI);

    public Attack(Player player) {
        this.player = player;
    }

    public Attack(Monster monster, Player player) {
        this.monster = monster;
        this.player = player;
    }

    public void attack(Monster monster) {
        attackHandler.attack(player, monster);
    }

    public void attack(Player player) {
        attackHandler.attack(monster, player);
    }

    public void attackRandomTarget() {
        if (player.getEquippedPet() != null && new Random().nextBoolean()) {
            attack(player.getEquippedPet());
        } else {
            attack(player);
        }
    }

    public void attack(Pet pet) {
        attackHandler.attack(monster, pet);
    }
}