package game.item;

import game.equipment.Equipment;
import game.equipment.EquipmentActions;
import game.equipment.EquipmentManager;
import game.player.Player;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ItemDisplay {

    public static void displayInventory(Player player) {
        System.out.println("持有的物品：");
        for (Map.Entry<String, Object> entry : player.getInventory().getInventory().entrySet()) {
            if (entry.getValue() instanceof Item) {
                Item item = (Item) entry.getValue();
                String effectDescription = getEffectDescription(item);
                System.out.println(entry.getKey() + " x" + item.getQuantity() + " - 效果：" + effectDescription);
            }
        }
    }

    public static String getEffectDescription(Item item) {
        switch (item.getType()) {
            case "defense":
                return "提升 " + item.getEffect() + " 点防御力";
            case "stamina":
                return "提升 " + item.getEffect() + " 点体力";
            case "dodge":
                return "提升 " + item.getEffect() + " 点闪避";
            case "intelligence":
                return "提升 " + item.getEffect() + " 点智力";
            case "damage":
                return "对当前怪物造成 " + item.getEffect() + " 点伤害";
            case "healing":
                return "恢复 " + item.getEffect() + " 点生命值";
            case "criticalRate":
                return "提升 " + item.getEffect() + " 点暴击率";
            case "criticalDamage":
                return "提升 " + item.getEffect() + " 点暴击伤害";
            case "hitRate":
                return "提升 " + item.getEffect() + " 点命中率";
            case "blockRate":
                return "提升 " + item.getEffect() + " 点格挡率";
            case "dodgeRate":
                return "提升 " + item.getEffect() + " 点闪避率";
            case "fireMagicLevel":
                return "提升 " + item.getEffect() + " 级火魔法等级";
            case "healMagicLevel":
                return "提升 " + item.getEffect() + " 级治疗魔法等级";
            case "vampirismLevel":
                return "提升 " + item.getEffect() + " 级吸血魔法等级";
            case "piercingAttackDamage":
                return "提升 " + item.getEffect() + " 点穿透攻击伤害";
            case "armorPenetration":
                return "提升 " + item.getEffect() + " 点防御穿透";
            case "damageIncrease":
                return "提升 " + item.getEffect() + " 点额外伤害";
            case "lightningMagicLevel":
                return "提升 " + item.getEffect() + " 级雷电魔法等级";
            case "iceMagicLevel":
                return "提升 " + item.getEffect() + " 级冰霜魔法等级";
            case "randomEffect":
                return "随机效果";
            case "revive":
                return "复活";
            default:
                return "未知效果";
        }
    }

    public static String getEquipmentEffectDescription(Equipment equipment) {
        StringBuilder effectDescription = new StringBuilder();
        if (equipment.attackBonus > 0) effectDescription.append("攻击力提升：").append(equipment.attackBonus).append("\n");
        if (equipment.defenseBonus > 0) effectDescription.append("防御力提升：").append(equipment.defenseBonus).append("\n");
        if (equipment.hpBonus > 0) effectDescription.append("生命值提升：").append(equipment.hpBonus).append("\n");
        if (equipment.intelligenceBonus > 0) effectDescription.append("智力提升：").append(equipment.intelligenceBonus).append("\n");
        if (equipment.staminaBonus > 0) effectDescription.append("体力提升：").append(equipment.staminaBonus).append("\n");
        if (equipment.dodgeBonus > 0) effectDescription.append("闪避提升：").append(equipment.dodgeBonus).append("\n");
        return effectDescription.toString();
    }

    public static void displayItemSelection(Player player, Map<String, ? extends Item> selectedCategory, Scanner scanner, boolean isEquipment) {
        System.out.println("请选择您的物品 (输入0取消)：");
        String[] itemNames = selectedCategory.keySet().toArray(new String[0]);
        for (int i = 0; i < itemNames.length; i++) {
            Item item = (Item) player.getInventory().getInventory().get(itemNames[i]);
            String effectDescription = isEquipment ? getEquipmentEffectDescription((Equipment) item) : getEffectDescription(item);
            System.out.println((i + 1) + ". " + itemNames[i] + " x" + item.getQuantity() + " - 效果：" + effectDescription);
        }

        int itemChoice = scanner.nextInt();
        if (itemChoice == 0) {
            System.out.println("取消使用物品。");
            return;
        }
        if (itemChoice < 1 || itemChoice > itemNames.length) {
            System.out.println("无效的物品选择！");
            return;
        }

        String selectedItemName = itemNames[itemChoice - 1];
        Item selectedItem = (Item) player.getInventory().getInventory().get(selectedItemName);

        if (selectedItem == null) {
            System.out.println("未找到物品：" + selectedItemName + "。请确保物品名称正确并已注册。");
            return;
        }

        if (isEquipment) {
            if (EquipmentActions.isEquipped(player, (Equipment) selectedItem)) {
                System.out.println("你已经装备了 " + selectedItemName + "！");
            } else {
                EquipmentManager.equip(player, (Equipment) selectedItem);
//                System.out.println("你装备了 " + selectedItemName + "！");
            }
        } else {
            System.out.println("请输入要使用的数量：");
            int quantity = scanner.nextInt();
            if (quantity <= 0 || quantity > selectedItem.getQuantity()) {
                System.out.println("无效的数量！");
                return;
            }

            for (int i = 0; i < quantity; i++) {
                ItemUsage.applyItemEffect(player, selectedItem);
            }

            player.getInventory().removeFromInventory(selectedItemName, quantity);
            System.out.println("你使用了 " + quantity + " 个 " + selectedItemName + "！");
        }
    }

    public static void displayItemCategories(Player player, Scanner scanner, Map<String, Item> itemItems, Map<String, Equipment> equipmentItems) {
        System.out.println("请选择物品类别 (输入0取消)：");
        System.out.println("1. 道具类");
        System.out.println("2. 装备类");

        try {
            int categoryChoice = scanner.nextInt();
            if (categoryChoice == 0) {
                System.out.println("取消使用物品。");
                return;
            }

            Map<String, ? extends Item> selectedCategory = null;
            boolean isEquipment = false;
            switch (categoryChoice) {
                case 1:
                    selectedCategory = itemItems;
                    break;
                case 2:
                    selectedCategory = equipmentItems;
                    isEquipment = true;
                    break;
                default:
                    System.out.println("无效的类别选择！");
                    return;
            }

            if (selectedCategory == null || selectedCategory.isEmpty()) {
                System.out.println("该类别下没有物品！");
                return;
            }

            displayItemSelection(player, selectedCategory, scanner, isEquipment);
        } catch (InputMismatchException e) {
            System.out.println("请输入有效的数字！");
            scanner.nextLine();
        }
    }
}