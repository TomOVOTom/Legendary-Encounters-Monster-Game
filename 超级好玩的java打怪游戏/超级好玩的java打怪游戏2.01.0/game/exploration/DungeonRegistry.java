// File: src/game/exploration/DungeonRegistry.java
package game.exploration;

import game.equipment.Equipment;
import game.monster.Monster;

import java.util.Arrays;
import java.util.List;

public class DungeonRegistry {
    public static final List<Dungeon> DUNGEONS = Arrays.asList(
            new Dungeon("黑暗洞穴", 2, "一个充满危险生物的黑暗洞穴，传说中这里曾是一个古老的祭坛，埋藏着无尽的秘密。许多冒险者在此失踪，只有最勇敢的人才能揭开它的真相。", Arrays.asList(
                    new Monster(1, 100, 20, 10, 5, 15, 5, "洞穴蝙蝠", 10, "毒素", 3),
                    new Monster(2, 150, 25, 15, 10, 20, 10, "洞穴蜘蛛", 20, "蛛网", 4)
            ), new Equipment("神秘宝箱", "Chest", 0, 0, 0, 0, 0, 0, 100, 1)),
            new Dungeon("火焰山", 4, "炽热的火焰山，只有勇敢者才能挑战。传说中，这里是火焰巨人的领地，守护着远古的火焰之剑。每年都有无数勇士前来挑战，但大多数都未能生还。", Arrays.asList(
                    new Monster(3, 200, 40, 20, 10, 30, 10, "火焰巨人", 50, "熔岩爆发", 4),
                    new Monster(4, 250, 45, 25, 15, 35, 15, "火焰蜥蜴", 60, "火焰喷射", 5)
            ), new Equipment("火焰之剑", "Weapon", 50, 0, 0, 0, 0, 0, 100, 1)),
            new Dungeon("龙之巢穴", 5, "传说中的龙之巢穴，充满了无尽的宝藏和危险。只有最勇敢的勇士才能击败这里的龙，获得龙之宝藏。每一个进入这里的人都必须面对龙的愤怒和火焰。", Arrays.asList(
                    new Monster(5, 300, 60, 30, 15, 45, 15, "龙", 100, "龙息", 5),
                    new Monster(6, 350, 65, 35, 20, 50, 20, "龙之守卫", 120, "龙爪", 6)
            ), new Equipment("龙之宝藏", "Chest", 0, 0, 0, 0, 0, 0, 200, 1)),
            new Dungeon("幽灵森林", 3, "一个被幽灵和亡灵占据的神秘森林。传说中，这里曾是一个繁荣的村庄，如今只剩下幽灵在此徘徊。每当夜幕降临，幽灵的哀嚎声便会响彻整个森林。", Arrays.asList(
                    new Monster(2, 120, 30, 15, 10, 25, 10, "幽灵", 30, "灵魂攻击", 4),
                    new Monster(3, 180, 35, 20, 15, 30, 15, "亡灵战士", 40, "亡灵之刃", 5)
            ), new Equipment("幽灵之戒", "Ring", 0, 0, 0, 0, 0, 0, 50, 1)),
            new Dungeon("冰雪洞窟", 4, "一个寒冷的冰雪洞窟，充满了冰霜怪物。传说中，这里隐藏着冰霜巨人的秘密，只有最勇敢的冒险者才能揭开。洞窟深处的寒冷足以冻结任何进入者的灵魂。", Arrays.asList(
                    new Monster(3, 220, 50, 25, 20, 40, 20, "冰霜巨人", 70, "冰霜打击", 5),
                    new Monster(4, 270, 55, 30, 25, 45, 25, "冰霜龙", 90, "冰霜吐息", 6)
            ), new Equipment("冰霜之盾", "Shield", 0, 50, 50, 0, 0, 0, 100, 1)),
            new Dungeon("沙漠遗迹", 3, "一个被沙漠覆盖的古老遗迹，充满了沙漠怪物。传说中，这里埋藏着古代文明的宝藏，等待着被发现。每当风沙吹过，遗迹中的秘密便会被揭开。", Arrays.asList(
                    new Monster(2, 130, 35, 20, 15, 30, 15, "沙漠蝎子", 35, "毒刺", 4),
                    new Monster(3, 190, 40, 25, 20, 35, 20, "沙漠巨人", 50, "沙暴", 5)
            ), new Equipment("沙漠之心", "Amulet", 0, 0, 0, 0, 0, 0, 75, 1)),
            new Dungeon("深海遗迹", 5, "一个沉没在深海中的古老遗迹，充满了海洋怪物。传说中，这里是海洋之王的领地，守护着无尽的宝藏。只有最勇敢的潜水员才能到达这里的深处。", Arrays.asList(
                    new Monster(4, 250, 55, 30, 25, 45, 25, "深海巨兽", 80, "水流冲击", 6),
                    new Monster(5, 300, 60, 35, 30, 50, 30, "海洋之王", 100, "海啸", 7)
            ), new Equipment("海洋之珠", "Orb", 0, 0, 0, 0, 0, 0, 150, 1)),
            new Dungeon("熔岩洞窟", 4, "一个充满熔岩和火焰的洞窟，充满了火焰怪物。传说中，这里是熔岩巨人的巢穴，守护着熔岩之核。每一个进入这里的人都必须面对炽热的熔岩和火焰。", Arrays.asList(
                    new Monster(3, 210, 45, 25, 20, 35, 20, "熔岩怪", 60, "熔岩喷射", 5),
                    new Monster(4, 260, 50, 30, 25, 40, 25, "熔岩巨人", 80, "熔岩爆发", 6)
            ), new Equipment("熔岩之核", "Core", 0, 0, 0, 0, 0, 0, 125, 1))
    );
}