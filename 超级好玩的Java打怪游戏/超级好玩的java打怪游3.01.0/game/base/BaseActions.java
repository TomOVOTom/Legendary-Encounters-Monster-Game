package game.base;

import game.equipment.EquipmentManager;
import game.inventory.InventoryManager;
import game.mount.MountTrainer;
import game.pet.Pet;
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
            System.out.println("基地：1. 休息 2. 升级基地 3. 使用泉水恢复 4. 查看设施 5. 学习技能 6. 装备升级 7. 坐骑训练 8. 宠物训练 9. 查看装备和物品 10. 管理任务 11. 返回");
            int baseChoice = scanner.nextInt();
            if (baseChoice == 1) {
                RestManager.rest(player);
            } else if (baseChoice == 2) {
                BaseUpgrade.upgradeBase(player, scanner);
            } else if (baseChoice == 3) {
                int springCost = 1000; //
                if (player.attributes.gold >= springCost) {
                    System.out.println("使用泉水恢复需要花费 " + springCost + " 金币。是否继续？(1. 是 2. 否)");
                    int continueChoice = scanner.nextInt();
                    if (continueChoice == 1) {
                        player.attributes.gold -= springCost;
                        player.attributes.hp +=10000;
                        player.attributes.stamina +=1000;
                        System.out.println("你在泉水中恢复了所有的血量和体力。");
                        System.out.println("当前血量：" + player.attributes.hp + "，体力：" + player.attributes.stamina + "，剩余金币：" + player.attributes.gold);

                        for (Pet pet : player.pets) {
                            pet.hp += 10000; // Assuming you want to fully restore pet health
                            pet.stamina += 1000; // Assuming you want to fully restore pet stamina
                            System.out.println("你的宠物 " + pet.name + " 也在泉水中恢复了所有的血量和体力。");
                            System.out.println(pet.name + " 当前血量：" + pet.hp + "，体力：" + pet.stamina);

                        }
                    } else {
                        System.out.println("你选择不使用泉水恢复。");
                    }
                } else {
                    System.out.println("你的金币不足，无法使用泉水恢复。当前金币：" + player.attributes.gold);
                }
            } else if (baseChoice == 4) {
                FacilityViewer.viewFacilities(player);
            } else if (baseChoice == 5) {
                SkillLearner.learnSkills(player, scanner);
            } else if (baseChoice == 6) {
                EquipmentManager.upgradeEquipment(player, scanner);
            } else if (baseChoice == 7) {
                MountTrainer.trainMount(player, scanner);
            } else if (baseChoice == 8) {
                PetTrainer.trainPet(player, scanner);
            } else if (baseChoice == 9) {
                InventoryManager.displayInventory(player);
            } else if (baseChoice == 10) {
                player.base.handleQuests(scanner, player);
            } else if (baseChoice == 11) {
                break;
            } else {
                System.out.println("无效的选择！");
            }
        }
    }
}