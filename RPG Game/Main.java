import java.util.Scanner;
import java.util.Random;

public class Main {
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Character user1 = new Character();
        Character user2 = new Character();
        Character user3 = new Character();

        user1.regName();
        user1.regClass(user1.getName());
    }
}
