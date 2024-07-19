// GameInitializer.java
package game;

public class GameInitializer {

    public static void registerAllItems() {
        // 定义并注册所有道具
        Item healingItem = new Item("治疗药水", "healing", 50, 10, 1);
        Item attackItem = new Item("攻击药水", "attack", 15, 20, 1);
        Item defenseItem = new Item("防御药水", "defense", 10, 15, 1);
        Item staminaItem = new Item("体力药水", "stamina", 20, 15, 1);
        Item dodgeItem = new Item("闪避药水", "dodge", 5, 10, 1);
        Item intelligenceItem = new Item("智力药水", "intelligence", 5, 10, 1);
        Item damageItem = new Item("伤害药水", "damage", 30, 15, 1);
        Item strongDamageItem = new Item("强效伤害药水", "damage", 60, 45, 1);
        Item celestialBlastPotion = new Item("天体爆炸药水", "damage", 100, 60, 1);
        ItemRegistry.registerItem(healingItem);
        ItemRegistry.registerItem(attackItem);
        ItemRegistry.registerItem(defenseItem);
        ItemRegistry.registerItem(staminaItem);
        ItemRegistry.registerItem(dodgeItem);
        ItemRegistry.registerItem(intelligenceItem);
        ItemRegistry.registerItem(damageItem);
        ItemRegistry.registerItem(strongDamageItem);
        ItemRegistry.registerItem(celestialBlastPotion);

        // 定义并注册所有装备
        Equipment woodSword = new Equipment("木剑", "Weapon", 10, 0, 0, 0, 0, 0, 10, 1);
        Equipment leatherArmor = new Equipment("皮甲", "Armor", 0, 5, 5, 0, 0, 0, 10, 1);
        Equipment ironSword = new Equipment("铁剑", "Weapon", 20, 0, 0, 0, 0, 0, 30, 1);
        Equipment chainmail = new Equipment("锁子甲", "Armor", 0, 10, 10, 0, 0, 0, 50, 1);
        Equipment dagger = new Equipment("匕首", "Weapon", 15, 0, 0, 0, 0, 5, 20, 1);
        Equipment leatherBoots = new Equipment("皮靴", "Boots", 0, 3, 0, 0, 5, 5, 15, 1);

        // 使用新的 registerEquipment 方法注册装备
        ItemRegistry.registerEquipment(woodSword);
        ItemRegistry.registerEquipment(leatherArmor);
        ItemRegistry.registerEquipment(ironSword);
        ItemRegistry.registerEquipment(chainmail);
        ItemRegistry.registerEquipment(dagger);
        ItemRegistry.registerEquipment(leatherBoots);

        // 定义并注册所有坐骑
        Mount celestialDragon = new Mount("天龙", 100, 200, 30, 25, 25, 1000);
        Mount phoenix = new Mount("凤凰", 120, 180, 35, 30, 20, 1200);
        Mount unicorn = new Mount("独角兽", 110, 190, 40, 20, 30, 1100);
        Mount griffin = new Mount("狮鹫", 130, 170, 25, 35, 25, 1300);
        Mount pegasus = new Mount("天马", 140, 160, 45, 20, 40, 1400);
        Mount dragonKing = new Mount("龙王", 150, 210, 50, 40, 35, 1500);
        Mount kirin = new Mount("麒麟", 160, 220, 55, 45, 30, 1600);
        Mount celestialTiger = new Mount("天虎", 170, 230, 60, 50, 25, 1700);

        // 使用新的 registerMount 方法注册坐骑
        ItemRegistry.registerMount(celestialDragon);
        ItemRegistry.registerMount(phoenix);
        ItemRegistry.registerMount(unicorn);
        ItemRegistry.registerMount(griffin);
        ItemRegistry.registerMount(pegasus);
        ItemRegistry.registerMount(dragonKing);
        ItemRegistry.registerMount(kirin);
        ItemRegistry.registerMount(celestialTiger);
    }
}