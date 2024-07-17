package game;

public class Game {
    public static Monster currentMonster; // 假设有一个字段来跟踪战斗中的当前怪物

    public static Monster findCurrentMonster() {
        // 这个方法将返回玩家正在战斗的当前怪物
        return currentMonster;
    }
}