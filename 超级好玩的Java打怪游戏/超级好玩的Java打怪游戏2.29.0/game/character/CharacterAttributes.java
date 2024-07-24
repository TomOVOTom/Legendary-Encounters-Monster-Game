package game.character;

import java.io.Serializable;

public class CharacterAttributes implements Serializable {
    private static final long serialVersionUID = 1L;

    public int hp;
    public int attack;
    public int defense;
    public int intelligence;
    public int stamina;
    public int dodge;
    public int criticalRate; // 暴击率
    public int criticalDamage; // 暴击伤害
    public int hitRate; // 命中率
    public int blockRate; // 格挡率
    public int dodgeRate; // 闪避率
    public int armorPenetration; // 防御穿透
    public int damageIncrease; // 额外伤害百分比
    public int piercingAttackRate; // 穿透攻击概率
    public int piercingAttackDamage; // 穿透攻击伤害

    public CharacterAttributes(int hp, int attack, int defense, int intelligence, int stamina, int dodge) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.intelligence = intelligence;
        this.stamina = stamina;
        this.dodge = dodge;

    }
}