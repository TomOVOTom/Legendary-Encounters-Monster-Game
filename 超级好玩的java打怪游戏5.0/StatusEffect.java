package game;

// 步骤2：创建状态效果类
public class StatusEffect {
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
                player.hp -= this.effectPerTurn;
                System.out.println("中毒效果，扣除 " + this.effectPerTurn + " 生命值。");
                break;
            case BURNING:
                player.hp -= this.effectPerTurn;
                System.out.println("燃烧效果，扣除 " + this.effectPerTurn + " 生命值。");
                break;
            case FROZEN:
                // 根据当前生命的百分比计算扣除
                int deduction = (int) (player.hp * (this.effectPerTurn / 100.0));
                player.hp -= deduction;
                System.out.println("冰冻效果，扣除 " + deduction + " 生命值（" + this.effectPerTurn + "%）。");
                break;
            case CHILLED:
                // 假设effectPerTurn用于存储要扣除的血量百分比
                int chilledHealthDeduction = (int) (player.hp * (this.effectPerTurn / 100.0));
                player.hp -= chilledHealthDeduction;
                if (originalDodge == -1) { // 如果未存储原始躲避值，则存储并减少躲避值
                    originalDodge = player.dodge;
                    player.dodge -= 10; // 假设减少10点躲避值
                    System.out.println(player.name + " 受到CHILLED效果影响，生命值减少了 " + chilledHealthDeduction + " 点，并且躲避值减少了 10。");
                } else {
                    System.out.println(player.name + " 由于CHILLED效果，生命值继续减少了 " + chilledHealthDeduction + " 点。");
                }
                if (this.duration == 5) {
                    player.dodge = originalDodge; // 持续时间结束时恢复躲避值
                    System.out.println(player.name + " 的CHILLED效果结束，躲避值恢复到 " + originalDodge + "。");
                }
            case FEAR:
                if (originalAttack == -1) { // 假设originalAttack用于存储原始攻击力
                    originalAttack = player.attack;
                    player.attack += this.effectPerTurn; // effectPerTurn在这里是负数
                    player.setCanAttack(false); // 设置玩家在下一回合不能攻击
                    System.out.println(player.name + " 受到FEAR效果影响，攻击力减少了 " + (-this.effectPerTurn) + "，并且无法在下一回合攻击。");
                }
                if (this.duration == 1) {
                    player.attack = originalAttack; // 持续时间结束时恢复攻击力
                    player.setCanAttack(true); // 恢复玩家的攻击能力
                    System.out.println(player.name + " 的FEAR效果结束，攻击力恢复到 " + originalAttack + "，并且可以再次攻击。");
                }
                break;

        }
        this.duration--;
    }

    public boolean isFinished() {
        return this.duration <= 0;
    }
}