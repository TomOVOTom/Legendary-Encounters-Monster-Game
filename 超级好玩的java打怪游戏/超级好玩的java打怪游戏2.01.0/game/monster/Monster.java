// File: src/game/monster/Monster.java
package game.monster;

import game.abilities.AbilityManager;
import game.battle.Attack;
import game.player.Player;
import game.status.StatusEffectManager;

import java.util.ArrayList;
import java.util.List;

public class Monster {
    public int level = 1;
    public int hp;
    public int attack;
    public int defense; // 防御力
    public int intelligence; // 智力

    public int stamina; // 体力
    public int dodge; // 躲避
    public String name;
    public int gold; // 金币
    public String abilityName; // 怪物技能名称
    public int abilityCooldown; // 技能冷却回合数
    public int initialAbilityCooldown; // 技能初始冷却回合数
    public int maxHp; // 最大生命值
    List<StatusEffectManager.StatusEffect> statusEffects = new ArrayList<>(); // 状态效果列表

    public Monster(int level, int hp, int attack, int defense, int intelligence, int stamina, int dodge, String name, int gold, String abilityName, int abilityCooldown) {
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.intelligence = intelligence;
        this.stamina = stamina;
        this.dodge = dodge;
        this.name = name;
        this.gold = gold;
        this.abilityName = abilityName;
        this.abilityCooldown = abilityCooldown;
        this.initialAbilityCooldown = abilityCooldown; // 初始化初始冷却时间
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0; // 确保生命值不会低于0
    }

    public void addStatusEffect(StatusEffectManager.StatusEffect effect) {
        statusEffects.add(effect);
    }

    public void useAbility(Player player) {
        AbilityManager.useAbility(this, player);
    }

    public void attack(Player player) {
        new Attack(this, player).attack(player);
    }
}