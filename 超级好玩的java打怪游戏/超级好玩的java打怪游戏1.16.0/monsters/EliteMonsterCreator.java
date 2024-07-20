package game.monsters;

import game.Monster;

import java.util.Random;

public class EliteMonsterCreator extends MonsterCreator {
    @Override
    public Monster createMonster(int level) {
        int monsterType = new Random().nextInt(6);
        switch (monsterType) {
            case 0:
                return createFlameGiant(level);
            case 1:
                return createFrostMage(level);
            case 2:
                return createThunderBeast(level);
            case 3:
                return createEarthGolem(level);
            case 4:
                return createWindSpirit(level);
            case 5:
                return createWaterNymph(level);
            default:
                return null;
        }
    }

    private Monster createFlameGiant(int level) {
        int hp = 150 + level * 30;
        int attack = 25 + level * 5;
        int defense = 15 + level * 3;
        int intelligence = 10 + level * 2;
        int stamina = 20 + level * 4;
        int dodge = 10 + level * 2;
        String name = "火焰巨人";
        int gold = 45 + level * 20;
        String abilityName = "熔岩爆发";
        int abilityCooldown = 4;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private Monster createFrostMage(int level) {
        int hp = 120 + level * 25;
        int attack = 15 + level * 3;
        int defense = 12 + level * 2;
        int intelligence = 20 + level * 5;
        int stamina = 30 + level * 3;
        int dodge = 20 + level * 2;
        String name = "霜寒巫师";
        int gold = 50 + level * 25;
        String abilityName = "冰封禁锢";
        int abilityCooldown = 5;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private Monster createThunderBeast(int level) {
        int hp = 130 + level * 28;
        int attack = 22 + level * 4;
        int defense = 14 + level * 3;
        int intelligence = 18 + level * 3;
        int stamina = 25 + level * 4;
        int dodge = 15 + level * 2;
        String name = "雷兽";
        int gold = 60 + level * 20;
        String abilityName = "雷霆一击";
        int abilityCooldown = 4;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private static Monster createEarthGolem(int level) {
        int hp = 200 + level * 40;
        int attack = 18 + level * 3;
        int defense = 20 + level * 4;
        int intelligence = 10 + level * 2;
        int stamina = 30 + level * 5;
        int dodge = 5 + level * 1;
        String name = "地元素";
        int gold = 70 + level * 25;
        String abilityName = "地震";
        int abilityCooldown = 5;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private static Monster createWindSpirit(int level) {
        int hp = 100 + level * 20;
        int attack = 15 + level * 3;
        int defense = 10 + level * 2;
        int intelligence = 20 + level * 4;
        int stamina = 25 + level * 3;
        int dodge = 30 + level * 5;
        String name = "风灵";
        int gold = 40 + level * 20;
        String abilityName = "风刃";
        int abilityCooldown = 3;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private static Monster createWaterNymph(int level) {
        int hp = 120 + level * 25;
        int attack = 18 + level * 4;
        int defense = 12 + level * 2;
        int intelligence = 22 + level * 5;
        int stamina = 28 + level * 4;
        int dodge = 20 + level * 3;
        String name = "水仙";
        int gold = 50 + level * 25;
        String abilityName = "水疗";
        int abilityCooldown = 4;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

}
