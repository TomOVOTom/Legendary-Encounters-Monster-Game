// InventoryManager.java
package game;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InventoryManager {

    // 使用物品的方法
    public static void useItem(Player player, Scanner scanner) {
        // 分类物品
        Map<String, Item> itemItems = player.inventory.entrySet().stream()
                .filter(entry -> !(entry.getValue() instanceof Equipment))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<String, Equipment> equipmentItems = player.inventory.entrySet().stream()
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
                System.out.println((i + 1) + ". " + itemNames[i] + " x" + player.inventory.get(itemNames[i]).getQuantity());
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

            // 使用物品
            String selectedItemName = itemNames[itemChoice - 1];
            Item selectedItem = ItemRegistry.getItemByName(selectedItemName);

            // 增加空值检查
            if (selectedItem == null) {
                System.out.println("未找到物品：" + selectedItemName + "。请确保物品名称正确并已注册。");
                return;
            }

            // 检查是否为装备类型
            if (selectedItem instanceof Equipment) {
                System.out.println("你购买了 " + selectedItem.getName() + "！");
                player.equip((Equipment) selectedItem);
            } else {
                // 根据 selectedItem.type 判断物品类型并执行相应操作
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
                        System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点闪避！");
                        break;
                    case "intelligence":
                        player.intelligence += selectedItem.getEffect();
                        System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点智力！");
                        break;
                    case "damage":
                        Monster monster = Game.findCurrentMonster(); // 正确访问findCurrentMonster方法
                        if (monster != null) {
                            int damage = selectedItem.getEffect();
                            monster.hp -= damage;
                            System.out.println("你使用了 " + selectedItem.getName() + "，对 " + monster.name + " 造成了 " + damage + " 点伤害！");
                        }
                        break;
                    default:
                        System.out.println("未知的物品类型！");
                        break;
                }
            }

            player.removeFromInventory(selectedItemName, 1); // 使用后从背包移除一个单位的物品
        } catch (InputMismatchException e) {
            System.out.println("请输入有效的数字！");
            scanner.nextLine(); // Consume the wrong input before retrying or exiting
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
        for (Map.Entry<String, Item> entry : player.inventory.entrySet()) {
            System.out.println(entry.getKey() + " x" + entry.getValue().getQuantity());
        }
    }
}