import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Amusement park tickets
        // $10.50 - adult
        // TODO: $7 - students
        // $5 - kids (12 - 2)
        // $1 - senior (65 or older)
        // $0 - 2 or less

        // [X] Get a name
        // [X] Ask how many people
        // [X] Ask age per person
        // [X] Calculate total price
        // [ ] Show total price (2 decimal places)

        Scanner console = new Scanner(System.in);

        System.out.print("What is your name? ");
        String name = console.nextLine();

        System.out.print("How many people in your group? ");
        int groupSize = Integer.parseInt(console.nextLine());

        double totalPrice = 0;
        for (int i = 0; i < groupSize; i++) {

            int selection;

            do {
                System.out.printf("How old is person %s?%n", i + 1);
                System.out.println("1. Adult");
                System.out.println("2. Kid (2-12)");
                System.out.println("3. Senior (65 or over)");
                System.out.println("4. Baby");
                System.out.print("Choose [1-4]: ");

                selection = Integer.parseInt(console.nextLine());

                switch (selection) {
                    case 1:
                        totalPrice += 10.5;
                        break;
                    case 2:
                        totalPrice += 5;
                        break;
                    case 3:
                        totalPrice += 1;
                        break;
                    case 4:
                        break;
                    default:
                        System.out.printf("%s is not a valid selection.%n", selection);
                        System.out.println();
                        break;
                }
            } while (selection < 1 || selection > 4);

            //int age = Integer.parseInt(console.nextLine());
//            if (age >= 65) {
//                totalPrice += 1;
//            } else if (age > 12) {
//                totalPrice += 10.5;
//            } else if (age > 2) {
//                totalPrice += 5;
//            }
        }

        String formatString = "%n%s, your total price for this ride is: $%.2f%n";
        System.out.printf(formatString, name, totalPrice);

    }
}
