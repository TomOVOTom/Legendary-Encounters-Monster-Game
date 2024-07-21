// game/equipment/Equipment.java
package game.equipment;

import game.item.Item;

public class Equipment extends Item {
    public int attackBonus; // 攻击力加成
    public int defenseBonus; // ��御力加成
    public int hpBonus; // 生命值加��
    public int intelligenceBonus; // 智力加成
    public int staminaBonus; // 体力加成
    public int dodgeBonus; // 闪避加成

    public Equipment(String name, String type, int attackBonus, int defenseBonus, int hpBonus, int intelligenceBonus, int staminaBonus, int dodgeBonus, int price, int quantity) {
        super(name, type, 0, price, quantity); // 调用父类构造函数
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.hpBonus = hpBonus;
        this.intelligenceBonus = intelligenceBonus;
        this.staminaBonus = staminaBonus;
        this.dodgeBonus = dodgeBonus;
    }
}