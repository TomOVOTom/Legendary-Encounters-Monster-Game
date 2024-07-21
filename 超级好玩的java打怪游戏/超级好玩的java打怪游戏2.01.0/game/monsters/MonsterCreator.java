package game.monsters;

import game.monster.Monster;

public abstract class MonsterCreator {
    public abstract Monster createMonster(int level);
}