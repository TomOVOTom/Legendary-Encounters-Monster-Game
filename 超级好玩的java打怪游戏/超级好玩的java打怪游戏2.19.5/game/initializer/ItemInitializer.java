package game.initializer;

import game.item.Item;
import game.item.ItemRegistry;
import game.player.Player;

public class ItemInitializer {
    public static void registerAllItems() {
        ItemRegistry.registerItem(new Item("治疗药水", "healing", 50, 10, 1));
        ItemRegistry.registerItem(new Item("攻击药水", "attack", 15, 20, 1));
        ItemRegistry.registerItem(new Item("防御药水", "defense", 10, 15, 1));
        ItemRegistry.registerItem(new Item("体力药水", "stamina", 20, 15, 1));
        ItemRegistry.registerItem(new Item("闪避药水", "dodge", 5, 10, 1));
        ItemRegistry.registerItem(new Item("智力药水", "intelligence", 5, 10, 1));
        ItemRegistry.registerItem(new Item("伤害药水", "damage", 60, 15, 1));
        ItemRegistry.registerItem(new Item("强效伤害药水", "damage", 160, 45, 1));
        ItemRegistry.registerItem(new Item("天体爆炸药水", "damage", 300, 60, 1));
        // 新增道具注册
        ItemRegistry.registerItem(new Item("暴击药水", "criticalRate", 5, 100, 1));
        ItemRegistry.registerItem(new Item("暴击伤害药水", "criticalDamage", 10, 150, 1));
        ItemRegistry.registerItem(new Item("命中药水", "hitRate", 5, 80, 1));
        ItemRegistry.registerItem(new Item("格挡药水", "blockRate", 5, 80, 1));
        ItemRegistry.registerItem(new Item("闪避药水", "dodgeRate", 5, 80, 1));
        ItemRegistry.registerItem(new Item("火魔法书", "fireMagicLevel", 1, 200, 1));
        ItemRegistry.registerItem(new Item("治疗魔法书", "healMagicLevel", 1, 200, 1));
        ItemRegistry.registerItem(new Item("吸血魔法书", "vampirismLevel", 1, 200, 1));
        ItemRegistry.registerItem(new Item("穿透药水", "piercingAttackDamage", 10, 120, 1));
        ItemRegistry.registerItem(new Item("防御穿透药水", "armorPenetration", 5, 120, 1));
        ItemRegistry.registerItem(new Item("额外伤害药水", "damageIncrease", 5, 150, 1));

        // 新增强化版道具
        ItemRegistry.registerItem(new Item("超级治疗药水", "healing", 100, 20, 1));
        ItemRegistry.registerItem(new Item("超级攻击药水", "attack", 30, 40, 1));
        ItemRegistry.registerItem(new Item("超级防御药水", "defense", 20, 30, 1));
        ItemRegistry.registerItem(new Item("超级体力药水", "stamina", 40, 30, 1));
        ItemRegistry.registerItem(new Item("超级闪避药水", "dodge", 10, 20, 1));
        ItemRegistry.registerItem(new Item("超级智力药水", "intelligence", 10, 20, 1));
        ItemRegistry.registerItem(new Item("超级伤害药水", "damage", 120, 30, 1));
        ItemRegistry.registerItem(new Item("终极伤害药水", "damage", 500, 100, 1));

        // 新增创意道具
        ItemRegistry.registerItem(new Item("雷电魔法书", "lightningMagicLevel", 1, 250, 1));
        ItemRegistry.registerItem(new Item("冰霜魔法书", "iceMagicLevel", 1, 250, 1));
        ItemRegistry.registerItem(new Item("神秘药水", "randomEffect", 0, 200, 1)); // 随机效果药水
        ItemRegistry.registerItem(new Item("复活药水", "revive", 0, 5000, 1)); // 复活药水
    }

    public static void initializePlayerInventory(Player player) {
        player.getInventory().addToInventory(new Item("治疗药水", "healing", 50, 10, 1), 1);
        player.getInventory().addToInventory(new Item("体力药水", "stamina", 20, 15, 1), 1);
        player.getInventory().addToInventory(new Item("防御药水", "defense", 10, 15, 1), 1);
        player.getInventory().addToInventory(new Item("伤害药水", "damage", 60, 15, 1), 1);
        player.getInventory().addToInventory(new Item("强效伤害药水", "damage", 160, 45, 1), 1);
        player.getInventory().addToInventory(new Item("天体爆炸药水", "damage", 300, 60, 1), 111);
        // 新增道具初始化
        player.getInventory().addToInventory(new Item("超级治疗药水", "healing", 100, 20, 1), 1);
        player.getInventory().addToInventory(new Item("超级攻击药水", "attack", 30, 40, 1), 1);
        player.getInventory().addToInventory(new Item("超级防御药水", "defense", 20, 30, 1), 1);
        player.getInventory().addToInventory(new Item("超级体力药水", "stamina", 40, 30, 1), 1);
        player.getInventory().addToInventory(new Item("超级闪避药水", "dodge", 10, 20, 1), 1);
        player.getInventory().addToInventory(new Item("超级智力药水", "intelligence", 10, 20, 1), 1);
        player.getInventory().addToInventory(new Item("超级伤害药水", "damage", 120, 30, 1), 1);
        player.getInventory().addToInventory(new Item("终极伤害药水", "damage", 500, 100, 1), 111);
        player.getInventory().addToInventory(new Item("雷电魔法书", "lightningMagicLevel", 1, 250, 1), 1);
        player.getInventory().addToInventory(new Item("冰霜魔法书", "iceMagicLevel", 1, 250, 1), 100);
        player.getInventory().addToInventory(new Item("神秘药水", "randomEffect", 0, 200, 1), 1);
        player.getInventory().addToInventory(new Item("复活药水", "revive", 0, 5000, 1), 1);
    }
}