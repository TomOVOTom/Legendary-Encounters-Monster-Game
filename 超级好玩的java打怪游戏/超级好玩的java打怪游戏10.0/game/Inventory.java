// Inventory.java
package game;

import java.util.HashMap;
import java.util.Map;

public class Inventory implements java.io.Serializable {
    private Map<String, Object> inventory = new HashMap<>();

    // 修改方法以向背包中添加物品
    public void addToInventory(Item item) {
        if (inventory.containsKey(item.getName())) {
            ((Item) inventory.get(item.getName())).increaseQuantity(item.getQuantity());
        } else {
            inventory.put(item.getName(), item);
        }
    }

    public void addToInventory(Equipment equipment) {
        inventory.put(equipment.name, equipment);
    }

    public void addToInventory(Mount mount) {
        inventory.put(mount.name, mount);
    }

    // 修改方法以从背包中移除物品
    public void removeFromInventory(String itemName, int quantity) {
        if (inventory.containsKey(itemName)) {
            Item item = (Item) inventory.get(itemName);
            item.decreaseQuantity(quantity);
            if (item.getQuantity() <= 0) {
                inventory.remove(itemName);
            }
        }
    }

    public void removeFromInventory(String mountName) {
        if (inventory.containsKey(mountName)) {
            inventory.remove(mountName);
        }
    }

    public Map<String, Object> getInventory() {
        return inventory;
    }
}