import java.util.Scanner;
import java.util.Random;

public class Main {
    private static String getUserName() {
        Scanner input = new Scanner(System.in);
        String name;

        while (true) {
            System.out.print("Enter your username: ");
            name = input.nextLine();

            if (name.length() > 10) {
                System.out.println("Username must not exceed more than 10 characters.");
            } else if (name.contains(" ")) {
                System.out.println("Username must not contain spaces.");
            } else break;
        }

        return name;
    }

    private static int getNumber() {
        Random random = new Random();
        return random.nextInt(7);
    }

    private static int getBet(String username, int currentCoins) {
        Scanner input = new Scanner(System.in);
        String userChoice;
        int bet = 0;

        if (currentCoins != 0) {
            do {
                mainInterface(username, currentCoins, 0, 0);
                System.out.print("Please enter your bet:\nA. 5 Coins\nB. 10 Coins\nC. 25 Coins\nD. 50 Coins\n> ");
                userChoice = input.next().toLowerCase();

                switch (userChoice) {
                    case "a":
                        bet = 5;
                        break;
                    case "b":
                        bet = 10;
                        break;
                    case "c":
                        bet = 25;
                        break;
                    case "d":
                        bet = 50;
                        break;
                    default:
                        System.out.println("Please enter the letter of your choice only.");
                        break;
                }
            } while (currentCoins < bet);

        } else {
            System.out.println("You do not have any coins left.");
            System.exit(0);
        }

        return bet;
    }

    private static boolean hiLoBet(String username, int coins, int comp) {
        Scanner input = new Scanner(System.in);
        String answer;

        while (true) {
            mainInterface(username, coins, comp, 0);
            System.out.print("Do you think your dice is higher or lower?"
                             + "\nPlease enter \"HI\" or \"LO\" only."
                             + "\n> ");
            answer = input.next().toLowerCase();
            if (answer.equals("hi") || answer.equals("high")) {
                return true;
            } else if (answer.equals("lo") || answer.equals("low")) {
                return false;
            } else {
                System.out.println("Please enter \"HI\" or \"LO\" only.");
            }
        }
    }

    private static void mainInterface(String username, int coins, int comp, int you) {
        System.out.printf("\n\n\n\n\n\n\n\n\n\n"
                       + "Welcome %s to HI-LO!"
                       + "\nYou still have %s Coins left."
                       + "\n\n    Computer: %s    You: %s\n\n"
                        , username
                        , coins
                        , (comp == 0) ? "?" : comp
                        , (you == 0) ? "?" : you);
    }

    private static int userWon(int coins, int bet) {
        System.out.println("Congratulations! You won " + bet + " Coins.");
        System.out.println("New Balance: " + (coins + bet));
        return coins + bet;
    }

    private static int userLost(int coins, int bet) {
        System.out.println("Sorry, You lost " + bet + " Coins.");
        System.out.println("New Balance: " + (coins + bet));
        return coins - bet;
    }

    private static void userDraw(int coins) {
        System.out.println("It's a draw!");
        System.out.println("Balance: " + coins);
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String username = getUserName();
        String tryAgain;
        boolean userDecision;
        int userCoins = 1000;
        int userDice;
        int compDice;
        int userBet;

        main:
        while (userCoins != 0) {
            userDice = getNumber();
            compDice = getNumber();
            userBet = getBet(username, userCoins);
            userDecision = hiLoBet(username, userCoins, compDice);

            mainInterface(username, userCoins, compDice, userDice);
            if (userDecision) {
                if (userDice > compDice) {
                    userCoins = userWon(userCoins, userBet);
                } else if (userDice < compDice){
                    userCoins = userLost(userCoins, userBet);
                } else {
                    userDraw(userCoins);
                }
            } else {
                if (userDice < compDice) {
                    userCoins = userWon(userCoins, userBet);
                } else if (userDice > compDice) {
                    userCoins = userWon(userCoins, userBet);
                } else {
                    userDraw(userCoins);
                }
            }

            while (true) {
                System.out.println("\nWould you like to try again? (Yes/No)\n> ");
                tryAgain = input.next().toLowerCase();
                if (tryAgain.equals("yes") || tryAgain.equals("y")) {
                    break;
                } else if (tryAgain.equals("no") || tryAgain.equals("n")) {
                    break main;
                } else {
                    System.out.println("Please enter yes or no only.");
                }
            }
        }
    }
}
