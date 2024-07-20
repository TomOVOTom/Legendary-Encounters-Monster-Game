package game;

import game.base.Base;
import game.pet.Pet;
import game.pet.PetManager;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int MAX_LEVEL = 10;
    public static int originalAttack;

    public String name;
    public PlayerAttributes attributes;

    public Equipment equippedWeapon;
    public Equipment equippedArmor;
    public Equipment equippedBoots;

    public int comboCounter;
    public boolean isStone = false;
    private boolean canAttack = true;

    public Inventory inventory = new Inventory();
    public List<StatusEffectManager.StatusEffect> statusEffects = new ArrayList<>();
    public Mount equippedMount;

    public List<Pet> pets = new ArrayList<>();
    public Pet equippedPet;

    public Base base = null; // Initialize base to null

    public Player(String name, int hp, int attack, int defense, int intelligence, int stamina, int dodge) {
        this.name = name;
        this.attributes = new PlayerAttributes(hp, attack, defense, intelligence, stamina, dodge);

        inventory.addToInventory(new Item("治疗药水", "healing", 50, 10, 15));
        inventory.addToInventory(new Item("体力药水", "stamina", 20, 5, 1));
        inventory.addToInventory(new Item("防御药水", "defense", 10, 5, 1));
        inventory.addToInventory(new Item("伤害药水", "damage", 30, 15, 1));
        inventory.addToInventory(new Item("强效伤害药水", "damage", 50, 20, 11));
        inventory.addToInventory(new Item("天体爆炸药水", "damage", 100, 50, 11));
    }

    public boolean canAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void addStatusEffect(StatusEffectManager.StatusEffect effect) {
        this.statusEffects.add(effect);
    }

    public void updateStatusEffects() {
        StatusEffectManager.updateStatusEffects(this);
    }

    public Pet getEquippedPet() {
        return PetManager.getEquippedPet(this);
    }

    public void gainExp(int exp) {
        this.attributes.exp += exp;
        int requiredExp = (int) Math.pow(this.attributes.level * 100, 1.2);
        if (this.attributes.exp >= requiredExp && this.attributes.level < MAX_LEVEL) {
            this.attributes.level++;
            this.attributes.exp -= requiredExp;
            this.attributes.skillPoints += 5;
            System.out.println("恭喜你升级了！当前等级：" + this.attributes.level);
        }
    }

    public void attack(Monster monster) {
        new Attack(this).attack(monster);
        if (equippedPet != null) {
            equippedPet.attack(monster);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}