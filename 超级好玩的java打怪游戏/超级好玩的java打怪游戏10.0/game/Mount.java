// Mount.java
package game;

import java.io.Serializable;

public class Mount implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int speed;
    int stamina;
    boolean isEquipped;
    int dodgeBonus;
    int attackBonus; // 新增攻击力加成属性
    int defenseBonus; // 新增防御力加成属性
    int price; // 新增价格属性

    public Mount(String name, int speed, int stamina, int dodgeBonus, int attackBonus, int defenseBonus, int price) {
        this.name = name;
        this.speed = speed;
        this.stamina = stamina;
        this.dodgeBonus = dodgeBonus;
        this.attackBonus = attackBonus; // 初始化攻击力加成
        this.defenseBonus = defenseBonus; // 初始化防御力加成
        this.price = price; // 初始化价格
        this.isEquipped = false;
    }

    public void ride(Player player) {
        if (stamina > 0) {
            System.out.println("你骑乘了 " + name + "。");
            stamina -= 10;
            player.restoreHealth(speed); // 根据速度恢复玩家血量
        } else {
            System.out.println(name + " 太累了，无法继续骑乘。");
        }
    }

    public void rest() {
        stamina += 20;
        System.out.println(name + " 休息了一会儿，恢复了体力。");
    }

    public void displayMountInfo() {
        System.out.println("坐骑名称：" + name);
        System.out.println("速度：" + speed);
        System.out.println("体力：" + stamina);
        System.out.println("躲避值加成：" + dodgeBonus);
        System.out.println("攻击力加成：" + attackBonus); // 显示攻击力加成
        System.out.println("防御力加成：" + defenseBonus); // 显示防御力加成
        System.out.println("价格：" + price); // 显示价格
    }

    public void displayMountAttributeBonuses(Mount mount) {
        System.out.println("坐骑属性提升：");
        if (mount.dodgeBonus > 0) System.out.println("躲避值提升：" + mount.dodgeBonus);
        if (mount.attackBonus > 0) System.out.println("攻击力提升：" + mount.attackBonus); // 显示攻击力提升
        if (mount.defenseBonus > 0) System.out.println("防御力提升：" + mount.defenseBonus); // 显示防御力提升
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}