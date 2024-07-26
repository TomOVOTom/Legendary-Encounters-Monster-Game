// game/base/FacilityViewer.java
package game.base;

import game.player.Player;

public class FacilityViewer {
    public static void viewFacilities(Player player) {
        Facility[] facilities = player.base.getUnlockedFeatures();
        System.out.println("当前基地设施：");
        for (Facility facility : facilities) {
            System.out.println("- " + facility.getName() + ": " + facility.getDescription());
        }
    }
}