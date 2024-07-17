package game;


import java.util.Random;
public class MonsterFactory {
    // 创建怪物的方法

    public static Monster createMonster(Random random) {
        int monsterType = random.nextInt(10); // 调整以包含黑暗巨龙
        int monsterLevel = random.nextInt(5) + 1;

        switch (monsterType) {
            case 0:
                return createSlime(monsterLevel);
            case 1:
                return createGoblin(monsterLevel);
            case 2:
                return createWerewolf(monsterLevel);
            case 3:
                return createViper(monsterLevel);
            case 4:
                return createFlyingMonster(monsterLevel);
            case 5:
                return createMagicMonster(monsterLevel);
            case 6:
                return createShadowAssassin(monsterLevel);
            case 7:
                return createFlameGiant(monsterLevel); // 新增火焰巨人的情况
            case 8:
                return createFrostMage(monsterLevel); // 新增冰霜法师的情况
            case 9:
                return new DarkDragon(monsterLevel); // 为黑暗巨龙添加情况
            default:
                return null;
        }
    }
    private static Monster createSlime(int level) {
        int hp = 50 + (level - 1) * 10;
        int attack = 10 + (level - 1) * 2;
        int defense = 3 + (level - 1); // 防御力随等级增加
        int intelligence = 5 + (level - 1); // 智力随等级增加
        int stamina = 15 + (level - 1) * 2; // 体力随等级增加
        int dodge = 5 + (level - 1); // 躲避率随等级增加
        String name = "史莱姆";
        int gold = 10 + (level - 1) * 5;
        String abilityName = "治疗";
        int abilityCooldown = 5;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private static Monster createGoblin(int level) {
        int hp = 70 + (level - 1) * 15;
        int attack = 15 + (level - 1) * 3;
        int defense = 7 + (level - 1) * 2; // 防御力随等级增加
        int intelligence = 8 + (level - 1); // 智力随等级增加
        int stamina = 20 + (level - 1) * 3; // 体力随等级增加
        int dodge = 10 + (level - 1) * 2; // 躲避率随等级增加
        String name = "哥布林";
        int gold = 20 + (level - 1) * 10;
        String abilityName = "狂暴";
        int abilityCooldown = 3;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private static Monster createWerewolf(int level) {
        int hp = 100 + (level - 1) * 20;
        int attack = 20 + (level - 1) * 4;
        int defense = 10 + (level - 1) * 3; // 防御力随等级增加
        int intelligence = 5 + (level - 1); // 智力随等级增加
        int stamina = 25 + (level - 1) * 4; // 体力随等级增加
        int dodge = 15 + (level - 1) * 3; // 躲避率随等级增加
        String name = "狼人";
        int gold = 30 + (level - 1) * 15;
        String abilityName = "护盾";
        int abilityCooldown = 5;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private static Monster createViper(int level) {
        int hp = 150 + (level - 1) * 30;
        int attack = 20 + (level - 1) * 5;
        int defense = 15 + (level - 1) * 4; // 防御力随等级增加
        int intelligence = 10 + (level - 1) * 2; // 智力随等级增加
        int stamina = 30 + (level - 1) * 5; // 体力随等级增加
        int dodge = 50 + (level - 1) * 3; // 躲避率随等级增加
        String name = "毒蛇";
        int gold = 40 + (level - 1) * 20;
        String abilityName = "毒素";
        int abilityCooldown = 5;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private static Monster createFlyingMonster(int level) {
        int hp = 180 + (level - 1) * 36;
        int attack = 18 + (level - 1) * 4;
        int defense = 13 + (level - 1) * 4; // 防御力随等级增加
        int intelligence = 12 + (level - 1) * 3; // 智力随等级增加
        int stamina = 22 + (level - 1) * 4; // 体力随等级增加
        int dodge = 68 + (level - 1) * 2; // 躲避率随等级增加
        String name = "飞行怪物";
        int gold = 75 + (level - 1) * 30;
        String abilityName = "火焰吐息";
        int abilityCooldown = 3;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private static Monster createMagicMonster(int level) {
        int hp = 120 + (level - 1) * 24;
        int attack = 25 + (level - 1) * 5;
        int defense = 8 + (level - 1) * 3; // 防御力随等级增加
        int intelligence = 15 + (level - 1) * 4; // 智力随等级增加
        int stamina = 28 + (level - 1) * 5; // 体力随等级增加
        int dodge = 42 + (level - 1) * 4; // 躲避率随等级增加
        String name = "魔法怪物";
        int gold = 35 + (level - 1) * 17;
        String abilityName = "石化";
        int abilityCooldown = 5;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    public static Monster createShadowAssassin(int level) {
        int hp = 80 + (level - 1) * 10; // 生命值较低
        int attack = 30 + (level - 1) * 6; // 攻击力较高
        int defense = 5 + (level - 1) * 2; // 防御力一般
        int intelligence = 8 + (level - 1) * 2; // 智力一般
        int stamina = 20 + (level - 1) * 3; // 体力一般
        int dodge = 40 + (level - 1) * 5; // 高躲避率
        String name = "影子刺客";
        int gold = 50 + (level - 1) * 25; // 掉落金币量
        String abilityName = "隐身攻击";
        int abilityCooldown = 3; // 技能冷却时间
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private static Monster createFlameGiant(int level) {
        int hp = 150 + level * 30;
        int attack = 25 + level * 5;
        int defense = 15 + level * 3;
        int intelligence = 10 + level * 2;
        int stamina = 20 + level * 4;
        int dodge = 10 + level * 2;
        String name = "火焰巨人";
        int gold = 45 + level * 20;
        String abilityName = "熔岩爆发";
        int abilityCooldown = 4;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

    private static Monster createFrostMage(int level) {
        int hp = 120 + level * 25;
        int attack = 15 + level * 3;
        int defense = 12 + level * 2;
        int intelligence = 20 + level * 5;
        int stamina = 30 + level * 3;
        int dodge = 20 + level * 2;
        String name = "霜寒巫师";
        int gold = 50 + level * 25;
        String abilityName = "冰封禁锢";
        int abilityCooldown = 5;
        return new Monster(level, hp, attack, defense, intelligence, stamina, dodge, name, gold, abilityName, abilityCooldown);
    }

}
