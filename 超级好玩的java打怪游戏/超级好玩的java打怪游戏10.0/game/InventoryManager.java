// InventoryManager.java
package game;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InventoryManager {

    public static void useItem(Player player, Scanner scanner) {
        Map<String, Item> itemItems = player.getInventory().getInventory().entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Item)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (Item) entry.getValue()));

        Map<String, Equipment> equipmentItems = player.getInventory().getInventory().entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Equipment)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (Equipment) entry.getValue()));

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
//                System.out.println("你购买了 " + selectedItem.getName() + "！");
                EquipmentManager.equip(player, (Equipment) selectedItem);
            } else {
                switch (selectedItem.getType()) {
                    case "defense":
                        player.defense += selectedItem.getEffect();
                        System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点防御力！");
                        break;
                    case "stamina":
                        player.stamina += selectedItem.getEffect();
                        System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点体力！");
                        break;
                    case "dodge":
                        player.dodge += selectedItem.getEffect();
                        System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点闪避��");
                        break;
                    case "intelligence":
                        player.intelligence += selectedItem.getEffect();
                        System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点智力！");
                        break;
                    case "damage":
                        Monster monster = Game.findCurrentMonster();
                        if (monster != null) {
                            int damage = selectedItem.getEffect();
                            monster.hp -= damage;
                            System.out.println("你使用了 " + selectedItem.getName() + "，对 " + monster.name + " 造成了 " + damage + " 点伤害！");
                        }
                        break;
                    case "healing":
                        player.hp += selectedItem.getEffect();
                        System.out.println("你使用了 " + selectedItem.getName() + "，恢复了 " + selectedItem.getEffect() + " 点生命值！");
                        break;
                    default:
                        System.out.println("未知的物品类型！");
                        break;
                }
            }

            player.getInventory().removeFromInventory(selectedItemName, 1);
        } catch (InputMismatchException e) {
            System.out.println("请输入有效的数字！");
            scanner.nextLine();
        }
    }

    public static void displayInventory(Player player) {
        System.out.println("当前装备：");
        if (player.equippedWeapon != null) {
            System.out.println("武器: " + player.equippedWeapon.name);
        }
        if (player.equippedArmor != null) {
            System.out.println("护甲: " + player.equippedArmor.name);
        }
        if (player.equippedBoots != null) {
            System.out.println("靴子: " + player.equippedBoots.name);
        }

        System.out.println("持有的物品：");
        for (Map.Entry<String, Object> entry : player.getInventory().getInventory().entrySet()) {
            if (entry.getValue() instanceof Item) {
                System.out.println(entry.getKey() + " x" + ((Item) entry.getValue()).getQuantity());
            } else if (entry.getValue() instanceof Equipment) {
                System.out.println(entry.getKey() + " (装备)");
            } else if (entry.getValue() instanceof Mount) {
                System.out.println(entry.getKey() + " (坐骑)");
            }
        }
    }
}