package learn.inheritance;

public class App {
    public static void main(String[] args) {
        Keyboard keyboard = new Keyboard("Equal Temperment", "Piano", 88);

        Guitar guitar = new Guitar("Pythagorean", "Fender", 6, true, true);

        System.out.println(guitar.getName());
        System.out.println(keyboard.getName());
    }
}
