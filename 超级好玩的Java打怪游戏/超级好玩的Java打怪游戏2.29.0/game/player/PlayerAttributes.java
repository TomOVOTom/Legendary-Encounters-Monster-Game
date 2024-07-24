// File: src/game/player/PlayerAttributes.java
package game.player;

import game.character.CharacterAttributes;

public class PlayerAttributes extends CharacterAttributes {
    private static final long serialVersionUID = 1L;

    public int level;
    public int exp;
    public int skillPoints;
    public int gold;

    public int fireMagicLevel;
    public int healMagicLevel;
    public int vampirismLevel;
    public int lightningMagicLevel;
    public int iceMagicLevel;

    public int fireMagicCooldown = 0;
    public int healMagicCooldown = 0;
    public int lightningMagicCooldown = 0;
    public int iceMagicCooldown = 0;

    public PlayerAttributes(int hp, int attack, int defense, int intelligence, int stamina, int dodge) {
        super(hp, attack, defense, intelligence, stamina, dodge);
        this.level = 1;
        this.exp = 0;
        this.skillPoints = 15;
        this.gold = 150000;

        this.fireMagicLevel = 0;
        this.healMagicLevel = 0;
        this.vampirismLevel = 0;
        this.lightningMagicLevel = 0;
        this.iceMagicLevel = 0;
        this.criticalRate = 60; // 默认暴击率
        this.criticalDamage =  2; // 默认暴击伤害倍率
        this.hitRate = 95; // 默认命中率
        this.blockRate = 90; // 默认格挡率
        this.dodgeRate = 15; // 默认闪避率
        this.armorPenetration=190;

        // 初始化新增属性
        this.damageIncrease = 0; // 假设对低血量目标的额外伤害百分比为15%
        this.piercingAttackRate = 80; // 假设触发穿透攻击的概率为5%
        this.piercingAttackDamage = 10; // 假设穿透攻击造成的固定伤害为100
    }
}