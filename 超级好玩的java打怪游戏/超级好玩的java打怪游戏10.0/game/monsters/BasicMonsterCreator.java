package game.monsters;

import game.Monster;

import java.util.Random;

public class BasicMonsterCreator extends MonsterCreator {
    @Override
    public Monster createMonster(int level) {
        int monsterType = new Random().nextInt(3);
        switch (monsterType) {
            case 0:
                return createSlime(level);
            case 1:
                return createGoblin(level);
            case 2:
                return createWerewolf(level);
            default:
                return null;
        }
    }

    private Monster createSlime(int level) {
        int hp = 50 + (level - 1) * 10;
        int attack = 10 + (level - 1) * 2;
        int defense = 3 + (level - 1);
        int intelligence = 5 + (level - 1);
        int stamina = 15 + (level - 1) * 2;
        int dodge = 5 + (level - 1);
        String name = "史莱姆";
        int gold = 10 + (level - 1) * 5;
        String abilityName = "治疗";
        int abilityCooldown = 5;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private Monster createGoblin(int level) {
        int hp = 70 + (level - 1) * 15;
        int attack = 15 + (level - 1) * 3;
        int defense = 7 + (level - 1) * 2;
        int intelligence = 8 + (level - 1);
        int stamina = 20 + (level - 1) * 3;
        int dodge = 10 + (level - 1) * 2;
        String name = "哥布林";
        int gold = 20 + (level - 1) * 10;
        String abilityName = "狂暴";
        int abilityCooldown = 3;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private Monster createWerewolf(int level) {
        int hp = 100 + (level - 1) * 20;
        int attack = 20 + (level - 1) * 4;
        int defense = 10 + (level - 1) * 3;
        int intelligence = 5 + (level - 1);
        int stamina = 25 + (level - 1) * 4;
        int dodge = 15 + (level - 1) * 3;
        String name = "狼人";
        int gold = 30 + (level - 1) * 15;
        String abilityName = "护盾";
        int abilityCooldown = 5;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }
}