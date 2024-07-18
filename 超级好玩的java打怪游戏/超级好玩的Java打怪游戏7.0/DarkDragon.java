// DarkDragon.java
package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DarkDragon extends Monster {
    private List<String> abilities = Arrays.asList("暗影爆发", "狂暴", "冰封禁锢");

    public DarkDragon(int level) {
        super(level, 200 + level * 40, // 生命值hp
                30 + level * 6,   // 攻击力
                20 + level * 4,   // 防御力
                25 + level * 5,   // 智力
                35 + level * 5,   // 体力
                30 + level * 3,   // 闪避
                "黑暗巨龙",        // 名称
                100 + level * 50, // 金币奖励
                "暗影爆发",        // 初始技能名称
                4);                // 技能冷却时间
    }

    @Override
    public void useAbility(Player player) {
        Random random = new Random();
        this.abilityName = abilities.get(random.nextInt(abilities.size())); // 随机选择一个技能
        super.useAbility(player);
    }
}