package game.shop;

import game.equipment.Equipment;
import game.item.Item;
import game.item.ItemDisplay;
import game.item.ItemRegistry;
import game.mount.Mount;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShopDisplay {
    public static List<Item> items;
    public static List<Equipment> weapons;
    public static List<Equipment> armors;
    public static List<Equipment> boots;
    public static List<Mount> mounts;

    static {
        items = new ArrayList<>(ItemRegistry.getAllItems().values());
        items.sort(Comparator.comparingInt(Item::getPrice));

        List<Equipment> equipments = new ArrayList<>(ItemRegistry.getAllEquipments().values());
        weapons = equipments.stream()
                .filter(e -> e.getType().equals("Weapon"))
                .sorted(Comparator.comparingInt(Equipment::getPrice))
                .collect(Collectors.toList());

        armors = equipments.stream()
                .filter(e -> e.getType().equals("Armor"))
                .sorted(Comparator.comparingInt(Equipment::getPrice))
                .collect(Collectors.toList());

        boots = equipments.stream()
                .filter(e -> e.getType().equals("Boots"))
                .sorted(Comparator.comparingInt(Equipment::getPrice))
                .collect(Collectors.toList());

        mounts = new ArrayList<>(ItemRegistry.getAllMounts().values());
        mounts.sort(Comparator.comparingInt(Mount::getPrice));
    }

    public static void displayItems() {
        int index = 1;
        System.out.println("可购买的道具：");
        for (Item item : items) {
            String effectDescription = ItemDisplay.getEffectDescription(item);
            System.out.println(index++ + ". " + item.getName() + " - 效果：" + effectDescription + " - 价格：" + item.getPrice() + "金币");
        }
    }

    public static void displayWeapons() {
        int index = 1;
        System.out.println("可购买的武器：");
        for (Equipment weapon : weapons) {
            String effectDescription = ItemDisplay.getEquipmentEffectDescription(weapon);
            System.out.println(index++ + ". " + weapon.getName() + " - 效果：" + effectDescription + " - 价格：" + weapon.getPrice() + "金币");
        }
    }

    public static void displayArmors() {
        int index = 1;
        System.out.println("可购买的护甲：");
        for (Equipment armor : armors) {
            String effectDescription = ItemDisplay.getEquipmentEffectDescription(armor);
            System.out.println(index++ + ". " + armor.getName() + " - 效果：" + effectDescription + " - 价格：" + armor.getPrice() + "金币");
        }
    }

    public static void displayBoots() {
        int index = 1;
        System.out.println("可购买的鞋子：");
        for (Equipment boot : boots) {
            String effectDescription = ItemDisplay.getEquipmentEffectDescription(boot);
            System.out.println(index++ + ". " + boot.getName() + " - 效果：" + effectDescription + " - 价格：" + boot.getPrice() + "金币");
        }
    }

    public static void displayMounts() {
        int index = 1;
        System.out.println("可购买的坐骑：");
        for (Mount mount : mounts) {
            System.out.println(index++ + ". " + mount.getName() + " - 价格：" + mount.getPrice() + "金币");
        }
    }
}