package game.monsters;

import game.monster.Monster;

public class DarkDragonCreator extends MonsterCreator {
    @Override
    public Monster createMonster(int level) {
        return new DarkDragon(level);
    }
}