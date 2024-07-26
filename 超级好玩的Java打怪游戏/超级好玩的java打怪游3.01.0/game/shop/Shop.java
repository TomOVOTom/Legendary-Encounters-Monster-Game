// File: src/game/shop/Shop.java
package game.shop;

import game.player.Player;

import java.util.Scanner;

public class Shop {
    public static void enterShop(Player player, Scanner scanner) {
        System.out.println("欢迎来到商店！当前金币：" + player.attributes.gold);

        System.out.println("请选择你想要查看的商品类别：");
        System.out.println("1. 道具");
        System.out.println("2. 武器");
        System.out.println("3. 护甲");
        System.out.println("4. 鞋子");
        System.out.println("5. 坐骑");

        int categoryChoice = scanner.nextInt();

        switch (categoryChoice) {
            case 1:
                ShopDisplay.displayItems();
                break;
            case 2:
                ShopDisplay.displayWeapons();
                break;
            case 3:
                ShopDisplay.displayArmors();
                break;
            case 4:
                ShopDisplay.displayBoots();
                break;
            case 5:
                ShopDisplay.displayMounts();
                break;
            default:
                System.out.println("无效的选择！");
                return;
        }

        System.out.println("请选择你想要购买的物品：");
        int shopChoice = scanner.nextInt();

        // 处理购买
        try {
            ShopPurchase.handlePurchase(player, categoryChoice, shopChoice, scanner);
        } catch (Exception e) {
            System.out.println("发生错误，请输入有效数字。");
        }
    }
}