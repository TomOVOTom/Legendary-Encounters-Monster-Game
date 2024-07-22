// File: src/game/pet/PetUtils.java
package game.pet;

import game.monster.Monster;

public class PetUtils {
    public static void initializeSkills(Pet pet) {
        PetSkillInitializer.initializeSkills(pet);
    }

    public static void attack(Pet pet, Monster monster) {
        PetCombat.attack(pet, monster);
    }

    public static void gainExp(Pet pet, int exp) {
        PetExperience.gainExp(pet, exp);
    }

    public static void calculateCombatPower(Pet pet) {
        PetAttributes.calculateCombatPower(pet);
    }

    public static void displayAttributes(Pet pet) {
        PetAttributes.displayAttributes(pet);
    }
}