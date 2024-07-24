package game.monster;

import game.character.CharacterAttributes;

public class MonsterAttributes extends CharacterAttributes {
    private static final long serialVersionUID = 1L;

    public int level;
    public String name;
    public int gold;
    public String abilityName;
    public int abilityCooldown;
    public int initialAbilityCooldown;
    public int maxHp;

    public MonsterAttributes(int level, int hp, int attack, int defense, int intelligence, int stamina, int dodge, String name, int gold, String abilityName, int abilityCooldown) {
        super(hp, attack, defense, intelligence, stamina, dodge);
        this.level = level;
        this.name = name;
        this.gold = gold;
        this.abilityName = abilityName;
        this.abilityCooldown = abilityCooldown;
        this.initialAbilityCooldown = abilityCooldown; // 初始化初始冷却时间
        this.maxHp = hp;


        this.criticalRate=40;
        this.criticalDamage=3;
    }
}