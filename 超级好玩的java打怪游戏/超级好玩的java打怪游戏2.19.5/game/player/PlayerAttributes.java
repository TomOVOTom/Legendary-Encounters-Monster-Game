// File: src/game/player/PlayerAttributes.java
package game.player;

import java.io.Serializable;

public class PlayerAttributes implements Serializable {
    private static final long serialVersionUID = 1L;

    public int hp;
    public int attack;
    public int defense;
    public int intelligence;
    public int stamina;
    public int dodge;
    public int level;
    public int exp;
    public int skillPoints;
    public int gold;
    public int criticalRate; // 暴击率
    public int criticalDamage; // 暴击伤害
    public int hitRate; // 命中率
    public int blockRate; // 格挡率
    public int dodgeRate; // 闪避率

    public int fireMagicLevel;
    public int healMagicLevel;
    public int vampirismLevel;
    public int lightningMagicLevel;
    public int iceMagicLevel;

    public int fireMagicCooldown = 0;
    public int healMagicCooldown = 0;
    public int lightningMagicCooldown = 0;
    public int iceMagicCooldown = 0;
    /**
     * 穿透攻击造成的固定伤害值。此属性在 `applyPiercingAttack` 方法中用于计算穿透攻击的伤害。
     */
    public int piercingAttackDamage;//伤害加深

    /**
     * 攻击者穿透目标防御的百分比。此属性在 `applyArmorPenetration` 方法中用于计算减少的目标防御力。
     */
    public int armorPenetration;//防御穿透

    /**
     * 攻击者对血量低于最大血量 30% 的目标造成的额外伤害百分比。此属性在 `applyDamageIncrease` 方法中用于增加伤害输出。
     */
    public int damageIncrease;

    /**
     * 攻击者触发穿透攻击的概率，以百分比表示。此属性在 `applyPiercingAttack` 方法中用于判断是否触发穿透攻击。
     */
    public int piercingAttackRate;//穿透攻击


    public PlayerAttributes(int hp, int attack, int defense, int intelligence, int stamina, int dodge) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.intelligence = intelligence;
        this.stamina = stamina;
        this.dodge = dodge;
        this.level = 1;
        this.exp = 0;
        this.skillPoints = 15;
        this.gold = 150000;

        this.fireMagicLevel = 0;
        this.healMagicLevel = 0;
        this.vampirismLevel = 0;
        this.lightningMagicLevel = 0;
        this.iceMagicLevel = 0;
        this.criticalRate = 95; // 默认暴击率
        this.criticalDamage = (int) 1.5; // 默认暴击伤害倍率
        this.hitRate = 95; // 默认命中率
        this.blockRate = 90; // 默认格挡率
        this.dodgeRate = 15; // 默认闪避率
        this.armorPenetration=190;

        // 初始化新增属性
        this.damageIncrease = 0; // 假设对低血量目标的额外伤害百分比为15%
        this.piercingAttackRate = 1905; // 假设触发穿透攻击的概率为5%
        this.piercingAttackDamage = 10; // 假设穿透攻击造成的固定伤害为100
    }
}