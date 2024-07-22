// File: src/game/item/ItemEffect.java
package game.item;

import game.monster.Monster;
import game.player.Player;

public class ItemEffect {

    public static void applyEffect(Player player, Item selectedItem) {
        switch (selectedItem.getType()) {
            case "defense":
                player.attributes.defense += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点防御力！");
                break;
            case "stamina":
                player.attributes.stamina += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点体力！");
                break;
            case "dodge":
                player.attributes.dodge += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点闪避！");
                break;
            case "intelligence":
                player.attributes.intelligence += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点智力！");
                break;
            case "damage":
                Monster monster = ItemManager.findCurrentMonster();
                if (monster != null) {
                    int damage = selectedItem.getEffect();
                    monster.hp -= damage;
                    System.out.println("你使用了 " + selectedItem.getName() + "，对 " + monster.name + " 造成了 " + damage + " 点伤害！");
                }
                break;
            case "healing":
                player.attributes.hp += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，恢复了 " + selectedItem.getEffect() + " 点生命值！");
                break;
            default:
                System.out.println("未知的物品类型！");
                break;
        }
    }
}