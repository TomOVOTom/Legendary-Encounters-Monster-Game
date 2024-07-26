package game.monster;

import game.abilities.AbilityManager;
import game.player.Player;
import game.status.StatusEffectManager;

import java.util.ArrayList;
import java.util.List;

public class Monster {
    public MonsterAttributes attributes;
    List<StatusEffectManager.StatusEffect> statusEffects = new ArrayList<>(); // 状态效果列表

    public Monster(int level, int hp, int attack, int defense, int intelligence, int stamina, int dodge, String name, int gold, String abilityName, int abilityCooldown) {
        this.attributes = new MonsterAttributes(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }


    public void addStatusEffect(StatusEffectManager.StatusEffect effect) {
        statusEffects.add(effect);
    }

    public void useAbility(Player player) {
        AbilityManager.useAbility(this, player);
    }

    public double calculateCombatPower() {
        return attributes.hp * 0.2 + attributes.attack * 0.3 + attributes.defense * 0.2 + attributes.intelligence * 0.1 + attributes.stamina * 0.1 + attributes.dodge * 0.3;
    }
}