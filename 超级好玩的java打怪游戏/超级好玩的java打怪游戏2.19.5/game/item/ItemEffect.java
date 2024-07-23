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
            case "criticalRate":
                player.attributes.criticalRate += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点暴击率！");
                break;
            case "criticalDamage":
                player.attributes.criticalDamage += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点暴击伤害！");
                break;
            case "hitRate":
                player.attributes.hitRate += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点命中率！");
                break;
            case "blockRate":
                player.attributes.blockRate += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点格挡率！");
                break;
            case "dodgeRate":
                player.attributes.dodgeRate += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点闪避率！");
            case "fireMagicLevel":
                player.attributes.fireMagicLevel += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点火魔法等级！");
                break;
            case "healMagicLevel":
                player.attributes.healMagicLevel += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点治疗魔法等级！");
                break;
            case "vampirismLevel":
                player.attributes.vampirismLevel += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点吸血魔法等级！");
                break;
            case "lightningMagicLevel":
                player.attributes.lightningMagicLevel += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点雷电魔法等级！");
                break;
            case "iceMagicLevel":
                player.attributes.iceMagicLevel += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点冰霜魔法等级！");
                break;

            case "piercingAttackDamage":
                player.attributes.piercingAttackDamage += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点穿透攻击伤害！");
                break;
            case "armorPenetration":
                player.attributes.armorPenetration += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点防御穿透！");
                break;
            case "damageIncrease":
                player.attributes.damageIncrease += selectedItem.getEffect();
                System.out.println("你使用了 " + selectedItem.getName() + "，提升了 " + selectedItem.getEffect() + " 点额外伤害！");
                break;
            default:
                System.out.println("未知的物品类型！");
                break;
        }
    }
}