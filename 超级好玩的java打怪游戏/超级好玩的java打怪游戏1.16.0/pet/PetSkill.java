// PetSkill.java
package game.pet;

public class PetSkill implements java.io.Serializable {
    public String name;
    public int baseDamage;
    public int cooldown;
    public int staminaCost;
    public double attackMultiplier; // 新增攻击力加成倍率
    public double intelligenceMultiplier; // 新增智力加成倍率
    public long lastUsed;

    public PetSkill(String name, int baseDamage, int cooldown, int staminaCost, double attackMultiplier, double intelligenceMultiplier) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.cooldown = cooldown;
        this.staminaCost = staminaCost;
        this.attackMultiplier = attackMultiplier;
        this.intelligenceMultiplier = intelligenceMultiplier;
        this.lastUsed = 0;
    }

    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getStaminaCost() {
        return staminaCost;
    }

    public double getAttackMultiplier() {
        return attackMultiplier;
    }

    public double getIntelligenceMultiplier() {
        return intelligenceMultiplier;
    }

    public long getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(long lastUsed) {
        this.lastUsed = lastUsed;
    }
}