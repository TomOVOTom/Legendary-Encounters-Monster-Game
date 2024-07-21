// File: src/game/player/PlayerAttributes.java
package game.player;

import java.io.Serializable;

public class PlayerAttributes implements Serializable {
    private static final long serialVersionUID = 1L;

    public int hp;
    public int attack;
    public int defense;
    public int intelligence;
    public int stamina;
    public int dodge;
    public int level;
    public int exp;
    public int skillPoints;
    public int gold;

    public int fireMagicLevel;
    public int healMagicLevel;
    public int vampirismLevel;
    public int lightningMagicLevel;
    public int iceMagicLevel;

    public int fireMagicCooldown = 0;
    public int healMagicCooldown = 0;
    public int lightningMagicCooldown = 0;
    public int iceMagicCooldown = 0;

    public PlayerAttributes(int hp, int attack, int defense, int intelligence, int stamina, int dodge) {
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
    }
}