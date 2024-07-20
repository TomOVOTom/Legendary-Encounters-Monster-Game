package game;

import game.monsters.BasicMonsterCreator;
import game.monsters.AdvancedMonsterCreator;
import game.monsters.EliteMonsterCreator;
import game.monsters.DarkDragonCreator;
import game.monsters.MonsterCreator;

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
