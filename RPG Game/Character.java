import java.util.Scanner;
import java.util.Random;

public class Character {
    private String charName;
    private String charClass;
    private int level = 1;
    private int maxHealth;
    private int maxMana;
    private int physicalAttack;
    private int magicalAttack;
    private int physicalDefense;
    private int magicalDefense;
    private int criticalChance;

    private boolean alive = true;
    private int battleHealth;
    private int battleMana;

    private Scanner input = new Scanner(System.in);
    private Random random = new Random();

    public void enterBattleState() {
        battleHealth = maxHealth;
        battleHealth = maxMana;
    }

    public String getName() {
        return charName;
    }

    public String getCharClass() {
        return charClass;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getPhyAttack() {
        return physicalAttack;
    }

    public int getMagAttack() {
        return magicalAttack;
    }

    public int getPhyDefense() {
        return physicalDefense;
    }

    public int getMagDefense() {
        return magicalDefense;
    }

    public int getCriticalChance() {
        return criticalChance;
    }

    public void getStatus() {
        System.out.printf("Player %s\nClass: %s\nLevel: %d\nMax Health: %d\nMax Mana: %d\nPhysical Attack: %d\nMagical Attack: %d\nPhysical Defense: %d\nMagical Defense: %d\nCritical Chance: %d\n\n", getName(), getCharClass(), getLevel(), getMaxHealth(), getMaxMana(), getPhyAttack(), getMagAttack(), getPhyDefense(), getMagDefense(), getCriticalChance());
    }

    public void regName() {
        boolean goodState = false;
        while (!goodState) {
            System.out.print("Enter character name: ");
            charName = input.nextLine();

            if (charName.length() <= 10 && !charName.contains(" ")) {
                System.out.printf("Welcome, %s!\n", getName());
                goodState = true;
            } else {
                System.out.println("Usernames must be not longer than 10 characters and no spaces are allowed.");
            }
        }
    }

    public void regClass(String name) {
        boolean goodState = false;
        while (!goodState) {
            System.out.printf("Hello %s, Choose your class:\nA. Warrior\nB. Mage\nC. Assassin\n> ", name);
            charClass = input.nextLine().toLowerCase();

            switch (charClass) {
                case "a":
                    charClass = "Warrior";
                    regStatus();
                    goodState = true;
                    break;
                case "b":
                    charClass = "Mage";
                    regStatus();
                    goodState = true;
                    break;
                case "c":
                    charClass = "Assassin";
                    regStatus();
                    goodState = true;
                    break;
                default:
                    System.out.println("Choose only the letters provided.");
                    break;
            }
        }
    }

    public void regStatus() {
        switch (charClass) {
            case "Warrior":
                maxHealth = randomRange(450, 500);
                maxMana = randomRange(100, 150);
                physicalAttack = randomRange(40, 80);
                magicalAttack = randomRange(20, 60);
                physicalDefense = randomRange(80, 100);
                magicalDefense = randomRange(40, 80);
                criticalChance = randomRange(10, 30);
                break;
            case "Mage":
                maxHealth = randomRange(350, 450);
                maxMana = randomRange(175, 200);
                physicalAttack = randomRange(20, 60);
                magicalAttack = randomRange(80, 100);
                physicalDefense = randomRange(20, 60);
                magicalDefense = randomRange(80, 100);
                criticalChance = randomRange(20, 40);
                break;
            case "Assassin":
                maxHealth = randomRange(300, 400);
                maxMana = randomRange(125, 175);
                physicalAttack = randomRange(80, 100);
                magicalAttack = randomRange(40, 80);
                physicalDefense = randomRange(40, 80);
                magicalDefense = randomRange(20, 60);
                criticalChance = randomRange(40, 50);
                break;
            default:
                throw new RuntimeException("User Class not completed. Please restart the program.");
        }
    }

    public int randomRange(int low, int high) {
        high++;
        return random.nextInt(high-low) + low;
    }

    public void raiseLevel() {
        level += 1;
        switch (charClass) {
            case "Warrior":
                maxHealth += randomRange(450, 500) * 0.2;
                maxMana += randomRange(100, 150) * 0.2;
                physicalAttack += randomRange(40, 80) * 0.2;
                magicalAttack += randomRange(20, 60) * 0.2;
                physicalDefense += randomRange(80, 100) * 0.2;
                magicalDefense += randomRange(40, 80) * 0.2;
                break;
            case "Mage":
                maxHealth += randomRange(350, 450) * 0.2;
                maxMana += randomRange(175, 200) * 0.2;
                physicalAttack += randomRange(20, 60) * 0.2;
                magicalAttack += randomRange(80, 100) * 0.2;
                physicalDefense += randomRange(20, 60) * 0.2;
                magicalDefense += randomRange(80, 100) * 0.2;
                break;
            case "Assassin":
                maxHealth += randomRange(300, 400) * 0.2;
                maxMana += randomRange(125, 175) * 0.2;
                physicalAttack += randomRange(80, 100) * 0.2;
                magicalAttack += randomRange(40, 80) * 0.2;
                physicalDefense += randomRange(40, 80) * 0.2;
                magicalDefense += randomRange(20, 60) * 0.2;
                break;
            default:
                throw new RuntimeException("User Class not completed. Please restart the program.");
        }

        if (level % 10 == 0) {
            switch (charClass) {
                case "Warrior":
                    criticalChance += randomRange(10, 30) * 0.1;
                    break;
                case "Mage":
                    criticalChance += randomRange(20, 40) * 0.1;
                    break;
                case "Assassin":
                    criticalChance += randomRange(40, 50) * 0.1;
                    break;
                default:
                    throw new RuntimeException("User Class not completed. Please restart the program.");
            }
        }
    }
}
