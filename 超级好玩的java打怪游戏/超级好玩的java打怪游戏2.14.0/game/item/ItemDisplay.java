// File: src/game/item/ItemDisplay.java
package game.item;

import game.equipment.Equipment;
import game.equipment.EquipmentManager;
import game.player.Player;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ItemDisplay {

    public static void displayInventory(Player player) {
        System.out.println("持有的物品：");
        for (Map.Entry<String, Object> entry : player.getInventory().getInventory().entrySet()) {
            if (entry.getValue() instanceof Item) {
                System.out.println(entry.getKey() + " x" + ((Item) entry.getValue()).getQuantity());
            }
        }
    }

    public static void displayItemSelection(Player player, Map<String, ? extends Item> selectedCategory, Scanner scanner) {
        System.out.println("请选择您的物品 (输入0取消)：");
        String[] itemNames = selectedCategory.keySet().toArray(new String[0]);
        for (int i = 0; i < itemNames.length; i++) {
            System.out.println((i + 1) + ". " + itemNames[i] + " x" + ((Item) player.getInventory().getInventory().get(itemNames[i])).getQuantity());
        }

        int itemChoice = scanner.nextInt();
        if (itemChoice == 0) {
            System.out.println("取消使用物品。");
            return;
        }
        if (itemChoice < 1 || itemChoice > itemNames.length) {
            System.out.println("无效的物品选择！");
            return;
        }

        String selectedItemName = itemNames[itemChoice - 1];
        Item selectedItem = ItemRegistry.getItemByName(selectedItemName);

        if (selectedItem == null) {
            System.out.println("未找到物品：" + selectedItemName + "。请确保物品名称正确并已注册。");
            return;
        }

        if (selectedItem instanceof Equipment) {
            EquipmentManager.equip(player, (Equipment) selectedItem);
        } else {
            ItemUsage.applyItemEffect(player, selectedItem);
        }

        player.getInventory().removeFromInventory(selectedItemName, 1);
    }

    public static void displayItemCategories(Player player, Scanner scanner, Map<String, Item> itemItems, Map<String, Equipment> equipmentItems) {
        System.out.println("请选择物品类别 (输入0取消)：");
        System.out.println("1. 道具类");
        System.out.println("2. 装备类");

        try {
            int categoryChoice = scanner.nextInt();
            if (categoryChoice == 0) {
                System.out.println("取消使用物品。");
                return;
            }

            Map<String, ? extends Item> selectedCategory = null;
            switch (categoryChoice) {
                case 1:
                    selectedCategory = itemItems;
                    break;
                case 2:
                    selectedCategory = equipmentItems;
                    break;
                default:
                    System.out.println("无效的类别选择！");
                    return;
            }

            if (selectedCategory == null || selectedCategory.isEmpty()) {
                System.out.println("该类别下没有物品！");
                return;
            }

            displayItemSelection(player, selectedCategory, scanner);
        } catch (InputMismatchException e) {
            System.out.println("请输入有效的数字！");
            scanner.nextLine();
        }
    }
}