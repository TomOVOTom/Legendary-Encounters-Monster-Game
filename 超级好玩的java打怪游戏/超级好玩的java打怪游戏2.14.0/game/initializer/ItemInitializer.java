// File: src/game/initializer/ItemInitializer.java
package game.initializer;

import game.item.Item;
import game.item.ItemRegistry;

public class ItemInitializer {
    public static void registerAllItems() {
        Item healingItem = new Item("治疗药水", "healing", 50, 10, 1);
        Item attackItem = new Item("攻击药水", "attack", 15, 20, 1);
        Item defenseItem = new Item("防御药水", "defense", 10, 15, 1);
        Item staminaItem = new Item("体力药水", "stamina", 20, 15, 1);
        Item dodgeItem = new Item("闪避药水", "dodge", 5, 10, 1);
        Item intelligenceItem = new Item("智力药水", "intelligence", 5, 10, 1);
        Item damageItem = new Item("伤害药水", "damage", 60, 15, 1);
        Item strongDamageItem = new Item("强效伤害药水", "damage", 160, 45, 1);
        Item celestialBlastPotion = new Item("天体爆炸药水", "damage", 300, 60, 1);
        Item invisibilityPotion = new Item("隐身药水", "invisibility", 0, 50, 1);
        ItemRegistry.registerItem(healingItem);
        ItemRegistry.registerItem(attackItem);
        ItemRegistry.registerItem(defenseItem);
        ItemRegistry.registerItem(staminaItem);
        ItemRegistry.registerItem(dodgeItem);
        ItemRegistry.registerItem(intelligenceItem);
        ItemRegistry.registerItem(damageItem);
        ItemRegistry.registerItem(strongDamageItem);
        ItemRegistry.registerItem(celestialBlastPotion);
        ItemRegistry.registerItem(invisibilityPotion);
    }
}
