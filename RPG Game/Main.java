import java.util.Scanner;
import java.util.Random;

public class Main {
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        UserCharacter user1 = new UserCharacter();
        UserCharacter user2 = new UserCharacter();

        ComputerCharacter enemy1 = new ComputerCharacter();

        user1.regName("Glenn");
        user1.regClass("Rogue");

        user2.regName("Vlademir");
        user2.regClass("Warrior");

        for (int i = 0; i < 100; i++) {
            user1.gainExp(100000);
            user2.gainExp(1000000);
        }

        user1.enterBattleState();
        user2.enterBattleState();

        System.out.println("Glenn attacks Vlademir");

        user2.setBattleHealth(user2.getBattleHealth() - user1.dealDamage(user1.getPhyAttack(), user2.getPhyDefense()));

        user2.getStatus();

        System.out.println("Vlademir counterattacks!");

        user1.setBattleHealth(user1.getBattleHealth() - user2.dealDamage(user2.getPhyAttack(), user1.getPhyDefense()));

        user1.getStatus();
    }
}
