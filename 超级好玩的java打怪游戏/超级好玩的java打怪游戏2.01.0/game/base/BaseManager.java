// game/base/BaseManager.java
package game.base;

import game.player.Player;
import java.util.Scanner;

public class BaseManager {
    public static void baseActions(Player player, Scanner scanner) {
        BaseActions.baseActions(player, scanner);
    }

    public static void upgradeBase(Player player, Scanner scanner) {
        BaseUpgrade.upgradeBase(player, scanner);
    }

    public static void viewFacilities(Player player) {
        FacilityViewer.viewFacilities(player);
    }

    public static void rest(Player player) {
        RestManager.rest(player);
    }

    public static void learnSkills(Player player, Scanner scanner) {
        SkillLearner.learnSkills(player, scanner);
    }
}