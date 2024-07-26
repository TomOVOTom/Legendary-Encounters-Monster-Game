// File: src/game/pet/PetManager.java
package game.pet;

import game.player.Player;

import java.util.Scanner;

public class PetManager {
    public static void managePets(Player player, Scanner scanner) {
        while (true) {
            System.out.println("宠物管理：1. 查看宠物 2. 装备宠物 3. 自动装备 4. 卸下宠物 5. 复活宠物 6. 查看已装备宠物 7. 返回");
            int petChoice = scanner.nextInt();
            switch (petChoice) {
                case 1:
                    PetOperations.listPets(player);
                    break;
                case 2:
                    System.out.println("请输入要装备的宠物名称：");
                    String petName = scanner.next();
                    Pet petToEquip = player.pets.stream().filter(p -> p.name.equals(petName)).findFirst().orElse(null);
                    if (petToEquip != null) {
                        PetOperations.equipPet(player, petToEquip);
                    } else {
                        System.out.println("未找到该名称的宠物。");
                    }
                    break;
                case 3:
                    PetOperations.autoEquipPet(player);
                    break;
                case 4:
                    PetOperations.unequipPet(player);
                    break;
                case 5:
                    PetOperations.revivePets(player, scanner);
                    break;
                case 6:
                    PetOperations.viewEquippedPet(player);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("无效的选择！");
            }
        }
    }


    public static Pet getEquippedPet(Player player) {
        return PetOperations.getEquippedPet(player);
    }
}