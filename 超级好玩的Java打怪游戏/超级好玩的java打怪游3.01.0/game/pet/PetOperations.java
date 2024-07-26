// File: src/game/pet/PetOperations.java
package game.pet;

import game.player.Player;

import java.util.Comparator;
import java.util.Scanner;

public class PetOperations {

    public static void viewEquippedPet(Player player) {
        Pet equippedPet = player.getEquippedPet();
        if (equippedPet != null) {
            System.out.println("当前已装备的宠物：");
            equippedPet.displayAttributes();
        } else {
            System.out.println("你没有装备任何宠物！");
        }
    }

    public static void addPet(Player player, Pet pet) {
        player.pets.add(pet);
        System.out.println("你获得了新的宠物: " + pet.name);
    }

    public static void removePet(Player player, Pet pet) {
        player.pets.remove(pet);
        System.out.println("你移除了宠物: " + pet.name);
    }

    public static void listPets(Player player) {
        if (player.pets.isEmpty()) {
            System.out.println("你没有任何宠物。");
        } else {
            System.out.println("你的宠物列表:");
            for (Pet pet : player.pets) {
                pet.displayAttributes();
            }
        }
    }

    public static void equipPet(Player player, Pet pet) {
        if (pet.hp <= 0) {
            System.out.println("无法装备血量小于等于0的宠物！");
            return;
        }
        if (player.equippedPet != null) {
            System.out.println("你已经装备了宠物 " + player.equippedPet.name + "，先卸下它！");
            unequipPet(player);
        }
        player.equippedPet = pet;
        System.out.println("你装备了宠物 " + pet.name + "！");
    }

    public static void autoEquipPet(Player player) {
        Pet highestAttackPet = player.pets.stream().filter(p -> p.hp > 0).max(Comparator.comparingInt(p -> p.attack)).orElse(null);
        if (highestAttackPet != null) {
            equipPet(player, highestAttackPet);
        } else {
            System.out.println("没有可装备的宠物！");
        }
    }

    public static void unequipPet(Player player) {
        if (player.equippedPet != null) {
            System.out.println("你卸下了宠物 " + player.equippedPet.name + "！");
            player.equippedPet = null;
        } else {
            System.out.println("你没有装备任何宠物！");
        }
    }

    public static void revivePets(Player player, Scanner scanner) {
        for (Pet pet : player.pets) {
            if (pet.hp <= 0) {
                int reviveCost = pet.combatPower * 17;
                System.out.println("复活宠物 " + pet.name + " 需要 " + reviveCost + " 金币。是否复活？(y/n)");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("y") && player.attributes.gold >= reviveCost) {
                    player.attributes.gold -= reviveCost;
                    pet.hp = pet.level * 200; // 恢复一定的生命值
                    System.out.println("宠物 " + pet.name + " 已复活！");
                } else if (choice.equalsIgnoreCase("y")) {
                    System.out.println("金币不足，无法复活宠物 " + pet.name + "！");
                }
            }
        }
    }

    public static Pet getEquippedPet(Player player) {
        return player.equippedPet;
    }
}