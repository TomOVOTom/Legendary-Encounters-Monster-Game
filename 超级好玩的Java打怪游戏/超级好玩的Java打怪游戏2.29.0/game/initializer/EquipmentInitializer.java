// File: src/game/initializer/EquipmentInitializer.java
package game.initializer;

import game.equipment.Equipment;
import game.item.ItemRegistry;

public class EquipmentInitializer {
    public static void registerAllEquipment() {
        Equipment woodSword = new Equipment("木剑", "Weapon", 10, 0, 0, 0, 0, 0, 10, 1);
        Equipment leatherArmor = new Equipment("皮甲", "Armor", 0, 5, 5, 0, 0, 0, 10, 1);
        Equipment ironSword = new Equipment("铁剑", "Weapon", 20, 0, 0, 0, 0, 0, 30, 1);
        Equipment chainmail = new Equipment("锁子甲", "Armor", 0, 10, 10, 0, 0, 0, 50, 1);
        Equipment dagger = new Equipment("匕首", "Weapon", 15, 0, 0, 0, 0, 5, 20, 1);
        Equipment leatherBoots = new Equipment("皮靴", "Boots", 0, 3, 0, 0, 5, 5, 15, 1);
        Equipment dragonSword = new Equipment("龙剑", "Weapon", 50, 0, 0, 0, 0, 0, 100, 1);
        Equipment phoenixArmor = new Equipment("凤凰甲", "Armor", 0, 50, 50, 0, 0, 0, 100, 1);
        Equipment shadowDagger = new Equipment("影匕", "Weapon", 40, 0, 0, 0, 0, 20, 80, 1);
        Equipment celestialBoots = new Equipment("天体靴", "Boots", 0, 10, 0, 0, 20, 20, 50, 1);
        Equipment mysteriousChest = new Equipment("神秘宝箱", "Chest", 0, 0, 0, 0, 0, 0, 100, 1);
        Equipment flameSword = new Equipment("火焰之剑", "Weapon", 50, 0, 0, 0, 0, 0, 100, 1);
        Equipment dragonTreasure = new Equipment("龙之宝藏", "Chest", 0, 0, 0, 0, 0, 0, 200, 1);
        Equipment ghostRing = new Equipment("幽灵之戒", "Ring", 0, 0, 0, 0, 0, 0, 50, 1);
        Equipment frostShield = new Equipment("冰霜之盾", "Shield", 0, 50, 50, 0, 0, 0, 100, 1);
        Equipment desertHeart = new Equipment("沙漠之心", "Amulet", 0, 0, 0, 0, 0, 0, 75, 1);
        Equipment oceanOrb = new Equipment("海洋之珠", "Orb", 0, 0, 0, 0, 0, 0, 150, 1);
        Equipment lavaCore = new Equipment("熔岩之核", "Core", 0, 0, 0, 0, 0, 0, 125, 1);

        ItemRegistry.registerEquipment(woodSword);
        ItemRegistry.registerEquipment(leatherArmor);
        ItemRegistry.registerEquipment(ironSword);
        ItemRegistry.registerEquipment(chainmail);
        ItemRegistry.registerEquipment(dagger);
        ItemRegistry.registerEquipment(leatherBoots);
        ItemRegistry.registerEquipment(dragonSword);
        ItemRegistry.registerEquipment(phoenixArmor);
        ItemRegistry.registerEquipment(shadowDagger);
        ItemRegistry.registerEquipment(celestialBoots);
        ItemRegistry.registerEquipment(mysteriousChest);
        ItemRegistry.registerEquipment(flameSword);
        ItemRegistry.registerEquipment(dragonTreasure);
        ItemRegistry.registerEquipment(ghostRing);
        ItemRegistry.registerEquipment(frostShield);
        ItemRegistry.registerEquipment(desertHeart);
        ItemRegistry.registerEquipment(oceanOrb);
        ItemRegistry.registerEquipment(lavaCore);
    }
}