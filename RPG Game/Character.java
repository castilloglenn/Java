import java.util.Scanner;
import java.util.Random;

public class Character {

    private String charName;
    private String charClass;
    private int level = 1;
    private double maxHealth;
    private double maxMana;
    private double physicalAttack;
    private double magicalAttack;
    private double physicalDefense;
    private double magicalDefense;
    private double criticalChance;

    private boolean aliveStatus = true;
    private boolean inBattleStatus = false;
    private boolean criticalDamage = false;
    private double battleHealth;
    private double battleMana;

    public Scanner input = new Scanner(System.in);
    public Random random = new Random();

    public void refreshHealth() {
        this.battleHealth = this.maxHealth;
        this.battleMana = this.maxMana;
    }

    public void enterBattleState() {
        this.inBattleStatus = true;
        this.battleHealth = this.maxHealth;
        this.battleMana = this.maxMana;
    }

    public boolean checkVital() {
        return this.battleHealth <= 0;
    }

    public double dealDamage(double damageDealt, double enemyDefense) {
        int criticalPercent = random.nextInt(100) + 1;

        if (Double.valueOf(criticalPercent) <= this.criticalChance) {
            this.criticalDamage = true;
            damageDealt *= 1.5;
            System.out.println("Crit takes effect");
        }

        double netDamage = damageDealt * (100 / (100 + enemyDefense));

        System.out.printf("Total damage is: %,.0f\n\n", netDamage);
        return netDamage;
    }

    public double randomRange(int low, int high) {
        high++;
        return Double.valueOf(random.nextInt(high-low) + low);
    }

    public String getName() {
        return this.charName;
    }

    public String getCharClass() {
        return this.charClass;
    }

    public int getLevel() {
        return this.level;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public double getMaxMana() {
        return this.maxMana;
    }

    public double getPhyAttack() {
        return this.physicalAttack;
    }

    public double getMagAttack() {
        return this.magicalAttack;
    }

    public double getPhyDefense() {
        return this.physicalDefense;
    }

    public double getMagDefense() {
        return this.magicalDefense;
    }

    public double getCriticalChance() {
        return this.criticalChance;
    }

    public boolean getAliveStatus() {
        return this.aliveStatus;
    }

    public boolean getInBattleStatus() {
        return this.inBattleStatus;
    }

    public double getBattleHealth() {
        return this.battleHealth;
    }

    public double getBattleMana() {
        return this.battleMana;
    }

    public void setName(String newName) {
        this.charName = newName;
    }

    public void setCharClass(String newClass) {
        this.charClass = newClass;
    }

    public void setLevel(int newLevel) {
        this.level = newLevel;
    }

    public void setMaxHealth(double newMaxLevel) {
        this.maxHealth = newMaxLevel;
    }

    public void setMaxMana(double newMaxMana) {
        this.maxMana = newMaxMana;
    }

    public void setPhyAttack(double newPhyAtk) {
        this.physicalAttack = newPhyAtk;
    }

    public void setMagAttack(double newMagAtk) {
        this.magicalAttack = newMagAtk;
    }

    public void setPhyDefense(double newPhyDef) {
        this.physicalDefense = newPhyDef;
    }

    public void setMagDefense(double newMagDef) {
        this.magicalDefense = newMagDef;
    }

    public void setCriticalChance(double newCritChance) {
        this.criticalChance = newCritChance;
    }

    public void setAliveStatus(boolean newStatus) {
        this.aliveStatus = newStatus;
    }

    public void setInBattleStatus(boolean newStatus) {
        this.inBattleStatus = newStatus;
    }

    public void setBattleHealth(double newHealth) {
        this.battleHealth = newHealth;
    }

    public void setBattleMana(double newMana) {
        this.battleMana = newMana;
    }
}
