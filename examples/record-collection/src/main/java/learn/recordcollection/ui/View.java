package learn.recordcollection.ui;

import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class View {

    private TextIO textIO = new ConsoleIO();

    @Autowired
    public void setTextIO(TextIO textIO) {
        this.textIO = textIO;
    }

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
        if (records.size() == 0) {
            displayText("No records found.");
        } else {
            String tableHeader = String.format("#  %-15s  %-25s  %-3s   %7s", "Artist", "Title", "", "$ Value");
            displayTableHeader(title, tableHeader);
            for (int i = 0; i < records.size(); i++) {
                displayText(recordRow(i, records.get(i)));
            }
            printBorder(tableHeader.length());
        }
    }

    private void displayTableHeader(String title, String tableHeader) {
        displayHeader(title);
        displayText(tableHeader);
        printBorder(tableHeader.length());
    }

    private String recordRow(int index, Record record) {
        return String.format("%s. %-15s  %-25s  %-3s   %7s",
                index + 1,
                record.getArtist(),
                record.getTitle(),
                record.getCondition().getAbbreviation(),
                String.format("%,.2f", record.getValue())
        );
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
        displayRecords(records, title);
        if (records.size() == 0) return null;

        int selection;
        do {
            selection = readInt("Record: ");
        } while (selection < 1 || selection > records.size());
        return records.get(selection - 1);
    }

    public Record update(Record record) {
        String artist = readString(String.format("Artist (%s):", record.getArtist()));
        if (!artist.isBlank()) {
            record.setArtist(artist);
        }
        String title = readString(String.format("Title (%s):", record.getTitle()));
        if (!title.isBlank()) {
            record.setTitle(title);
        }
        record.setCondition(readCondition(String.format("Condition (%s): ",
                record.getCondition().getAbbreviation())));
        record.setValue(readDouble(String.format("Value (%,.2f)", record.getValue())));
        return record;
    }

    public boolean confirmDelete(Record record) {
        displayHeader("Delete");
        displayText("Are you sure you want to delete this record?");
        displayText(recordRow(1, record));
        return readBoolean("Delete? [Y]: ");
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

    private void printBorder(int length) {
        System.out.println("-".repeat(length));
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
                displayText(String.format("'%s' is not a valid number.%n", value));
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            String value = readString(prompt);
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException ex) {
                displayText(String.format("'%s' is not a valid number.%n", value));
            }
        }
    }

    private boolean readBoolean(String prompt) {
        String value = readString(prompt).toUpperCase().trim();
        return value.length() > 0 && value.charAt(0) == 'Y';
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
                displayText(String.format("'%s' is not a Condition number.%n", selection));
            }
        }
    }
}
