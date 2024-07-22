// File: src/game/item/Item.java
package game.item;

import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    public String name;
    public String type; // "治疗", "攻击", "防御", "体力", "闪避", "智力"
    public int effect; // 效果值（治疗量、攻击加成等）
    public int price;
    public int quantity; // 数量

    public Item(String name, String type, int effect, int price, int quantity) {
        this.name = name;
        this.type = type;
        this.effect = effect;
        this.price = price;
        this.quantity = quantity; // 使用传入的数量
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getEffect() {
        return effect;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }
}