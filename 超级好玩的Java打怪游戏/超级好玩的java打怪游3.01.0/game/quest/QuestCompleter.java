package game.quest;

import game.monster.Monster;
import game.player.Player;

public class QuestCompleter {
    public static void completeQuestForMonster(QuestManager questManager, Player player, Monster monster) {
        switch (monster.attributes.name) {
            case "幽灵":
                questManager.completeQuest(1, player);
                break;
            case "沙漠蝎子":
                questManager.completeQuest(2, player);
                break;
            case "火焰巨人":
                questManager.completeQuest(3, player);
                break;
            case "冰霜巨人":
                questManager.completeQuest(4, player);
                break;
            case "深海巨兽":
                questManager.completeQuest(5, player);
                break;
            case "龙":
                questManager.completeQuest(6, player);
                break;
            case "噩梦巨兽":
                questManager.completeQuest(7, player);
                break;
            case "地狱巨兽":
                questManager.completeQuest(8, player);
                break;
            case "终极巨兽":
                questManager.completeQuest(9, player);
                break;
            case "洞穴蝙蝠":
                questManager.completeQuest(10, player);
                break;
            case "洞穴蜘蛛":
                questManager.completeQuest(11, player);
                break;
            case "亡灵战士":
                questManager.completeQuest(12, player);
                break;
            case "沙漠巨人":
                questManager.completeQuest(13, player);
                break;
            case "火焰蜥蜴":
                questManager.completeQuest(14, player);
                break;
            case "冰霜龙":
                questManager.completeQuest(15, player);
                break;
            case "熔岩怪":
                questManager.completeQuest(16, player);
                break;
            case "海洋之王":
                questManager.completeQuest(17, player);
                break;
            case "龙之守卫":
                questManager.completeQuest(18, player);
                break;
            case "噩梦龙":
                questManager.completeQuest(19, player);
                break;
            case "地狱龙":
                questManager.completeQuest(20, player);
                break;
            case "终极龙":
                questManager.completeQuest(21, player);
                break;
            default:
                System.out.println("没有对应的任务完成逻辑！");
                break;
        }
    }
}