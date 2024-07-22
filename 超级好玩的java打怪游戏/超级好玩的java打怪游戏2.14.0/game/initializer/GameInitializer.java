// File: src/game/initializer/GameInitializer.java
package game.initializer;

public class GameInitializer {
    public static void registerAll() {
        ItemInitializer.registerAllItems();
        EquipmentInitializer.registerAllEquipment();
        MountInitializer.registerAllMounts();
    }
}