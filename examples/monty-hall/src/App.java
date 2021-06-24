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
            playAgain = readInt(console, "Play again? [1 - Yes, 2 - No]", 1, 2) == 1;
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
        playerDoor = getFinalPlayerDoor(console, random, playerDoor, revealedDoor);

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

    public static int getFinalPlayerDoor(Scanner console, Random random, int playerDoor, int revealedDoor) {
        int result = playerDoor;
        System.out.printf("You picked door %s...%n", playerDoor);
        System.out.printf("The announcer has revealed a goat behind door %s%n", revealedDoor);
        int switchDoor = readInt(console, "Switch? [1 - Yes, 2 - No] ", 1, 2);

        if (switchDoor == 1) {
            result = 6 - playerDoor - revealedDoor;
            // result = revealDoorWithRandomLoop(random, playerDoor, revealedDoor, numberOfDoors);
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
            System.out.println(prompt);
            result = Integer.parseInt(console.nextLine());

            if (result < min || result > max) {
                System.out.printf("Please choose a number between %s and %s.%n", min, max);
            }

        } while (result < min || result > max);
        return result;
    }
}
