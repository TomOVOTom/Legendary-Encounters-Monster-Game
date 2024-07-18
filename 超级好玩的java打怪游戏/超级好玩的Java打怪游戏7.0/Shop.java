// Shop.java
package game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Shop {
    public static void enterShop(Player player, Scanner scanner) {
        System.out.println("欢迎来到商店！当前金币：" + player.gold);

        // 从注册表获取所有物品、装备和坐骑
        List<Item> items = new ArrayList<>(ItemRegistry.getAllItems().values());
        List<Equipment> equipments = new ArrayList<>(ItemRegistry.getAllEquipments().values());
        List<Mount> mounts = new ArrayList<>(ItemRegistry.getAllMounts().values());

        // 按价格排序物品
        items.sort(Comparator.comparingInt(Item::getPrice));

        // 按类型分组并排序装备
        List<Equipment> weapons = equipments.stream()
                .filter(e -> e.getType().equals("Weapon"))
                .sorted(Comparator.comparingInt(Equipment::getPrice))
                .collect(Collectors.toList());

        List<Equipment> armors = equipments.stream()
                .filter(e -> e.getType().equals("Armor"))
                .sorted(Comparator.comparingInt(Equipment::getPrice))
                .collect(Collectors.toList());
        List<Equipment> boots = equipments.stream()
                .filter(e -> e.getType().equals("Boots"))
                .sorted(Comparator.comparingInt(Equipment::getPrice))
                .collect(Collectors.toList());

        // 按价格排序坐骑
        mounts.sort(Comparator.comparingInt(Mount::getPrice));

        // 展示出售的物品、装备和坐骑
        int index = 1;
        System.out.println("可购买的道具：");
        for (Item item : items) {
            System.out.println(index++ + ". " + item.getName() + " - 价格：" + item.getPrice() + "金币");
        }

        System.out.println("可购买的武器：");
        for (Equipment weapon : weapons) {
            System.out.println(index++ + ". " + weapon.getName() + " - 价格：" + weapon.getPrice() + "金币");
        }

        System.out.println("可购买的护甲：");
        for (Equipment armor : armors) {
            System.out.println(index++ + ". " + armor.getName() + " - 价格：" + armor.getPrice() + "金币");
        }

        System.out.println("可购买的鞋子：");
        for (Equipment boot : boots) {
            System.out.println(index++ + ". " + boot.getName() + " - 价格：" + boot.getPrice() + "金币");
        }

        System.out.println("可购买的坐骑：");
        for (Mount mount : mounts) {
            System.out.println(index++ + ". " + mount.getName() + " - 价格：" + mount.getPrice() + "金币");
        }

        System.out.println("请选择你想要购买的物品：");
        int shopChoice = scanner.nextInt();

        // 处理购买
        try {
            if (shopChoice <= items.size()) {
                purchaseItem(player, items.get(shopChoice - 1));
            } else if (shopChoice <= items.size() + weapons.size()) {
                purchaseItem(player, weapons.get(shopChoice - items.size() - 1));
            } else if (shopChoice <= items.size() + weapons.size() + armors.size()) {
                purchaseItem(player, armors.get(shopChoice - items.size() - weapons.size() - 1));
            } else if (shopChoice <= items.size() + weapons.size() + armors.size() + boots.size()) {
                purchaseItem(player, boots.get(shopChoice - items.size() - weapons.size() - armors.size() - 1));
            } else if (shopChoice <= items.size() + weapons.size() + armors.size() + boots.size() + mounts.size()) {
                purchaseMount(player, mounts.get(shopChoice - items.size() - weapons.size() - armors.size() - boots.size() - 1));
            } else {
                System.out.println("无效的商店选择！");
            }
        } catch (Exception e) {
            System.out.println("发生错误，请输入有效数字。");
        }
    }

    private static void purchaseItem(Player player, Item item) {
        if (player.gold >= item.price) {
            player.gold -= item.price;
            player.addToInventory(item);
            System.out.println("你购买了 " + item.name + "！");
        } else {
            System.out.println("你的金币不足，无法购买" + item.name + "！");
        }
    }

    private static void purchaseItem(Player player, Equipment equipment) {
        if (player.gold >= equipment.price) {
            player.gold -= equipment.price;
            player.addToInventory(equipment);
            System.out.println("你购买了 " + equipment.name + "！");
        } else {
            System.out.println("你的金币不足，无法购买" + equipment.name + "！");
        }
    }

    private static void purchaseMount(Player player, Mount mount) {
        if (player.gold >= mount.getPrice()) {
            player.gold -= mount.getPrice();
            player.addToInventory(mount);
            System.out.println("你购买了 " + mount.getName() + "！");
        } else {
            System.out.println("你的金币不足，无法购买" + mount.getName() + "！");
        }
    }
}