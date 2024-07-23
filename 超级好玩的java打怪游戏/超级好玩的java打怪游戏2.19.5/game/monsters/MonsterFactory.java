package game.monsters;

import game.monster.Monster;

import java.util.Random;

public class MonsterFactory {
    public static final MonsterCreator[] creators = {
            new BasicMonsterCreator(),
            new AdvancedMonsterCreator(),
            new EliteMonsterCreator(),
            new DarkDragonCreator()
    };

    public static Monster createMonster(Random random) {
        int creatorIndex = random.nextInt(creators.length);
        int monsterLevel = random.nextInt(5) + 1;
        return creators[creatorIndex].createMonster(monsterLevel);
    }
}