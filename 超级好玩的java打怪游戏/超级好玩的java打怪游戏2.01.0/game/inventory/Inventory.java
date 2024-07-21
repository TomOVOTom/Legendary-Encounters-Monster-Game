// game/inventory/Inventory.java
package game.inventory;

import game.equipment.Equipment;
import game.item.Item;
import game.mount.Mount;

import java.util.HashMap;
import java.util.Map;

public class Inventory implements java.io.Serializable {
    private Map<String, Object> inventory = new HashMap<>();

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