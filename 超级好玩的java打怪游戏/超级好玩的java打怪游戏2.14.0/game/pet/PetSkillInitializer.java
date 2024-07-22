// File: src/game/pet/PetSkillInitializer.java
package game.pet;

public class PetSkillInitializer {
    public static void initializeSkills(Pet pet) {
        pet.skills.add(new PetSkill("基础攻击", 10, 0, 0, 1.0, 0.0));
        pet.skills.add(new PetSkill("火焰喷射", 20, 3, 5, 1.2, 0.5));
        pet.skills.add(new PetSkill("冰霜打击", 15, 2, 4, 1.1, 0.4));
        pet.skills.add(new PetSkill("雷电冲击", 25, 4, 6, 1.3, 0.6));
        pet.skills.add(new PetSkill("风暴之怒", 30, 5, 7, 1.4, 0.7));
        pet.skills.add(new PetSkill("大地震击", 35, 6, 8, 1.5, 0.8));
    }
}