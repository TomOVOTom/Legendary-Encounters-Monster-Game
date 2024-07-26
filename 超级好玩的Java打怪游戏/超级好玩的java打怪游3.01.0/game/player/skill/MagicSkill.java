// File: src/game/player/skill/MagicSkill.java
package game.player.skill;

import game.monster.Monster;
import game.player.Player;
import game.player.skill.magic.*;

public class MagicSkill {
    public static void useHealMagic(Player player) {
        HealMagic.useHealMagic(player);
    }

    public static void useFireMagic(Player player, Monster monster) {
        FireMagic.useFireMagic(player, monster);
    }

    public static void useLightningMagic(Player player, Monster monster) {
        LightningMagic.useLightningMagic(player, monster);
    }

    public static void useIceMagic(Player player, Monster monster) {
        IceMagic.useIceMagic(player, monster);
    }

    public static void learnFireMagic(Player player) {
        FireMagic.learnFireMagic(player);
    }

    public static void learnHealMagic(Player player) {
        HealMagic.learnHealMagic(player);
    }

    public static void learnVampirism(Player player) {
        Vampirism.learnVampirism(player);
    }

    public static void learnLightningMagic(Player player) {
        LightningMagic.learnLightningMagic(player);
    }

    public static void learnIceMagic(Player player) {
        IceMagic.learnIceMagic(player);
    }
}