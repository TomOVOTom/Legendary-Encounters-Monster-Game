// Player.java
package game;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    public static int originalAttack;
    int hp;
    int attack;
    int defense;  // 防御力
    int intelligence;  // 智力
    int stamina;  // 体力
    int dodge;  // 躲避
    int level; // 等级
    int exp; // 经验值
    int skillPoints; // 技能点
    public int gold; // 金币

    String name; // 玩家名称

    // ... 其他属性 ...
    int fireMagicLevel; // 火焰魔法等级
    int healMagicLevel; // 治疗魔法等级
    int vampirismLevel; // 吸血术等级
    int lightningMagicLevel; // 闪电魔法等级
    int iceMagicLevel; // 冰冻魔法等级

    int fireMagicCooldown = 0; // 火焰魔法冷却时间
    int healMagicCooldown = 0; // 治疗魔法冷却时间
    int lightningMagicCooldown = 0; // 闪电魔法冷却时间
    int iceMagicCooldown = 0; // 冰冻魔法冷却时间

    Equipment equippedWeapon; // 装备的武器
    Equipment equippedArmor; // 装备的护甲
    Equipment equippedBoots; // 装备的鞋子

    int comboCounter; // 连击计数器
    boolean isStone = false; // 是否被石化
    private boolean canAttack = true;

    // 将背包存储方式更改为存储物品名称及其数量
    Map<String, Object> inventory = new HashMap<>();
    List<StatusEffectManager.StatusEffect> statusEffects = new ArrayList<>();
    Mount equippedMount;

    // 添加方法来设置和获取这个状态
    public boolean canAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    // 添加状态效果
    public void addStatusEffect(StatusEffectManager.StatusEffect effect) {
        this.statusEffects.add(effect);
    }

    // 更新所有状态效果
    public void updateStatusEffects() {
        StatusEffectManager.updateStatusEffects(this);
    }

    // 添加休息方法
    public void rest() {
        int restoredHp = 50; // 恢复的生命值
        this.hp += restoredHp;
        System.out.println("你选择休息，恢复了 " + restoredHp + " 点生命值！");
    }

    public void equipMount(Mount mount) {
        if (equippedMount != null) {
            System.out.println("你已经装备了 " + equippedMount.name + "！");
            return;
        }
        this.equippedMount = mount;
        System.out.println("你装备了 " + mount.name + "！");
        applyMountBonuses(mount);
        displayMountAttributeBonuses(mount);
    }

    public void displayMountAttributeBonuses(Mount mount) {
        System.out.println("坐骑属性提升：");
        if (mount.dodgeBonus > 0) System.out.println("躲避值提升：" + mount.dodgeBonus);
        if (mount.attackBonus > 0) System.out.println("攻击力提升：" + mount.attackBonus); // 显示攻击力提升
        if (mount.defenseBonus > 0) System.out.println("防御力提升：" + mount.defenseBonus); // 显示防御力提升
    }

    public void unequipMount() {
        if (equippedMount != null) {
            System.out.println("你卸下了 " + equippedMount.name + "！");
            removeMountBonuses(equippedMount);
            equippedMount = null;
        } else {
            System.out.println("你没有装备任何坐骑！");
        }
    }

    private void applyMountBonuses(Mount mount) {
        this.dodge += mount.dodgeBonus;
        this.attack += mount.attackBonus;
        this.defense += mount.defenseBonus;
    }

    private void removeMountBonuses(Mount mount) {
        this.dodge -= mount.dodgeBonus;
        this.attack -= mount.attackBonus;
        this.defense -= mount.defenseBonus;
    }

    public void rideMount() {
        if (equippedMount != null) {
            System.out.println("你骑上了 " + equippedMount.name + "！");
        } else {
            System.out.println("你没有装备任何坐骑！");
        }
    }

    public void restMount() {
        if (equippedMount != null) {
            System.out.println(equippedMount.name + " 正在休息！");
        } else {
            System.out.println("你没有装备任何坐骑！");
        }
    }

    public void restoreHealth(int speed) {
        int restoredHp = speed; // 根据速度恢复血量
        this.hp += restoredHp;
        System.out.println("骑乘坐骑恢复了 " + restoredHp + " 点生命值！");
    }

    Player(String name, int hp, int attack, int defense, int intelligence, int stamina, int dodge) {
        this.name = name; // 初始化玩家名称
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.intelligence = intelligence;
        this.stamina = stamina;
        this.dodge = dodge;
        this.level = 1;
        this.exp = 0;
        this.skillPoints = 5;
        this.gold = 150;
        // ... 初始化其他属性 ...
        this.fireMagicLevel = 0;
        this.healMagicLevel = 0;
        this.vampirismLevel = 0; // 初始化吸血术等级
        this.lightningMagicLevel = 0; // 初始化闪电魔法等级
        this.iceMagicLevel = 0; // 初始化冰冻魔法等级

        final int MAX_LEVEL = 10; // 设置最高等级为10

        // 初始化背包，为每种物品设置初始数量
        inventory.put("治疗药水", new Item("治疗药水", "healing", 50, 10, 15));
        inventory.put("体力药水", new Item("体力药水", "stamina", 20, 5, 1));
        inventory.put("防御药水", new Item("防御药水", "defense", 10, 5, 1));
        inventory.put("伤害药水", new Item("伤害药水", "damage", 30, 15, 1));
        inventory.put("强效伤害药水", new Item("强效伤害药水", "damage", 50, 20, 11));
        inventory.put("天体爆炸药水", new Item("天体爆炸药水", "damage", 100, 50, 11));
    }

    // 修改方法以向背包中添加物品
    public void addToInventory(Item item) {
        if (inventory.containsKey(item.getName())) {
            ((Item) inventory.get(item.getName())).increaseQuantity(item.getQuantity());
        } else {
            inventory.put(item.getName(), item);
        }
    }

    public void addToInventory(Equipment equipment) {
        inventory.put(equipment.name, equipment);
    }

    public void addToInventory(Mount mount) {
        inventory.put(mount.name, mount);
    }

    // 修改方法以从背包中移除物品
    public void removeFromInventory(String itemName, int quantity) {
        if (inventory.containsKey(itemName)) {
            Item item = (Item) inventory.get(itemName);
            item.decreaseQuantity(quantity);
            if (item.getQuantity() <= 0) {
                inventory.remove(itemName);
            }
        }
    }

    public void removeFromInventory(String mountName) {
        if (inventory.containsKey(mountName)) {
            inventory.remove(mountName);
        }
    }

    // 装备物品
    public void displayAttributeBonuses(Equipment equipment) {
        System.out.println("装备属性提升：");
        if (equipment.attackBonus > 0) System.out.println("攻击力提升：" + equipment.attackBonus);
        if (equipment.defenseBonus > 0) System.out.println("防御力提升：" + equipment.defenseBonus);
        if (equipment.hpBonus > 0) System.out.println("生命值提升：" + equipment.hpBonus);
        if (equipment.intelligenceBonus > 0) System.out.println("智力提升：" + equipment.intelligenceBonus);
        if (equipment.staminaBonus > 0) System.out.println("体力提升：" + equipment.staminaBonus);
        if (equipment.dodgeBonus > 0) System.out.println("闪避提升：" + equipment.dodgeBonus);
    }

    public static final int MAX_LEVEL = 10; // 设置最高等级为10

    public void gainExp(int exp) {
        this.exp += exp;
        // 修改升级条件，使所需经验值随等级增加而增加
        int requiredExp = (int) Math.pow(this.level * 100, 1.2); // 升级所需经验值随等级指数增长
        if (this.exp >= requiredExp && this.level < MAX_LEVEL) {
            this.level++;
            this.exp -= requiredExp;
            this.skillPoints += 5;
            System.out.println("恭喜你升级了！当前等级：" + this.level);
        }
    }

    // Player 类中的 attack 方法
    public void attack(Monster monster) {
        Random random = new Random();
        // 判断怪物是否躲避攻击
        if (random.nextInt(100) < monster.dodge) {
            System.out.println(monster.name + " 躲避了你的攻击！");
            this.comboCounter = 0; // 怪物躲避攻击时，连击计数器重置
        } else {
            int baseDamage; // 基础��害值
            // 判断是否触发暴击
            boolean isCriticalHit = random.nextInt(100) < this.dodge;
            if (isCriticalHit) {
                // 暴击伤害计算
                // 确保基础伤害不为负
                baseDamage = Math.max((this.attack - monster.defense) * 2, 0);
                System.out.println("你发动了暴击！攻击了 " + monster.name + "，造成了 " + baseDamage + " 点伤害！");
            } else {
                // 普通攻击伤害计算
                baseDamage = Math.max(this.attack - monster.defense, 0);
                if (baseDamage > 0) {
                    System.out.println("你攻击了 " + monster.name + "，造成了 " + baseDamage + " 点伤害！");
                } else {
                    System.out.println("你的攻击被 " + monster.name + " 防御住了！");
                    this.comboCounter = 0; // 攻击被完全防御时，连击计数器重置
                    return;
                }
                // 停止播放攻击音效
//                AudioPlayer.stopEffect();
            }

            // 如果连击计数器大于0，则应用连击伤害加成
            double comboMultiplier = 1 + this.comboCounter * 0.4;
            int totalDamage = (int) (baseDamage * comboMultiplier);
            totalDamage = Math.max(totalDamage, 0); // 确保总伤害不为负数

            if (this.comboCounter > 0) {
                System.out.println("连击！造成额外伤害：" + (totalDamage - baseDamage));
            }

            monster.hp -= totalDamage; // 应用伤害到怪物
            this.comboCounter++; // 成功攻击后，连击计数器增加

            // 如果吸血等级大于0，且体力值大于0，且总伤害大于0，则进行吸血
            if (this.vampirismLevel > 0 && this.stamina > 0 && totalDamage > 0) {
                int healAmount = (int) (totalDamage * this.vampirismLevel * 0.1);
                healAmount = Math.max(healAmount, 0); // 确保吸血量不为负数

                this.hp += healAmount; // 吸血增加玩家生命值
                this.stamina -= 5; // 吸血消耗体力
                System.out.println("你吸取了 " + healAmount + " 点生命值！ 体力消耗了 5 点。");
            }
        }
    }
}