package game.inventory;

import game.equipment.Equipment;
import game.item.Item;
import game.mount.Mount;

import java.util.HashMap;
import java.util.Map;

public class Inventory implements java.io.Serializable {
    private Map<String, Object> inventory = new HashMap<>();

    public void addToInventory(Item item, int quantity) {
        if (inventory.containsKey(item.getName())) {
            ((Item) inventory.get(item.getName())).increaseQuantity(quantity);
        } else {
            item.setQuantity(quantity); // 设置初始数量
            inventory.put(item.getName(), item);
        }
    }

    public void addToInventory(Equipment equipment, int quantity) {
        if (inventory.containsKey(equipment.name)) {
            ((Equipment) inventory.get(equipment.name)).increaseQuantity(quantity);
        } else {
            equipment.setQuantity(quantity); // 设置初始数量
            inventory.put(equipment.name, equipment);
        }
    }

    public void addToInventory(Mount mount, int quantity) {
        if (inventory.containsKey(mount.getName())) {
            ((Mount) inventory.get(mount.getName())).increaseQuantity(quantity);
        } else {
            mount.setQuantity(quantity); // 设置初始数量
            inventory.put(mount.getName(), mount);
        }
    }

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