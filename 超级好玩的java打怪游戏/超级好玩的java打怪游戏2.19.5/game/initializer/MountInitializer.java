// File: src/game/initializer/MountInitializer.java
package game.initializer;

import game.mount.Mount;
import game.item.ItemRegistry;

public class MountInitializer {
    public static void registerAllMounts() {
        // Strong mounts
        Mount shadowPanther = new Mount("影豹", 100, 200, 25, 20, 15, 1000);
        Mount pegasus = new Mount("天马", 120, 220, 30, 20, 15, 1200);
        Mount griffin = new Mount("狮鹫", 140, 240, 35, 25, 20, 1400);
        Mount unicorn = new Mount("独角兽", 160, 260, 40, 30, 25, 1600);
        Mount phoenix = new Mount("凤凰", 180, 280, 45, 35, 30, 1800);
        Mount celestialDragon = new Mount("天龙", 200, 300, 50, 40, 35, 2000);
        Mount dragonKing = new Mount("龙王", 220, 320, 55, 45, 40, 2200);
        Mount kirin = new Mount("麒麟", 240, 340, 60, 50, 45, 2400);
        Mount celestialTiger = new Mount("天虎", 260, 360, 65, 55, 50, 2600);
        Mount goldenPhoenix = new Mount("金凤凰", 280, 380, 70, 60, 55, 2800);
        Mount silverDragon = new Mount("银龙", 300, 400, 75, 65, 60, 3000);

        // Weaker mounts with more balanced attributes
        Mount forestDeer = new Mount("森林鹿", 30, 60, 5, 3, 3, 100);
        Mount mountainGoat = new Mount("山羊", 35, 70, 6, 4, 4, 150);
        Mount desertFox = new Mount("沙漠狐", 40, 80, 7, 5, 5, 200);
        Mount riverOtter = new Mount("河狸", 45, 90, 8, 6, 6, 250);
        Mount prairieDog = new Mount("草原犬", 50, 100, 9, 7, 7, 300);
        Mount snowLeopard = new Mount("雪豹", 55, 110, 10, 8, 8, 350);
        Mount jungleTiger = new Mount("丛林虎", 60, 120, 11, 9, 9, 400);
        Mount desertEagle = new Mount("沙漠鹰", 65, 130, 12, 10, 10, 450);
        Mount riverCrocodile = new Mount("河鳄", 70, 140, 13, 11, 11, 500);
        Mount prairieWolf = new Mount("草原狼", 50, 100, 9, 7, 7, 300);

        ItemRegistry.registerMount(shadowPanther);
        ItemRegistry.registerMount(pegasus);
        ItemRegistry.registerMount(griffin);
        ItemRegistry.registerMount(unicorn);
        ItemRegistry.registerMount(phoenix);
        ItemRegistry.registerMount(celestialDragon);
        ItemRegistry.registerMount(dragonKing);
        ItemRegistry.registerMount(kirin);
        ItemRegistry.registerMount(celestialTiger);
        ItemRegistry.registerMount(goldenPhoenix);
        ItemRegistry.registerMount(silverDragon);

        ItemRegistry.registerMount(forestDeer);
        ItemRegistry.registerMount(mountainGoat);
        ItemRegistry.registerMount(desertFox);
        ItemRegistry.registerMount(riverOtter);
        ItemRegistry.registerMount(prairieDog);
        ItemRegistry.registerMount(snowLeopard);
        ItemRegistry.registerMount(jungleTiger);
        ItemRegistry.registerMount(desertEagle);
        ItemRegistry.registerMount(riverCrocodile);
        ItemRegistry.registerMount(prairieWolf);
    }
}