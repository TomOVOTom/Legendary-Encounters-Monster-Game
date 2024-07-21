// File: src/game/initializer/GameInitializer.java
package game.initializer;

import game.equipment.Equipment;
import game.item.Item;
import game.item.ItemRegistry;
import game.mount.Mount;

public class GameInitializer {

    public static void registerAllItems() {
        // Register all items
        Item healingItem = new Item("治疗药水", "healing", 50, 10, 1);
        Item attackItem = new Item("攻击药水", "attack", 15, 20, 1);
        Item defenseItem = new Item("防御药水", "defense", 10, 15, 1);
        Item staminaItem = new Item("体力药水", "stamina", 20, 15, 1);
        Item dodgeItem = new Item("闪避药��", "dodge", 5, 10, 1);
        Item intelligenceItem = new Item("智力药水", "intelligence", 5, 10, 1);
        Item damageItem = new Item("伤害药水", "damage", 30, 15, 1);
        Item strongDamageItem = new Item("强效伤害药水", "damage", 60, 45, 1);
        Item celestialBlastPotion = new Item("天体爆炸药水", "damage", 100, 60, 1);
        Item invisibilityPotion = new Item("隐身药水", "invisibility", 0, 50, 1);
        ItemRegistry.registerItem(healingItem);
        ItemRegistry.registerItem(attackItem);
        ItemRegistry.registerItem(defenseItem);
        ItemRegistry.registerItem(staminaItem);
        ItemRegistry.registerItem(dodgeItem);
        ItemRegistry.registerItem(intelligenceItem);
        ItemRegistry.registerItem(damageItem);
        ItemRegistry.registerItem(strongDamageItem);
        ItemRegistry.registerItem(celestialBlastPotion);
        ItemRegistry.registerItem(invisibilityPotion);

        // Register all equipment
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

        // Register all mounts
        Mount shadowPanther = new Mount("影豹", 100, 200, 25, 20, 15, 1000);
        Mount pegasus = new Mount("天马", 120, 220, 30, 20, 15, 1200);
        Mount griffin = new Mount("狮鹫", 140, 240, 35, 25, 20, 1400);
        Mount unicorn = new Mount("独角兽", 160, 260, 40, 30, 25, 1600);
        Mount phoenix = new Mount("凤凰", 180, 280, 45, 35, 30, 1800);
        Mount celestialDragon = new Mount("天龙", 200, 300, 50, 40, 35, 2000);
        Mount dragonKing = new Mount("龙王", 220, 320, 55, 45, 40, 2200);
        Mount kirin = new Mount("麒麟", 240, 340, 60, 50, 45, 2400);
        Mount celestialTiger = new Mount("天虎", 260, 360, 65, 55, 50, 2600);
        Mount goldenPhoenix = new Mount("金凤凰", 280, 380, 70, 60, 55, 2800);
        Mount silverDragon = new Mount("银龙", 300, 400, 75, 65, 60, 3000);

        ItemRegistry.registerMount(shadowPanther);
        ItemRegistry.registerMount(pegasus);
        ItemRegistry.registerMount(griffin);
        ItemRegistry.registerMount(unicorn);
        ItemRegistry.registerMount(phoenix);
        ItemRegistry.registerMount(celestialDragon);
        ItemRegistry.registerMount(dragonKing);
        ItemRegistry.registerMount(kirin);
        ItemRegistry.registerMount(celestialTiger);
        ItemRegistry.registerMount(goldenPhoenix);
        ItemRegistry.registerMount(silverDragon);
    }
}