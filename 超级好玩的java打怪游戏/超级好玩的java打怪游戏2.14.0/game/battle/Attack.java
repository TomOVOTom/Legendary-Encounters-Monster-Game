package game.battle;

import game.monster.Monster;
import game.pet.Pet;
import game.player.Player;

import java.util.Random;

public class Attack {
    private Player player;
    public Monster monster;
    private AttackHandler attackHandler = new AttackHandler();

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