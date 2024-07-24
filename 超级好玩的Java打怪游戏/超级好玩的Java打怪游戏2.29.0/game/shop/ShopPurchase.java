package game.shop;

import game.equipment.Equipment;
import game.item.Item;
import game.mount.Mount;
import game.player.Player;

import java.util.Scanner;

public class ShopPurchase {
    public static void handlePurchase(Player player, int categoryChoice, int shopChoice, Scanner scanner) {
        int index = shopChoice - 1;
        switch (categoryChoice) {
            case 1:
                if (index < ShopDisplay.items.size()) {
                    purchaseItem(player, ShopDisplay.items.get(index), scanner);
                } else {
                    System.out.println("无效的商店选择！");
                }
                break;
            case 2:
                if (index < ShopDisplay.weapons.size()) {
                    purchaseEquipment(player, ShopDisplay.weapons.get(index), scanner);
                } else {
                    System.out.println("无效的商店选择！");
                }
                break;
            case 3:
                if (index < ShopDisplay.armors.size()) {
                    purchaseEquipment(player, ShopDisplay.armors.get(index), scanner);
                } else {
                    System.out.println("无效的商店选择！");
                }
                break;
            case 4:
                if (index < ShopDisplay.boots.size()) {
                    purchaseEquipment(player, ShopDisplay.boots.get(index), scanner);
                } else {
                    System.out.println("无效的商店选择！");
                }
                break;
            case 5:
                if (index < ShopDisplay.mounts.size()) {
                    purchaseMount(player, ShopDisplay.mounts.get(index), scanner);
                } else {
                    System.out.println("无效的商店选择！");
                }
                break;
            default:
                System.out.println("无效的商店选择！");
                break;
        }
    }

    private static void purchaseItem(Player player, Item item, Scanner scanner) {
        System.out.println("请输入要购买的数量：");
        int quantity = scanner.nextInt();
        int totalPrice = item.price * quantity;

        if (player.attributes.gold >= totalPrice) {
            player.attributes.gold -= totalPrice;
            player.inventory.addToInventory(item, quantity);
            System.out.println("你购买了 " + quantity + " 个 " + item.name + "！");
        } else {
            System.out.println("你的金币不足，无法购买 " + quantity + " 个 " + item.name + "！");
        }
    }

    private static void purchaseEquipment(Player player, Equipment equipment, Scanner scanner) {
        System.out.println("请输入要购买的数量：");
        int quantity = scanner.nextInt();
        int totalPrice = equipment.price * quantity;

        if (player.attributes.gold >= totalPrice) {
            player.attributes.gold -= totalPrice;
            player.inventory.addToInventory(equipment, quantity);
            System.out.println("你购买了 " + quantity + " 个 " + equipment.name + "！");
        } else {
            System.out.println("你的金币不足，无法购买 " + quantity + " 个 " + equipment.name + "！");
        }
    }

    private static void purchaseMount(Player player, Mount mount, Scanner scanner) {
        System.out.println("请输入要购买的数量：");
        int quantity = scanner.nextInt();
        int totalPrice = mount.getPrice() * quantity;

        if (player.attributes.gold >= totalPrice) {
            player.attributes.gold -= totalPrice;
            player.inventory.addToInventory(mount, quantity);
            System.out.println("你购买了 " + quantity + " 个 " + mount.getName() + "！");
        } else {
            System.out.println("你的金币不足，无法购买 " + quantity + " 个 " + mount.getName() + "！");
        }
    }
}