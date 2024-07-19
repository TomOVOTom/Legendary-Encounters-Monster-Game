package game;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    public static int originalAttack;
    int hp;
    int attack;
    int defense;
    int intelligence;
    int stamina;
    int dodge;
    int level;
    int exp;
    int skillPoints;
    public int gold;

    String name;

    int fireMagicLevel;
    int healMagicLevel;
    int vampirismLevel;
    int lightningMagicLevel;
    int iceMagicLevel;

    int fireMagicCooldown = 0;
    int healMagicCooldown = 0;
    int lightningMagicCooldown = 0;
    int iceMagicCooldown = 0;

    Equipment equippedWeapon;
    Equipment equippedArmor;
    Equipment equippedBoots;

    int comboCounter;
    boolean isStone = false;
    private boolean canAttack = true;

    Inventory inventory = new Inventory();
    List<StatusEffectManager.StatusEffect> statusEffects = new ArrayList<>();
    Mount equippedMount;

    List<Pet> pets = new ArrayList<>();
    Pet equippedPet;

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

    public void rest() {
        int restoredHp = 50;
        this.hp += restoredHp;
        System.out.println("你选择休息，恢复了 " + restoredHp + " 点生命值！");

        // Restore health for all pets
        int petRestoredHp = 30; // Amount of health to restore to each pet
        for (Pet pet : pets) {
            if (pet.hp > 0) {
                pet.hp += petRestoredHp;
                System.out.println("你的宠物 " + pet.name + " 恢复了 " + petRestoredHp + " 点生命值！");
            }
        }
    }

    public Pet getEquippedPet() {
        return Pet.getEquippedPet(this);
    }
    public boolean ownsMount(Mount mount) {
        return inventory.getInventory().values().contains(mount);
    }

    public void equipMount(Mount mount) {
        if (equippedMount != null) {
            System.out.println("你已经装备了 " + equippedMount.name + "，先卸下它！");
            unequipMount();
        }
        this.equippedMount = mount;
        System.out.println("你装备了 " + mount.name + "！");
        applyMountBonuses(mount);
        Game.horse.displayMountAttributeBonuses(mount);
    }

    public void unequipMount() {
        if (equippedMount != null) {
            System.out.println("你卸下了 " + equippedMount.name + "！");
            removeMountBonuses(equippedMount);
            equippedMount = null;
        } else {
            System.out.println("你没有装备任何坐骑！");
        }
    }

    public Mount getEquippedMount() {
        return this.equippedMount;
    }

    private void applyMountBonuses(Mount mount) {
        this.dodge += mount.dodgeBonus;
        this.attack += mount.attackBonus;
        this.defense += mount.defenseBonus;
    }

    private void removeMountBonuses(Mount mount) {
        this.dodge -= mount.dodgeBonus;
        this.attack -= mount.attackBonus;
        this.defense -= mount.defenseBonus;
    }

    public void restoreHealth(int speed) {
        int restoredHp = speed;
        this.hp += restoredHp;
        System.out.println("骑乘坐骑恢复了 " + restoredHp + " 点生命值！");
    }

    Player(String name, int hp, int attack, int defense, int intelligence, int stamina, int dodge) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.intelligence = intelligence;
        this.stamina = stamina;
        this.dodge = dodge;
        this.level = 1;
        this.exp = 0;
        this.skillPoints = 15;
        this.gold = 150000;

        this.fireMagicLevel = 0;
        this.healMagicLevel = 0;
        this.vampirismLevel = 0;
        this.lightningMagicLevel = 0;
        this.iceMagicLevel = 0;

        final int MAX_LEVEL = 10;

        inventory.addToInventory(new Item("治疗药水", "healing", 50, 10, 15));
        inventory.addToInventory(new Item("体力药水", "stamina", 20, 5, 1));
        inventory.addToInventory(new Item("防御药水", "defense", 10, 5, 1));
        inventory.addToInventory(new Item("伤害药水", "damage", 30, 15, 1));
        inventory.addToInventory(new Item("强效伤害药水", "damage", 50, 20, 11));
        inventory.addToInventory(new Item("天体爆炸药水", "damage", 100, 50, 11));
    }

    public static final int MAX_LEVEL = 10;

    public void gainExp(int exp) {
        this.exp += exp;
        int requiredExp = (int) Math.pow(this.level * 100, 1.2);
        if (this.exp >= requiredExp && this.level < MAX_LEVEL) {
            this.level++;
            this.exp -= requiredExp;
            this.skillPoints += 5;
            System.out.println("恭喜你升级了！当前等级：" + this.level);
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