package game.monsters;

import game.monster.Monster;

import java.util.Random;

public class AdvancedMonsterCreator extends MonsterCreator {
    @Override
    public Monster createMonster(int level) {
        int monsterType = new Random().nextInt(3);
        switch (monsterType) {
            case 0:
                return createViper(level);
            case 1:
                return createFlyingMonster(level);
            case 2:
                return createMagicMonster(level);
            default:
                return null;

        }
    }

    private Monster createViper(int level) {
        int hp = 150 + (level - 1) * 30;
        int attack = 20 + (level - 1) * 5;
        int defense = 15 + (level - 1) * 4;
        int intelligence = 10 + (level - 1) * 2;
        int stamina = 30 + (level - 1) * 5;
        int dodge = 50 + (level - 1) * 3;
        String name = "毒蛇";
        int gold = 40 + (level - 1) * 20;
        String abilityName = "毒素";
        int abilityCooldown = 5;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private Monster createFlyingMonster(int level) {
        int hp = 180 + (level - 1) * 36;
        int attack = 18 + (level - 1) * 4;
        int defense = 13 + (level - 1) * 4;
        int intelligence = 12 + (level - 1) * 3;
        int stamina = 22 + (level - 1) * 4;
        int dodge = 68 + (level - 1) * 2;
        String name = "飞行怪物";
        int gold = 75 + (level - 1) * 30;
        String abilityName = "火焰吐息";
        int abilityCooldown = 3;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private Monster createMagicMonster(int level) {
        int hp = 120 + (level - 1) * 24;
        int attack = 25 + (level - 1) * 5;
        int defense = 8 + (level - 1) * 3;
        int intelligence = 15 + (level - 1) * 4;
        int stamina = 28 + (level - 1) * 5;
        int dodge = 42 + (level - 1) * 4;
        String name = "魔法怪物";
        int gold = 35 + (level - 1) * 17;
        String abilityName = "石化";
        int abilityCooldown = 5;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }
}