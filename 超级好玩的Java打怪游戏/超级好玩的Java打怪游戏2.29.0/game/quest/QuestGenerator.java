package game.quest;

import java.util.List;

public class QuestGenerator {
    public static void generateQuests(List<Quest> availableQuests) {
        availableQuests.add(new Quest(1, "主线任务 1", "击败幽灵", Quest.QuestType.MAIN, "100 XP, 50 Gold", false));
        availableQuests.add(new Quest(2, "主线任务 2", "击败沙漠蝎子", Quest.QuestType.MAIN, "150 XP, 70 Gold", false));
        availableQuests.add(new Quest(3, "主线任务 3", "击败火焰巨人", Quest.QuestType.MAIN, "200 XP, 100 Gold", false));
        availableQuests.add(new Quest(4, "主线任务 4", "击败冰霜巨人", Quest.QuestType.MAIN, "250 XP, 120 Gold", false));
        availableQuests.add(new Quest(5, "主线任务 5", "击败深海巨兽", Quest.QuestType.MAIN, "300 XP, 150 Gold", false));
        availableQuests.add(new Quest(6, "主线任务 6", "击败龙", Quest.QuestType.MAIN, "350 XP, 200 Gold", false));
        availableQuests.add(new Quest(7, "主线任务 7", "击败噩梦巨兽", Quest.QuestType.MAIN, "400 XP, 250 Gold", false));
        availableQuests.add(new Quest(8, "主线任务 8", "击败地狱巨兽", Quest.QuestType.MAIN, "450 XP, 300 Gold", false));
        availableQuests.add(new Quest(9, "主线任务 9", "击败终极巨兽", Quest.QuestType.MAIN, "500 XP, 350 Gold", false));
        // 新增任务
        availableQuests.add(new Quest(10, "支线任务 1", "击败洞穴蝙蝠", Quest.QuestType.SIDE, "50 XP, 20 Gold", true));
        availableQuests.add(new Quest(11, "支线任务 2", "击败洞穴蜘蛛", Quest.QuestType.SIDE, "60 XP, 25 Gold", true));
        availableQuests.add(new Quest(12, "支线任务 3", "击败亡灵战士", Quest.QuestType.SIDE, "70 XP, 30 Gold", true));
        availableQuests.add(new Quest(13, "支线任务 4", "击败沙漠巨人", Quest.QuestType.SIDE, "80 XP, 35 Gold", true));
        availableQuests.add(new Quest(14, "支线任务 5", "击败火焰蜥蜴", Quest.QuestType.SIDE, "90 XP, 40 Gold", true));
        availableQuests.add(new Quest(15, "支线任务 6", "击败冰霜龙", Quest.QuestType.SIDE, "100 XP, 45 Gold", true));
        availableQuests.add(new Quest(16, "支线任务 7", "击败熔岩怪", Quest.QuestType.SIDE, "110 XP, 50 Gold", true));
        availableQuests.add(new Quest(17, "支线任务 8", "击败海洋之王", Quest.QuestType.SIDE, "120 XP, 55 Gold", true));
        availableQuests.add(new Quest(18, "支线任务 9", "击败龙之守卫", Quest.QuestType.SIDE, "130 XP, 60 Gold", true));
        availableQuests.add(new Quest(19, "支线任务 10", "击败噩梦龙", Quest.QuestType.SIDE, "140 XP, 65 Gold", true));
        availableQuests.add(new Quest(20, "支线任务 11", "击败地狱龙", Quest.QuestType.SIDE, "150 XP, 70 Gold", true));
        availableQuests.add(new Quest(21, "支线任务 12", "击败终极龙", Quest.QuestType.SIDE, "160 XP, 75 Gold", true));
    }
}