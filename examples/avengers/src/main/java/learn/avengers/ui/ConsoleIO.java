package learn.avengers.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleIO implements TextIO {

    private final Scanner console = new Scanner(System.in);

    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public void printf(String text, Object... args) {
        System.out.printf(text, args);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return console.nextLine();
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        while(true) {
            String input = "";
            try {
                input = readString(prompt);
                int result = Integer.parseInt(input);
                if (result >= min && result <= max) {
                    return result;
                }
            } catch (NumberFormatException ex) {
                printf("'%s' is not a valid number", input);
            }
        }
    }

    @Override
    public boolean readBoolean(String prompt) {
        return readString(prompt).equalsIgnoreCase("Y");
    }
}
