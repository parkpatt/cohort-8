import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean playAgain;
        int totalGames = 0;
        int totalWins = 0;

        do {
            boolean winner = playMontyHall(console);
            if (winner) {
                totalWins++;
            }
            totalGames++;
            playAgain = readBoolean(console, "Play again? [y/n]");
        } while (playAgain);

        System.out.printf("Total Cars: %s%n", totalWins);
        System.out.printf("Total Goats: %s%n", totalGames - totalWins);
        System.out.printf("Total Games: %s%n", totalGames);
    }

    public static boolean playMontyHall(Scanner console) {
        System.out.println("Let's Make a Deal!");

        Random random = new Random();
        int carDoor = random.nextInt(3) + 1;
        int playerDoor = readInt(console, "Pick a Door: 1, 2, or 3...",1, 3);
        int revealedDoor = revealDoor(random, playerDoor, carDoor);
        playerDoor = getFinalPlayerDoor(console, playerDoor, revealedDoor);

        printResult(playerDoor == carDoor);

        return playerDoor == carDoor;
    }

    public static int revealDoor(Random random, int playerDoor, int carDoor) {
        int revealedDoor;
        do {
            revealedDoor = random.nextInt(3) + 1;
        } while (revealedDoor == playerDoor || revealedDoor == carDoor);
        return revealedDoor;
    }

    public static int getFinalPlayerDoor(Scanner console, int playerDoor, int revealedDoor) {
        int result = playerDoor;
        System.out.printf("You picked door %s...%n", playerDoor);
        System.out.printf("The announcer has revealed a goat behind door %s%n", revealedDoor);
        boolean switchDoor = readBoolean(console, "Switch? [y/n] ");

        if (switchDoor) {
            result = 6 - playerDoor - revealedDoor;
        }

        return result;
    }

    public static void printResult(boolean winner) {
        if (winner) {
            System.out.println("You won a car!");
        } else {
            System.out.println("You got a goat!");
        }
    }

    public static int readInt(Scanner console, String prompt, int min, int max) {
        int result;
        do {
            result = Integer.parseInt(readString(console, prompt));
            if (result < min || result > max) {
                System.out.printf("Please choose a number between %s and %s.%n", min, max);
            }

        } while (result < min || result > max);
        return result;
    }

    public static boolean readBoolean(Scanner console, String prompt) {
        String input = null;
        do {
            input = readString(console, prompt);
            if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
                System.out.printf("I don't recognize that option");
            }
        } while(!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));
        return input.equalsIgnoreCase("y");
    }

    public static String readString(Scanner console, String prompt) {
        System.out.println(prompt);
        return console.nextLine();
    }
}
