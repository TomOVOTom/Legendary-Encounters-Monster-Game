// Pet.java
package game.pet;

import game.monster.Monster;

import java.util.ArrayList;
import java.util.List;

public class Pet implements java.io.Serializable {
    public String name;
    public int hp;
    public int attack;
    public int defense;
    public int intelligence;
    public int stamina;
    public int dodge;
    public int level;
    public int exp;
    public int combatPower;
    public List<PetSkill> skills;

    public Pet(String name, int hp, int attack, int defense, int intelligence, int stamina, int dodge) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.intelligence = intelligence;
        this.stamina = stamina;
        this.dodge = dodge;
        this.level = 1;
        this.exp = 0;
        this.skills = new ArrayList<>();
        PetUtils.initializeSkills(this);
        PetUtils.calculateCombatPower(this);
    }

    public void attack(Monster monster) {
        PetUtils.attack(this, monster);
    }

    public void gainExp(int exp) {
        PetUtils.gainExp(this, exp);
    }

    public void calculateCombatPower() {
        PetUtils.calculateCombatPower(this);
    }

    public void displayAttributes() {
        PetUtils.displayAttributes(this);
    }
}