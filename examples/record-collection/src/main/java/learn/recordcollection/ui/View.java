package learn.recordcollection.ui;

import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;

import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner console = new Scanner(System.in);

    public int getMenuSelection() {
        displayHeader("Main Menu");
        displayText("0. Exit");
        displayText("1. View Record Collection");
        displayText("2. View Records by Artist");
        displayText("3. Add a Record");
        displayText("4. Update a Record");
        displayText("5. Delete a Record");
        return readInt("Select [0-5]: ");
    }

    public void displayRecords(List<Record> records, String title) {
        displayRecords(records, title, false);
    }

    public void displayRecordsMenu(List<Record> records, String title) {
        displayRecords(records, title, true);
    }

    private void displayRecords(List<Record> records, String title, boolean showMenuOptions) {
        displayHeader(title);
        for (int i = 0; i < records.size(); i++) {
            String recordText = showMenuOptions
                    ? String.format("%s. %s", i + 1, records.get(i).toString())
                    : records.get(i).toString();
            displayText(recordText);
        }
    }

    public Record makeRecord() {
        Record result = new Record();
        result.setArtist(readString("Artist: "));
        result.setTitle(readString("Title: "));
        result.setCondition(readCondition("Condition: "));
        result.setValue(readDouble("Value: "));
        return result;
    }

    public Record getRecord(List<Record> records, String title) {
        displayRecordsMenu(records, title);
        int selection;
        do {
            selection = readInt("Record: ");
        } while (selection < 1 || selection >= records.size());

        return records.get(selection - 1);
    }

    public Record update(Record record) {
        record.setArtist(readString("Artist: "));
        record.setTitle(readString("Title: "));
        record.setCondition(readCondition("Condition: "));
        record.setValue(readDouble("Value: "));
        return record;
    }

    public void displayHeader(String text) {
        displayText("");
        displayText(text);
        displayText("=".repeat(text.length()));
    }

    public void displayText(String text) {
        System.out.println(text);
    }

    public void displayErrors(List<String> errors) {
        displayHeader("Errors!");
        for (String err : errors) {
            displayText(err);
        }
    }

    public String readArtist() {
        return readString("Artist: ");
    }

    private String readString(String prompt) {
        displayText(prompt);
        return console.nextLine();
    }

    private int readInt(String prompt) {
        while (true) {
            String value = readString(prompt);
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                System.out.printf("'%s' is not a valid number.%n", value);
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            String value = readString(prompt);
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException ex) {
                System.out.printf("'%s' is not a valid number.%n", value);
            }
        }
    }

    private Condition readCondition(String prompt) {
        displayHeader("Conditions");
        for (Condition condition : Condition.values()) {
            displayText(condition.toString());
        }
        while (true) {
            String selection = readString(prompt).toUpperCase().trim();
            try {
                return Condition.valueOf(selection);
            } catch (IllegalArgumentException ex) {
                System.out.printf("'%s' is not a valid Condition.%n", selection);
            }
        }
    }
}
