// game/ItemRegistry.java
package game;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    // 存储游戏中所有物品的映射，键为物品名称，值为物品对象
    private static Map<String, Item> itemMap = new HashMap<>();
    // 存储游戏中所有装备的映射，键为装备名称，值为装备对象
    private static Map<String, Equipment> equipmentMap = new HashMap<>();
    // 存储游戏中所有坐骑的映射，键为坐骑名称，值为坐骑对象
    private static Map<String, Mount> mounts = new HashMap<>();

    // 将一个物品对象注册到游戏中，使其可以被获取和使用
    public static void registerItem(Item item) {
        itemMap.put(item.getName(), item);
    }

    // 将一个装备对象注册到游戏中，使其可以被获取和装备
    public static void registerEquipment(Equipment equipment) {
        equipmentMap.put(equipment.getName(), equipment);
    }

    // 将一个坐骑对象注册到游戏中，使其可以被获取和使用
    public static void registerMount(Mount mount) {
        mounts.put(mount.getName(), mount);
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

    // 根据坐骑名称获取一个已注册的坐骑对象
    public static Mount getMountByName(String name) {
        return mounts.get(name);
    }

    // 检查一个名称是否已经被注册为物品、装备或坐骑
    public static boolean isRegistered(String name) {
        return itemMap.containsKey(name) || equipmentMap.containsKey(name) || mounts.containsKey(name);
    }

    // 新方法，用于检索所有物品
    public static Map<String, Item> getAllItems() {
        return itemMap;
    }

    // 新方法，用于检索所有装备
    public static Map<String, Equipment> getAllEquipments() {
        return equipmentMap;
    }

    // 新方法，用于检索所有坐骑
    public static Map<String, Mount> getAllMounts() {
        return mounts;
    }
}