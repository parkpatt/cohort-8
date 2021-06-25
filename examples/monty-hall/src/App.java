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
        int revealedDoor = revealDoorCalculated(random, playerDoor, carDoor);
        System.out.printf("You picked door %s...%n", playerDoor);
        System.out.printf("The announcer has revealed a goat behind door %s%n", revealedDoor);
        boolean winner = getFinalPlayerDoor(console, playerDoor, revealedDoor) == carDoor;
        printResult(winner);

        return winner;
    }

    public static int revealDoorLoopRandom(Random random, int playerDoor, int carDoor) {
        int revealedDoor;
        do {
            revealedDoor = random.nextInt(3) + 1;
        } while (revealedDoor == playerDoor || revealedDoor == carDoor);
        return revealedDoor;
    }

    public static int revealDoorCalculated(Random random, int playerDoor, int carDoor) {
        int revealedDoor;
        if (carDoor == playerDoor) {
            // use zero based door
            int zeroBasedCarDoor = carDoor - 1;
            // random offset of 1 or 2
            int shift = random.nextInt(2) + 1;
            // get modulo (remainder) of dividing by 3 (0, 1, 2)
            int zeroBasedRevealedDoor = (zeroBasedCarDoor + shift) % 3;
            // add 1 back to door
            revealedDoor = zeroBasedRevealedDoor + 1;
        } else {
            revealedDoor = 6 - carDoor - playerDoor;
        }
        return revealedDoor;
    }

    public static int getFinalPlayerDoor(Scanner console, int playerDoor, int revealedDoor) {
        return readBoolean(console, "Switch? [y/n] ")
                ? 6 - playerDoor - revealedDoor
                : playerDoor;
    }

    public static void printResult(boolean winner) {
        String message = winner
                ? "You won a car!"
                : "You got a goat!";
        System.out.println(message);
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
        String input;
        boolean isValid;
        do {
            input = readString(console, prompt);
            isValid = input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n");
            if (!isValid) {
                System.out.println("I don't recognize that option");
            }
        } while(!isValid);
        return input.equalsIgnoreCase("y");
    }

    public static String readString(Scanner console, String prompt) {
        System.out.println(prompt);
        return console.nextLine();
    }
}
