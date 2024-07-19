package game;

import java.util.*;

public class Pet implements java.io.Serializable {
    String name;
    int hp;
    int attack;
    int defense;
    int intelligence;
    int stamina;
    int dodge;
    int level;
    int exp;
    int combatPower;
    List<String> skills;

    public Pet(String name, int hp, int attack, int defense, int intelligence, int stamina, int dodge) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.intelligence = intelligence;
        this.stamina = stamina;
        this.dodge = dodge;
        this.level = 1;
        this.exp = 0;
        this.skills = new ArrayList<>();
        initializeSkills();
        calculateCombatPower();
    }

    private void initializeSkills() {
        skills.add("基础攻击");
        // Add more skills as needed
    }

    public void attack(Monster monster) {
        Random random = new Random();
        if (random.nextInt(150) < monster.dodge) {
            System.out.println(monster.name + " 躲避了你的宠物 " + name + " 的攻击！");
        } else {
            int baseDamage = Math.max(attack - monster.defense, 0);
            if (baseDamage > 0) {
                System.out.println("你的宠物 " + name + " 攻击了 " + monster.name + "，造成了 " + baseDamage + " 点伤害！");
                monster.hp -= baseDamage;
            } else {
                System.out.println("你的宠物 " + name + " 的攻击被 " + monster.name + " 防御住了！");
            }
        }
    }

    public void gainExp(int exp) {
        this.exp += exp;
        int requiredExp = (int) Math.pow(this.level * 100, 1.2);
        if (this.exp >= requiredExp) {
            this.level++;
            this.exp -= requiredExp;
            System.out.println("你的宠物 " + name + " 升级了！当前等级：" + this.level);
            // Increase pet attributes on level up
            this.hp += 10;
            this.attack += 2;
            this.defense += 2;
            this.intelligence += 2;
            this.stamina += 5;
            this.dodge += 1;
            calculateCombatPower();
        }
    }

    public void calculateCombatPower() {
        this.combatPower = (int) (attack * 0.7 + defense * 0.3 + intelligence * 0.4 + stamina * 0.2 + dodge * 0.7);
        // Add additional calculations based on skills if needed
    }

    public void displayAttributes() {
        System.out.println("宠物名称: " + name);
        System.out.println("生命值: " + hp);
        System.out.println("攻击力: " + attack);
        System.out.println("防御力: " + defense);
        System.out.println("智力: " + intelligence);
        System.out.println("体力: " + stamina);
        System.out.println("躲避: " + dodge);
        System.out.println("等级: " + level);
        System.out.println("经验值: " + exp);
        System.out.println("战斗力: " + combatPower);
        System.out.println("技能: " + String.join(", ", skills));
    }

    public static void managePets(Player player, Scanner scanner) {
        while (true) {
            System.out.println("宠物管理：1. 查看宠物 2. 装备宠物 3. 自动装备 4. 卸下宠物 5. 复活宠物 6. 返回");
            int petChoice = scanner.nextInt();
            switch (petChoice) {
                case 1:
                    listPets(player);
                    break;
                case 2:
                    System.out.println("请���入要装备的宠物名称：");
                    String petName = scanner.next();
                    Pet petToEquip = player.pets.stream().filter(p -> p.name.equals(petName)).findFirst().orElse(null);
                    if (petToEquip != null) {
                        equipPet(player, petToEquip);
                    } else {
                        System.out.println("未找到该名称的宠物。");
                    }
                    break;
                case 3:
                    autoEquipPet(player);
                    break;
                case 4:
                    unequipPet(player);
                    break;
                case 5:
                    revivePets(player, scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("无效的选择！");
            }
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
                int reviveCost = pet.combatPower;
                System.out.println("复活宠物 " + pet.name + " 需要 " + reviveCost + " 金币。是否复活？(y/n)");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("y") && player.gold >= reviveCost) {
                    player.gold -= reviveCost;
                    pet.hp = pet.level * 10; // 恢复一定的生命值
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