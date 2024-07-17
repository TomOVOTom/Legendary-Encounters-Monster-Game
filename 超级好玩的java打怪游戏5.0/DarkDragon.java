package game;

public class DarkDragon extends Monster {
    public DarkDragon(int level) {
        super(level, 200 + level * 40, // 生命值hp
                30 + level * 6,   // 攻击力
                20 + level * 4,   // 防御力
                25 + level * 5,   // 智力
                35 + level * 5,   // 体力
                30 + level * 3,   // 闪避
                "黑暗巨龙",        // 名称
                100 + level * 50, // 金币奖励
                "暗影爆发",        // 技能名称
                4);                // 技能冷却时间
    }

}