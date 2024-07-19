package game.monsters;

import game.Monster;

public abstract class MonsterCreator {
    public abstract Monster createMonster(int level);
}