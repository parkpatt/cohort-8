package learn.recordcollection.ui;


import learn.recordcollection.domain.RecordResult;
import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.springframework.stereotype.Component;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Scanner;

@Component
public class View {
    private final Scanner console = new Scanner(System.in);

    public void printHeader(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    public void displayRecords(List<Record> records, String title) {
        printHeader(title);
        for (Record r : records) {
            System.out.printf(
                    "%d. %s: %s. %s. $%.2f%n",
                    r.getRecordId(),
                    r.getArtist(),
                    r.getTitle(),
                    r.getCondition().toString(),
                    r.getValue()
            );
        }
    }

    public void displayResult(RecordResult result) {
        if (result.isSuccess()) {
            printHeader("Success!");
        } else {
            printHeader("Err:");
            for (String err : result.getMessages()) {
                System.out.println(err);
            }
        }
    }

    private TextIO textIO = new ConsoleIO();

    @Autowired
    public void setTextIO(TextIO textIO) {
        this.textIO = textIO;
    }

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

    public Record findRecord(List<Record> records) {
        displayRecords(records, "Records by Id: ");
        if (records.size() == 0) {
            return null;
        }
        int recordId = readInt("Record Id: ");
        for (Record r : records) {
            if (r.getRecordId() == recordId) {
                return r;
            }
        }
        System.out.println("record Id " + recordId + " not found.");
        return null;
    }

    public Record update(Record record) {
        String artist = readString("Artist (" + record.getArtist() + "): ");
        if (artist.trim().length() > 0) {
            record.setTitle(artist);
        }
        String title = readString("Sponsor (" + record.getTitle() + "): ");
        if (title.trim().length() > 0) {
            record.setTitle(title);
        }
        String condition = readString("Condition (" + record.getCondition().toString() + "): ");
        if (condition.trim().length() > 0) {
            while (true) {
                try {
                    record.setCondition(Condition.valueOf(condition));
                    break;
                } catch (IllegalArgumentException ex) {
                    System.out.printf("%s is not a valid Condition.%n", condition);
                    condition = readString("Condition (" + record.getCondition().toString() + "): ");
                }
            }
        }
        String value = readString("Value (" + record.getValue() + "): ");
        if (value.trim().length() > 0){
            while(true) {
                try {
                    record.setValue(Double.parseDouble(value));
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.printf("%s is not a valid price.%n", value);
                    value = readString("Value (" + record.getValue() + "): ");
                }
            }
        }
        return record;
    }

    public String readRequiredString(String message) {
        String result;
        do {
            result = readString(message);
            if (result.trim().length() == 0) {
                System.out.println("Value is required.");
            }
        } while (result.trim().length() == 0);
        return result;
    }

    private int readInt(String message) {
        String input = null;
        int result = 0;
        boolean isValid = false;
        do {
            try {
                input = readRequiredString(message);
                result = Integer.parseInt(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("%s is not a valid number.%n", input);
            }
        } while (!isValid);

        return result;
    }

    private int readInt(String prompt, int min, int max) {
        int result = 0;
        do {
            result = readInt(prompt);
            if (result < min || result > max) {
                System.out.printf("Value must be between %s and %s%n", min, max);
            }
        } while (result < min || result > max);
        return result;
    }

    public double readDouble(String prompt) {
        String input = null;
        double result = 0;
        boolean isValid = false;
        do {
            try {
                input = readRequiredString(prompt);
                result = Double.parseDouble(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("%s is not a valid double.%n", input);
            }
        } while (!isValid);

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
                System.out.printf("%s is not a valid Condition.%n", selection);
            }
        }
    }
}
