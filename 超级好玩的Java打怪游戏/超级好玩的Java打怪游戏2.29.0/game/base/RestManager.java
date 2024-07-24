// File: src/game/base/RestManager.java
package game.base;

import game.player.Player;
import game.pet.Pet;

public class RestManager {
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
        System.out.println("当前生命值：" + player.attributes.hp);
        System.out.println("当前体力值：" + player.attributes.stamina);

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