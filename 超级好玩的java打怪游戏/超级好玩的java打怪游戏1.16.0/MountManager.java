// MountManager.java
package game;

public class MountManager {
    public static boolean ownsMount(Player player, Mount mount) {
        return player.getInventory().getInventory().values().contains(mount);
    }

    public static void equipMount(Player player, Mount mount) {
        if (player.equippedMount != null) {
            System.out.println("你已经装备了 " + player.equippedMount.name + "，先卸下它！");
            unequipMount(player);
        }
        player.equippedMount = mount;
        System.out.println("你装备了 " + mount.name + "！");
        applyMountBonuses(player, mount);
        Game.horse.displayMountAttributeBonuses(mount);
    }

    public static void unequipMount(Player player) {
        if (player.equippedMount != null) {
            System.out.println("你卸下了 " + player.equippedMount.name + "！");
            removeMountBonuses(player, player.equippedMount);
            player.equippedMount = null;
        } else {
            System.out.println("你没有装备任何坐骑！");
        }
    }

    public static Mount getEquippedMount(Player player) {
        return player.equippedMount;
    }

    private static void applyMountBonuses(Player player, Mount mount) {
        player.attributes.dodge += mount.dodgeBonus;
        player.attributes.attack += mount.attackBonus;
        player.attributes.defense += mount.defenseBonus;
    }

    private static void removeMountBonuses(Player player, Mount mount) {
        player.attributes.dodge -= mount.dodgeBonus;
        player.attributes.attack -= mount.attackBonus;
        player.attributes.defense -= mount.defenseBonus;
    }

    public static void restoreHealth(Player player, int speed) {
        int restoredHp = speed;
        player.attributes.hp += restoredHp;
        System.out.println("骑乘坐骑恢复了 " + restoredHp + " 点生命值！");
    }
}