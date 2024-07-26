// File: src/game/pet/PetExperience.java
package game.pet;

public class PetExperience {
    public static void gainExp(Pet pet, int exp) {
        pet.exp += exp;
        int requiredExp = (int) Math.pow(pet.level * 100, 1.2);
        if (pet.exp >= requiredExp) {
            pet.level++;
            pet.exp -= requiredExp;
            System.out.println("你的宠物 " + pet.name + " 升级了！当前等级：" + pet.level);
            // Increase pet attributes on level up
            pet.hp += 10;
            pet.attack += 2;
            pet.defense += 2;
            pet.intelligence += 2;
            pet.stamina += 5;
            pet.dodge += 1;
            PetUtils.calculateCombatPower(pet);
        }
    }
}