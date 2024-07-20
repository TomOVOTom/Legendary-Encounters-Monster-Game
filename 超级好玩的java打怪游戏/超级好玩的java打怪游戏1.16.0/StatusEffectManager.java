// StatusEffectManager.java
package game;

import java.util.Iterator;

public class StatusEffectManager {
    // 状态效果类
    public static class StatusEffect {
        StatusEffectType type;
        int duration; // 持续回合数
        int effectPerTurn; // 每回合的效果，例如扣血量或躲避值减少
        int originalDodge; // 用于存储原始躲避值
        int originalAttack; // 用于存储原始攻击力

        public StatusEffect(StatusEffectType type, int duration, int effectPerTurn) {
            this.type = type;
            this.duration = duration;
            this.effectPerTurn = effectPerTurn;
            this.originalDodge = -1; // 初始化时设置为-1，表示未存储原始躲避值
            this.originalAttack = -1; // 初始化时设置为-1，表示未存储原始攻击力
        }

        // 每回合更新状态效果
        public void updateEffect(Player player) {
            switch (this.type) {
                case POISONED:
                    int poisonDeduction = (int)(player.attributes.hp * (this.effectPerTurn / 100.0));
                    player.attributes.hp -= poisonDeduction;
                    System.out.println("中毒效果，扣除 " + poisonDeduction + " 生命值。");
                    break;
                case BURNING:
                    player.attributes.hp -= this.effectPerTurn;
                    System.out.println("燃烧效果，扣�� " + this.effectPerTurn + " 生命值。");
                    break;
                case FROZEN:
                    int deduction = (int) (player.attributes.hp * (this.effectPerTurn / 100.0));
                    player.attributes.hp -= deduction;
                    System.out.println("冰冻效果，扣除 " + deduction + " 生命值（" + this.effectPerTurn + "%）。");
                    break;
                case CHILLED:
                    int chilledHealthDeduction = (int) (player.attributes.hp * (this.effectPerTurn / 100.0));
                    player.attributes.hp -= chilledHealthDeduction;
                    if (originalDodge == -1) {
                        originalDodge = player.attributes.dodge;
                        player.attributes.dodge -= 10;
                        System.out.println(player.name + " 受到CHILLED效果影响，生命值减少了 " + chilledHealthDeduction + " 点，并且躲避值减少了 10。");
                    } else {
                        System.out.println(player.name + " 由于CHILLED效果，生命值继续减少了 " + chilledHealthDeduction + " 点。");
                    }
                    if (this.duration == 2) {
                        player.attributes.dodge = originalDodge;
                        System.out.println(player.name + " 的CHILLED效果结束，躲避值恢复到 " + originalDodge + "。");
                    }
                    break;
                case SLOWED:
                    if (originalDodge == -1) {
                        originalDodge = player.attributes.dodge;
                        player.attributes.dodge += this.effectPerTurn;
                        System.out.println(player.name + " 受到SLOWED效果影响��躲避值降低了 " + this.effectPerTurn + "。");
                    }
                    if(this.duration == 1) {
                        player.attributes.dodge = originalDodge;
                        System.out.println(player.name + " 的SLOWED效果结束，躲避值恢复到 " + originalDodge + "。");
                    }
                    break;
                case FEAR:
                    if (originalAttack == -1) {
                        originalAttack = player.attributes.attack;
                        player.attributes.attack += this.effectPerTurn;
                        player.setCanAttack(false);
                        System.out.println(player.name + " 受到FEAR效果影响，攻击力减少了 " + (-this.effectPerTurn) + "，并且并且暂时无法攻击。");
                    }
                    if(this.duration==2)
                    {
                        player.setCanAttack(true);
                        System.out.println(player.name + " 的FEAR效果减弱，可以攻击了。");

                    }
                    if (this.duration == 1) {
                        player.attributes.attack = originalAttack;
                        System.out.println(player.name + " 的FEAR效果结束，攻击力恢复到 " + originalAttack );
                    }
                    break;
                case STUNNED:
                    player.setCanAttack(false);
                    System.out.println(player.name + " 被震晕了，无法攻击。");
                    if (this.duration == 1) {
                        player.setCanAttack(true);
                        System.out.println(player.name + " 的震晕效果结束，可以再次攻击。");
                    }
                    break;
            }
            this.duration--;
        }

        public boolean isFinished() {
            return this.duration <= 0;
        }
    }

    // 更新状态效果的方法
    public static void updateStatusEffects(Player player) {
        Iterator<StatusEffect> iterator = player.statusEffects.iterator();
        while (iterator.hasNext()) {
            StatusEffect effect = iterator.next();
            effect.updateEffect(player);
            if (effect.isFinished()) {
                iterator.remove();
            }
        }
    }
}