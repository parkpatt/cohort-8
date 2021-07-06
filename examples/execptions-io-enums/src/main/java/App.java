import learn.exceptions.TooBigCheckedException;
import learn.exceptions.TooBigException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void ioReadLines() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("some-file"));

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nioDelete() {
        Path filePath = Paths.get("another-file.txt");
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            System.out.println("Not there!");
        }
    }

    public static void fileDelete() {
        File file = new File("some-file");

        boolean deleted = file.delete();

        if (deleted) {
            System.out.println("Deleted");
        } else {
            System.out.println("Not deleted");
        }
    }

    public static void nioReadLines() {
        Path filePath = Paths.get("another-file.txt");
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nioAppend() {
        Path filePath = Paths.get("another-file.txt");
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Helium");
        lines.add("Oxygen");
        lines.add("Hydrogen");
        try {
            Files.write(filePath, lines, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nioWriteFile() {
        Path filePath = Paths.get("another-file.txt");
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Helium");
        lines.add("Oxygen");
        lines.add("Hydrogen");
        try {
            Files.write(filePath, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void twoCheckedExceptions() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("some-file", true))) {
            printWriter.println("apple");
            printWriter.println("orange");
            printWriter.println("banana");

            int tooBig = getBigInt();

        } catch (IOException | TooBigCheckedException ex) {
            ex.printStackTrace();
        }
    }

    // throws checked exception
    public static int getBigInt() throws TooBigCheckedException {
        throw new TooBigCheckedException("Too darn big");
    }

    public static void swallowException() {
        int age = 0;
        try {
            age = Integer.parseInt("Not an age");
        } catch (NumberFormatException ex) {
            // not a great idea in this case
        }
        System.out.println(age);
    }

    public static void throwRuntimeException() {
        int num = 1000;
        if (num > 999) {
            throw new TooBigException("Too dang big");
        }
    }

    public static void numberFormatException() {
        try {
            int num = Integer.parseInt("BAD");
            System.out.println("Made it");
        } catch (NumberFormatException e) {
            System.out.println("Oops");
        }
    }

    public static void ioAppendWithFilerWriter() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("some-file", true))) {
            printWriter.println("apple");
            printWriter.println("orange");
            printWriter.println("banana");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void ioCreatePrintWriter() {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("some-file");
            printWriter.println("apple");
            printWriter.println("orange");
            printWriter.println("banana");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
