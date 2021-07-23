package learn.avengers.ui;

import learn.avengers.models.Avenger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class View {

    private final TextIO io;

    public View(TextIO io) {
        this.io = io;
    }

    public int getMenuSelection() {
        displayHeader("Main Menu");
        displayText("0. Exit");
        displayText("1. View Avengers");
        return io.readInt("Select [0-1]: ", 0, 1);
    }

    public void viewAvengers(List<Avenger> avengers) {
        displayHeader("Avengers");
        for (Avenger avenger : avengers) {
            io.println(avenger.toString());
        }
    }

    public boolean confirmExit() {
        return io.readBoolean("Are you sure you want to leave? [Y/N] ");
    }

    public void displayHeader(String text) {
        io.println(text);
        io.println("=".repeat(text.length()));
    }

    public void displayText(String text) {
        io.println(text);
    }
}
