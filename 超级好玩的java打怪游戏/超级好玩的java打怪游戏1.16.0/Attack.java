// game/Attack.java
package game;

import game.pet.Pet;

import java.util.Random;

public class Attack {
    private Player player;
    private Monster monster;

    public Attack(Player player) {
        this.player = player;
    }

    public Attack(Monster monster, Player player) {
        this.monster = monster;
        this.player = player;
    }

    public void attack(Monster monster) {
        this.monster = monster; // Ensure monster is initialized
        Random random = new Random();
        if (random.nextInt(150) < monster.dodge) {
            System.out.println(monster.name + " 躲避了你的攻击！");
            player.comboCounter = 0; // 怪物躲避攻击时，连击计数器重置
        } else {
            int baseDamage; // 基础伤害值
            boolean isCriticalHit = random.nextInt(150) > monster.dodge;
            if (isCriticalHit) {
                baseDamage = Math.max((player.attributes.attack - monster.defense) * 2, 0);
                System.out.println("你发动了暴击！攻击了 " + monster.name + "，造成了 " + baseDamage + " 点伤害！");
            } else {
                baseDamage = Math.max(player.attributes.attack - monster.defense, 0);
                if (baseDamage > 0) {
                    System.out.println("你攻击了 " + monster.name + "，造成了 " + baseDamage + " 点伤害！");
                } else {
                    System.out.println("你的攻击被 " + monster.name + " 防御住了！");
                    player.comboCounter = 0;
                    return;
                }
            }

            double comboMultiplier = 1 + player.comboCounter * 0.4;
            int totalDamage = (int) (baseDamage * comboMultiplier);
            totalDamage = Math.max(totalDamage, 0);

            if (player.comboCounter > 0) {
                System.out.println("连击！造成额外伤害：" + (totalDamage - baseDamage));
            }

            monster.hp -= totalDamage;
            player.comboCounter++;

            if (player.attributes.vampirismLevel > 0 && player.attributes.stamina > 0 && totalDamage > 0) {
                int healAmount = (int) (totalDamage * player.attributes.vampirismLevel * 0.1);
                healAmount = Math.max(healAmount, 0);

                player.attributes.hp += healAmount;
                player.attributes.stamina -= 5;
                System.out.println("你吸取了 " + healAmount + " 点生命值！ 体力消耗了 5 点。");
            }
        }
    }

    public void attack(Player player) {
        Random random = new Random();
        if (random.nextInt(150) < player.attributes.dodge) {
            System.out.println("你躲避了 " + monster.name + " 的攻击！");
        } else {
            if (random.nextInt(150) > player.attributes.dodge) {
                int damage = Math.max((monster.attack - player.attributes.defense) * 2, 0);
                player.attributes.hp -= damage;
                System.out.println(monster.name + " 发动了暴击！攻击了你，造成了 " + damage + " 点伤害！");
            } else {
                int damage = Math.max(monster.attack - player.attributes.defense, 0);
                player.attributes.hp -= damage;
                if (damage > 0) {
                    System.out.println(monster.name + " 攻击了你，造成了 " + damage + " 点伤害！");
                } else {
                    System.out.println(monster.name + " 的攻击被你防御住了！");
                }
            }
        }
    }

    public void attackRandomTarget() {
        Random random = new Random();
        if (player.getEquippedPet() != null && random.nextBoolean()) {
            attack(player.getEquippedPet());
        } else {
            attack(player);
        }
    }

    public void attack(Pet pet) {
        Random random = new Random();
        if (random.nextInt(150) < pet.dodge) {
            System.out.println(pet.name + " 躲避了 " + monster.name + " 的攻击！");
        } else {
            int damage = Math.max(monster.attack - pet.defense, 0);
            pet.hp -= damage;
            if (damage > 0) {
                System.out.println(monster.name + " 攻击了 " + pet.name + "，造成了 " + damage + " 点伤害！");
            } else {
                System.out.println(monster.name + " 的攻击被 " + pet.name + " 防御住了！");
            }
            if (pet.hp <= 0) {
                System.out.println(pet.name + " 被击败了！");
                player.equippedPet = null;
            }
        }
    }
}