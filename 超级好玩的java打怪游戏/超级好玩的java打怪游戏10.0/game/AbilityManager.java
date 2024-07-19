package game;

import java.util.Random;

public class AbilityManager  {
    public static void useAbility(Monster monster, Player player) {
        Random random = new Random();
        int chance = random.nextInt(100) + 1; // 生成一个1到100之间的随机数
        int threshold = 75; // 75%尝试使用能力的机会

        if (chance <= threshold) {
            if (monster.abilityCooldown <= 0) {
                int staminaCost = 0; // 初始化技能的体力消耗

                // 对玩家应用技能效果
                switch (monster.abilityName) {
                    case "毒素":
                        AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\毒素.wav", false, false, -5);
                        staminaCost = 5; // 假设毒素技能的体力消耗为5
                        if (monster.stamina >= staminaCost) {
                            player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.POISONED, 3, 10)); // 中毒3回合，每回合扣10血
                            System.out.println(monster.name + " 使用了毒素，对玩家造成了中毒效果。");
                            monster.stamina -= staminaCost;
                            monster.abilityCooldown = monster.initialAbilityCooldown; // 重置技能冷却
                        }
                        break;
                    case "熔岩爆发":
                        AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\熔岩爆发.wav", false, false, -5);
                        int damageLavaBurst = 30 + monster.level * 5; // 示例伤害计算
                        int staminaCostLavaBurst = 6; // 设置熔岩爆发技能的体力消耗
                        if (monster.stamina >= staminaCostLavaBurst) {
                            System.out.println(monster.name + " 使用了 " + monster.abilityName + " 对玩家造成了 " + damageLavaBurst + " 点伤害。");
                            player.hp -= damageLavaBurst;
                            if (new Random().nextInt(100) < 80) { // 80%的几率应用BURNING
                                player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.BURNING, 3, 15 * player.level * 2));
                                System.out.println("玩家被点燃了！");
                            }
                            monster.stamina -= staminaCostLavaBurst; // 扣除体力消耗
                        } else {
                            System.out.println(monster.name + " 体力不足，无法使用 " + monster.abilityName + " 技能。");
                        }
                        break;
                    case "地震":
                        AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\地震.wav", false, false, -5);
                        staminaCost = 5; // 设置地震技能的体力消耗
                        int damageEarthquake = 50 + player.level * 8; // 示例伤害计算
                        if (monster.stamina >= staminaCost) {
                            player.hp -= damageEarthquake;
                            System.out.println(monster.name + " 使用了地震，对玩家造成了 " + damageEarthquake + " 点伤害。");

                            if (new Random().nextInt(100) < 90) { // 90%的几率应用SLOWED
                                player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.SLOWED, 4, -30));
                                System.out.println("玩家的速度降低了！");
                            }
                            monster.stamina -= staminaCost; // 扣除体力消耗
                        } else {
                            System.out.println(monster.name + " 体力不足，无法使用 " + monster.abilityName + " 技能。");
                        }
                        break;
                    case "暗影爆发":
                        AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\暗影爆发.wav", false, false, -5);
                        staminaCost = 10; // 设置暗影爆发技能的体力消耗
                        if (monster.stamina >= staminaCost) {
                            int damage = 50 + monster.level * 10; // 暗影爆发造成的伤害
                            System.out.println(monster.name + " 使用了暗影爆发，对玩家造成了 " + damage + " 点伤害。");
                            player.hp -= damage;
                            // 可以在这里添加特殊状态效果，例如恐惧，减少玩家的攻击力或防御力
                            player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.FEAR, 3+monster.level/2, -10)); // 假设效果
                            monster.stamina -= staminaCost;
                        }
                        break;

                    case "冰封禁锢":
                        AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\冰封禁锢.wav", false, false, -5);
                        int staminaCostFrozen = 4; // 设置冰封禁锢技能的体力消耗
                        if (monster.stamina >= staminaCostFrozen) {
                            System.out.println(monster.name + " 使用了 " + monster.abilityName + "，玩家被冻结了一回合。");
                            player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.FROZEN, 1, 23)); // 冰冻1回合，扣除23%生命值
                            // 假设CHILLED效果每回合扣除10%生命值并减少10点躲避值
                            player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.CHILLED, 4, 10)); // CHILLED效果，持续2回合
                            monster.stamina -= staminaCostFrozen; // 扣除体力消耗
                        } else {
                            System.out.println(monster.name + " 体力不足，无法使用 " + monster.abilityName + " 技能。");
                        }
                        break;
                    case "隐身攻击":
                        AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\隐身攻击.wav", false, false, -5);
                        staminaCost = 3; // 设置隐身攻击技能的体力消耗
                        if (monster.stamina >= staminaCost) {
                            monster.dodge += 20; // 攻击后增加20%的躲避率
                            System.out.println(monster.name + " 使用了隐身攻击！躲避率暂时提高。");
                            monster.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "火焰吐息":
                        AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\火焰吐息.wav", false, false, -5);
                        staminaCost = 3; // 设置火焰吐息技能的体力消耗
                        if (monster.stamina >= staminaCost) {
                            player.hp -= monster.intelligence * 2;
                            System.out.println(monster.name + " 使用了火焰吐息！你受到 " + (monster.intelligence * 2) + " 点伤害。");
                            monster.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "石化":
                        staminaCost = 5; // 设置石化技能的体力消耗
                        if (monster.stamina >= staminaCost) {
                            if (!player.isStone) {
                                player.originalAttack = player.attack;
                            }
                            player.attack /= 2;
                            player.isStone = true;
                            System.out.println(monster.name + " 使用了石化！你的攻击力降低 50%。");
                            monster.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "治疗":
                        staminaCost = 5; // 设置治疗技能的体力消耗
                        if (monster.stamina >= staminaCost) {
                            monster.hp += monster.stamina * 0.5;
                            System.out.println(monster.name + " 使用了治疗！恢复了 " + (monster.stamina * 0.5) + " 点生命值。");
                            monster.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "狂暴":
                        AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\狂暴.wav", false, false, -5);
                        staminaCost = 5; // 设置狂暴技能的体力消耗
                        if (monster.stamina >= staminaCost) {
                            monster.attack *= 1.3;
                            System.out.println(monster.name + " 使用了狂暴！攻击力提高了 30%。");
                            monster.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "护盾":
                        staminaCost = 2; // 设置护盾技能的体力消耗
                        if (monster.stamina >= staminaCost) {
                            monster.defense *= 1.5;
                            System.out.println(monster.name + " 使用了护盾！防御力提高了 50%。");
                            monster.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "召唤":
                        staminaCost = 30; // 设置召唤技能的体力消耗
                        if (monster.stamina >= staminaCost) {
                            // 在这里创建一个新的怪物对象并将其添加到游戏场景中
                            System.out.println(monster.name + " 使用了召唤！生成了一个新的怪物。");
                            monster.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "雷霆一击":
                        AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\雷霆一击.wav", false, false, -5);
                        staminaCost = 6; // 设置雷霆一击技能的体力消耗
                        if (monster.stamina >= staminaCost) {
                            int damageThunderStrike = 40 + monster.level * 6; // 示例伤害计算
                            System.out.println(monster.name + " 使用了 " + monster.abilityName + " 对玩家造成了 " + damageThunderStrike + " 点伤害。");
                            player.hp -= damageThunderStrike;
                            // 应用状态效果的示例
                            if (new Random().nextInt(100) < 90) { // 90%的几率应用STUNNED
                                player.addStatusEffect(new StatusEffectManager.StatusEffect(StatusEffectType.STUNNED, 3, 0));
                                System.out.println("玩家被震晕了！");
                            }
                            monster.stamina -= staminaCost; // 扣除体力消耗
                        } else {
                            System.out.println(monster.name + " 体力不足，无法使用 " + monster.abilityName + " 技能。");
                        }
                        break;

                    case "风刃":
                        AudioPlayer.playSound("C:\\Users\\GeekGuru\\Downloads\\Music\\风刃.wav", false, false, -5);
                        staminaCost = 4; // 设置风刃技能的体力消耗
                        if (monster.stamina >= staminaCost) {
                            int damageWindBlade = 20 + monster.level * 4; // 风刃造成的伤害
                            System.out.println(monster.name + " 使用了风刃，对玩家造成了 " + damageWindBlade + " 点伤害。");
                            player.hp -= damageWindBlade;
                            monster.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;
                    case "水疗":
                        staminaCost = 5; // 设置水疗技能的体力消耗
                        if (monster.stamina >= staminaCost) {
                            monster.hp += monster.intelligence * 3;
                            System.out.println(monster.name + " 使用了水疗！恢复了 " + (monster.intelligence * 3) + " 点生命值。");
                            monster.stamina -= staminaCost; // 扣除体力消耗
                        }
                        break;

                    // 在这里添加其他能力
                    }

                if (staminaCost > 0 && monster.stamina < staminaCost) {
                    System.out.println(monster.name + " 体力不足，无法使用 " + monster.abilityName + " 技能。");
                } else {
                    monster.abilityCooldown = monster.initialAbilityCooldown; // 重置冷却时间
                }
            }
        } else {
            System.out.println(monster.name + " 在这个回合选择了不使用任何技能。");
        }

        if (monster.abilityCooldown > 0) {
            monster.abilityCooldown--;
        }
    }
}