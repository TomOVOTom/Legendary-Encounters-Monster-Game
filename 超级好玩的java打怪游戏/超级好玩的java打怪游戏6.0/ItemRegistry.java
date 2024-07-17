// ItemRegistry.java
package game;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    // 存储游戏中所有物品的映射，键为物品名称，值为物品对象
    private static Map<String, Item> itemMap = new HashMap<>();
    // 存储游戏中所有装备的映射，键为装备名称，值为装备对象
    private static Map<String, Equipment> equipmentMap = new HashMap<>();

    // 将一个物品对象注册到游戏中，使其可以被获取和使用
    public static void registerItem(Item item) {
        itemMap.put(item.getName(), item);
    }

    // 将一个装备对象注册到游戏中，使其可以被获取和装备
    public static void registerEquipment(Equipment equipment) {
        equipmentMap.put(equipment.getName(), equipment);
    }

    // 根据物品或装备名称获取一个已注册的对象
    public static Item getItemByName(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name);
        } else if (equipmentMap.containsKey(name)) {
            return equipmentMap.get(name);
        }
        return null;
    }

    // 检查一个名称是否已经被注册为物品或装备
    public static boolean isRegistered(String name) {
        return itemMap.containsKey(name) || equipmentMap.containsKey(name);
    }

    // 新方法，用于检索所有物品和装备
    public static Map<String, Item> getAllItems() {
        return itemMap;
    }

    public static Map<String, Equipment> getAllEquipments() {
        return equipmentMap;
    }
}