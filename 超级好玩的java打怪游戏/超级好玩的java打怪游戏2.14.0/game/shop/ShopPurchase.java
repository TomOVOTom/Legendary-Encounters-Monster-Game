// File: src/game/shop/ShopPurchase.java
package game.shop;

import game.equipment.Equipment;
import game.item.Item;
import game.mount.Mount;
import game.player.Player;

public class ShopPurchase {
    public static void handlePurchase(Player player, int categoryChoice, int shopChoice) {
        int index = shopChoice - 1;
        switch (categoryChoice) {
            case 1:
                if (index < ShopDisplay.items.size()) {
                    purchaseItem(player, ShopDisplay.items.get(index));
                } else {
                    System.out.println("无效的商店选择！");
                }
                break;
            case 2:
                if (index < ShopDisplay.weapons.size()) {
                    purchaseItem(player, ShopDisplay.weapons.get(index));
                } else {
                    System.out.println("无效的商店选择！");
                }
                break;
            case 3:
                if (index < ShopDisplay.armors.size()) {
                    purchaseItem(player, ShopDisplay.armors.get(index));
                } else {
                    System.out.println("无效的商店选择！");
                }
                break;
            case 4:
                if (index < ShopDisplay.boots.size()) {
                    purchaseItem(player, ShopDisplay.boots.get(index));
                } else {
                    System.out.println("无效的商店选择！");
                }
                break;
            case 5:
                if (index < ShopDisplay.mounts.size()) {
                    purchaseMount(player, ShopDisplay.mounts.get(index));
                } else {
                    System.out.println("无效的商店选择！");
                }
                break;
            default:
                System.out.println("无效的商店选择！");
                break;
        }
    }

    private static void purchaseItem(Player player, Item item) {
        if (player.attributes.gold >= item.price) {
            player.attributes.gold -= item.price;
            player.inventory.addToInventory(item);
            System.out.println("你购买了 " + item.name + "！");
        } else {
            System.out.println("你的金币不足，无法购买" + item.name + "！");
        }
    }

    private static void purchaseItem(Player player, Equipment equipment) {
        if (player.attributes.gold >= equipment.price) {
            player.attributes.gold -= equipment.price;
            player.inventory.addToInventory(equipment);
            System.out.println("你购买了 " + equipment.name + "！");
        } else {
            System.out.println("你的金币不足，无法购买" + equipment.name + "！");
        }
    }

    private static void purchaseMount(Player player, Mount mount) {
        if (player.attributes.gold >= mount.getPrice()) {
            player.attributes.gold -= mount.getPrice();
            player.inventory.addToInventory(mount);
            System.out.println("你购买了 " + mount.getName() + "！");
        } else {
            System.out.println("你的金币不足，无法购买" + mount.getName() + "！");
        }
    }
}