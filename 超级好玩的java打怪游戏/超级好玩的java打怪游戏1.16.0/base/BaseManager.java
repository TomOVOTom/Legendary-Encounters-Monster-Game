package game.base;

import game.Player;
import game.pet.Pet;

import java.util.Scanner;

public class BaseManager {

    public static void baseActions(Player player, Scanner scanner) {
        if (player.base == null) {
            System.out.println("你还没有基地。创建基地需要200金币。是否创建？(1. 是 2. 否)");
            int createBaseChoice = scanner.nextInt();
            if (createBaseChoice == 1) {
                if (player.attributes.gold >= 200) {
                    player.attributes.gold -= 200;
                    player.base = new Base();
                    System.out.println("基地创建成功！你花费了200金币，当前剩余金币：" + player.attributes.gold);
                } else {
                    System.out.println("你的金币不足，无法创建基地！");
                    return;
                }
            } else {
                return;
            }
        }

        while (true) {
            System.out.println("基地：1. 休息 2. 升级基地 3. 查看设施 4. 返回");
            int baseChoice = scanner.nextInt();
            if (baseChoice == 1) {
                rest(player);
            } else if (baseChoice == 2) {
                upgradeBase(player, scanner);
            } else if (baseChoice == 3) {
                viewFacilities(player);
            } else if (baseChoice == 4) {
                break;
            } else {
                System.out.println("无效的选择！");
            }
        }
    }

    private static void upgradeBase(Player player, Scanner scanner) {
        int upgradeCost = player.base.getGoldCost();
        System.out.println("升级基地需要 " + upgradeCost + " 金币。是否升级？(1. 是 2. 否)");
        int upgradeChoice = scanner.nextInt();
        if (upgradeChoice == 1) {
            if (player.attributes.gold < upgradeCost) {
                System.out.println("你的金币不足，无法升级基地！");
                return;
            }
            player.attributes.gold -= upgradeCost;
            player.base.upgrade();
            System.out.println("基地升级成功！你花费了 " + upgradeCost + " 金币，当前剩余金币：" + player.attributes.gold + "。当前基地等级：" + player.base.getLevel());
        } else {
            System.out.println("你选择了不升级基地。");
        }
    }

    private static void viewFacilities(Player player) {
        String[] facilities = player.base.getUnlockedFeatures();
        System.out.println("当前基地设施：");
        for (String facility : facilities) {
            System.out.println("- " + facility);
        }
    }

    public static void rest(Player player) {
        int restCost = 50;
        if (player.attributes.gold < restCost) {
            System.out.println("你的金币不足，无法休息！");
            return;
        }
        player.attributes.gold -= restCost;
        System.out.println("你花费了 " + restCost + " 金币进行休息。当前剩余金币：" + player.attributes.gold);

        int restoredHp = player.base.getRestoredHp();
        int restoredStamina = player.base.getRestoredStamina();
        player.attributes.hp += restoredHp;
        player.attributes.stamina += restoredStamina;
        System.out.println("你选择了休息，恢复了 " + restoredHp + " 点生命值和 " + restoredStamina + " 点体力！");

        // Restore health for all pets
        int petRestoredHp = 30; // Amount of health to restore to each pet
        int petRestoredStamina = 10; // Amount of stamina to restore to each pet
        for (Pet pet : player.pets) {
            if (pet.hp > 0) {
                pet.hp += petRestoredHp;
                pet.stamina += petRestoredStamina;
                System.out.println("你的宠物 " + pet.name + " 也恢复了 " + petRestoredHp + " 点生命值和 " + petRestoredStamina + " 点体力！");
            }
        }
    }
}