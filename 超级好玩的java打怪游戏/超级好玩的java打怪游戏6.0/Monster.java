package game;

import java.util.Random;

public class Monster {
    int level = 1;
    int hp;
    int attack;
    int defense;  // 防御力
    int intelligence;  // 智力
    int stamina;  // 体力
    int dodge;  // 躲避
    String name;
    int gold; // 金币

    String abilityName; // 怪物技能名称
    int abilityCooldown; // 技能冷却回合数

    int initialAbilityCooldown; // 技能初始冷却回合数


    Monster(int level, int hp, int attack, int defense, int intelligence, int stamina, int dodge, String name, int gold, String abilityName, int abilityCooldown) {
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.intelligence = intelligence;
        this.stamina = stamina;
        this.dodge = dodge;
        this.name = name;
        this.gold = gold;
        this.abilityName = abilityName;
        this.abilityCooldown = abilityCooldown;
        this.initialAbilityCooldown = abilityCooldown; // 初始化初始冷却时间
    }

    // 在Monster类中
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0; // 确保生命值不会低于0
    }

    public void useAbility(Player player) {
        Random random = new Random();
        int chance = random.nextInt(100) + 1; // 生成一个1到100之间的随机数
        int threshold = 60; // 60%尝试使用能力的机会
        if (chance <= threshold) {
            if (abilityCooldown <= 0) {
                int staminaCost = 0; // 初始化技能的体力消耗
                // 对玩家应用技能效果
                switch (abilityName) {
                    case "毒素":
                        staminaCost = 5; // 假设毒素技能的体力消耗为5
                        if (this.stamina >= staminaCost) {
                            player.addStatusEffect(new StatusEffect(StatusEffectType.POISONED, 3, 10)); // 中毒3回合，每回合扣10血
                            System.out.println(this.name + " 使用了毒素，对玩家造成了中毒效果。");
                            this.stamina -= staminaCost;
                            this.abilityCooldown = this.initialAbilityCooldown; // 重置技能冷却
                        }
                        break;
                    case "熔岩爆发":
                        int damageLavaBurst = 30 + this.level * 5; // 示例伤害计算
                        int staminaCostLavaBurst = 6; // 设置熔岩爆发技能的体力消耗
                        if (this.stamina >= staminaCostLavaBurst) {
                            System.out.println(this.name + " 使用了 " + this.abilityName + " 对玩家造成了 " + damageLavaBurst + " 点伤害。");
                            player.hp -= damageLavaBurst;
                            // 应用状态效果的示例
                            if (new Random().nextInt(100) < 80) { // 80%的几率应用BURNING
                                player.addStatusEffect(new StatusEffect(StatusEffectType.BURNING, 3, 15 * player.level * 2));
                                System.out.println("玩家被点燃了！");
                            }
                            this.stamina -= staminaCostLavaBurst; // 扣除体力消耗
                        } else {
                            System.out.println(this.name + " 体力不足，无法使用 " + this.abilityName + " 技能。");
                        }
                        break;
                    case "暗影爆发":
                        staminaCost = 10; // 设置暗影爆发技能的体力消耗
                        if (this.stamina >= staminaCost) {
                            int damage = 50 + this.level * 10; // 暗影爆发造成的伤害
                            System.out.println(this.name + " 使用了暗影爆发，对玩家造成了 " + damage + " 点伤害。");
                            player.hp -= damage;
                            // 可以在这里添加特殊状态效果，例如恐惧，减少玩家的攻击力或防御力
                            player.addStatusEffect(new StatusEffect(StatusEffectType.FEAR, 2, -10)); // 假设效果
                            this.stamina -= staminaCost;
                        }
                        break;
                    case "冰封禁锢":
                        int staminaCostFrozen = 4; // 设置冰封禁锢技能的体力消耗
                        if (this.stamina >= staminaCostFrozen) {
                            System.out.println(this.name + " 使用了 " + this.abilityName + "，玩家被冻结了一回合。");
                            player.addStatusEffect(new StatusEffect(StatusEffectType.FROZEN, 1, 23)); // 冰冻1回合，扣除23%生命值
                            // 假设CHILLED效果每回合扣除10%生命值并减少10点躲避值
                            player.addStatusEffect(new StatusEffect(StatusEffectType.CHILLED, 2, 10)); // CHILLED效果，持续2回合
                            this.stamina -= staminaCostFrozen; // 扣除体力消耗
                        } else {
                            System.out.println(this.name + " 体力不足，无法使用 " + this.abilityName + " 技能。");
                        }
                        break;
                    case "隐身攻击":
                        staminaCost = 3; // 设置隐身攻击技能的体力消耗
                        if (this.stamina >= staminaCost) {
                            this.dodge += 20; // 攻击后增加20%的躲避率
                            System.out.println(this.name + " 使用了隐身攻击！躲避率暂时提高。");
                            this.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "火焰吐息":
                        staminaCost = 3; // 设置火焰吐息技能的体力消耗
                        if (this.stamina >= staminaCost) {
                            player.hp -= this.intelligence * 2;
                            System.out.println(this.name + " 使用了火焰吐息！你受到 " + (this.intelligence * 2) + " 点伤害。");
                            this.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "石化":
                        staminaCost = 5; // 设置石化技能的体力消耗
                        if (this.stamina >= staminaCost) {
                            if (!player.isStone) {
                                player.originalAttack = player.attack;
                            }
                            player.attack /= 2;
                            player.isStone = true;
                            System.out.println(this.name + " 使用了石化！你的攻击力降低 50%。");
                            this.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "治疗":
                        staminaCost = 5; // 设置治疗技能的体力消耗
                        if (this.stamina >= staminaCost) {
                            this.hp += this.stamina * 0.5;
                            System.out.println(this.name + " 使用了治疗！恢复了 " + (this.stamina * 0.5) + " 点生命值。");
                            this.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "狂暴":
                        staminaCost = 5; // 设置狂暴技能的体力消耗
                        if (this.stamina >= staminaCost) {
                            this.attack *= 1.3;
                            System.out.println(this.name + " 使用了狂暴！攻击力提高了 30%。");
                            this.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "护盾":
                        staminaCost = 2; // 设置护盾技能的体力消耗
                        if (this.stamina >= staminaCost) {
                            this.defense *= 1.5;
                            System.out.println(this.name + " 使用了护盾！防御力提高了 50%。");
                            this.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "召唤":
                        staminaCost = 30; // 设置召唤技能的体力消耗
                        if (this.stamina >= staminaCost) {
                            // 在这里创建一个新的怪物对象并将其添加到游戏场景中
                            System.out.println(this.name + " 使用了召唤！生成了一个新的怪物。");
                            this.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;


                    // 在这里添加其他能力
                }
                if (staminaCost > 0 && this.stamina < staminaCost) {
                    System.out.println(this.name + " 体力不足，无法使用 " + abilityName + " 技能。");
                } else {
                    abilityCooldown = initialAbilityCooldown; // 重置冷却时间
                }
            }
        } else {
            // 怪物决定不使用技能时的逻辑
            System.out.println(this.name + " 在这个回合选择了不使用任何技能。");
        }

        // 如果冷却时间大于0，在回合结束时总是减少冷却时间
        if (abilityCooldown > 0) {
            abilityCooldown--;
        }
    }


    // 攻击方法
    // 在 Monster 类中，修改 attack 方法
    public void attack(Player player) {
        Random random = new Random();
        if (random.nextInt(100) < player.dodge) {
            System.out.println("你躲避了 " + this.name + " 的攻击！");
        } else {
            // 暴击判定
            if (random.nextInt(100) < this.dodge) { // 暴击率
                int damage = Math.max((this.attack - player.defense) * 2, 0); // 确保伤害不为负数
                player.hp -= damage;
                System.out.println(this.name + " 发动了暴击！攻击了你，造成了 " + damage + " 点伤害！");
            } else {
                int damage = Math.max(this.attack - player.defense, 0); // 确保伤害不为负数
                player.hp -= damage;
                if (damage > 0) {
                    System.out.println(this.name + " 攻击了你，造成了 " + damage + " 点伤害！");
                } else {
                    System.out.println(this.name + " 的攻击被你防御住了！");
                }
            }
        }
    }
}
