// File: src/game/base/BaseActions.java
package game.base;

import game.equipment.EquipmentManager;
import game.inventory.InventoryManager;
import game.mount.MountTrainer;
import game.pet.PetTrainer;
import game.player.Player;
import java.util.Scanner;

public class BaseActions {
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
            System.out.println("基地：1. 休息 2. 升级基地 3. 查看设施 4. 学习技能 5. 装备升级 6. 坐骑训练 7. 宠物训练 8. 查看装备和物品 9. 管理任务 10. 返回");
            int baseChoice = scanner.nextInt();
            if (baseChoice == 1) {
                RestManager.rest(player);
            } else if (baseChoice == 2) {
                BaseUpgrade.upgradeBase(player, scanner);
            } else if (baseChoice == 3) {
                FacilityViewer.viewFacilities(player);
            } else if (baseChoice == 4) {
                SkillLearner.learnSkills(player, scanner);
            } else if (baseChoice == 5) {
                EquipmentManager.upgradeEquipment(player, scanner);
            } else if (baseChoice == 6) {
                MountTrainer.trainMount(player, scanner);
            } else if (baseChoice == 7) {
                PetTrainer.trainPet(player, scanner);
            } else if (baseChoice == 8) {
                InventoryManager.displayInventory(player);
            } else if (baseChoice == 9) {
                player.base.handleQuests(scanner, player);
            } else if (baseChoice == 10) {
                break;
            } else {
                System.out.println("无效的选择！");
            }
        }
    }
}