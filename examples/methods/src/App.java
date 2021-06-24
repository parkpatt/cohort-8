public class App {

    public static void main(String[] args) {
        String fullName = getFullName("Brendan", "Kendrick");

        int factorialOfTen = getFactorial(2);

        System.out.println(factorialOfTen);
    }

    public static int getFactorial(int num) {
        //return num == 0 ? 1 : num * getFactorial(num - 1);


        if (num == 0) {
            return 1;
        } else {
            return num * getFactorial(num - 1);
        }
    }


    public static void printHello() {
        System.out.println("Hello!");
    }

    public static void printHello(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public static String getFullName(String firstName, String lastName) {
        return "";
    }

    public static int add(int a, int b) {
        return 0;
    }
}
