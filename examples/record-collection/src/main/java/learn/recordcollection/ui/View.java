package learn.recordcollection.ui;

<<<<<<< HEAD
import learn.recordcollection.domain.RecordResult;
import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
=======
import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
>>>>>>> eb983f0c1b1af51da999e65215d750aaa7907d11
import java.util.Scanner;

@Component
public class View {
<<<<<<< HEAD
    private final Scanner console = new Scanner(System.in);

    public int getMenuSelection() {
        printHeader("Main Menu");
        System.out.println("0. Exit");
        System.out.println("1. View All Records");
        System.out.println("2. View Records by Artist");
        System.out.println("3. Add Record");
        System.out.println("4. Update Record");
        System.out.println("5. Delete Record");
        return readInt("Select [0-5]: ", 0, 5);
    }

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

    public Record makeRecord() {
        Record result = new Record();
        result.setArtist(readRequiredString("Artist: "));
        result.setTitle(readRequiredString("Title: "));
=======

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
>>>>>>> eb983f0c1b1af51da999e65215d750aaa7907d11
        result.setCondition(readCondition("Condition: "));
        result.setValue(readDouble("Value: "));
        return result;
    }

<<<<<<< HEAD
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

    public String readString(String message) {
        System.out.print(message);
        return console.nextLine();
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

    public Condition readCondition(String prompt) {
        printHeader("Condition Options");
        for (Condition condition : Condition.values()) {
            System.out.println(condition.toString());
        }

=======
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
>>>>>>> eb983f0c1b1af51da999e65215d750aaa7907d11
        while (true) {
            String selection = readString(prompt).toUpperCase().trim();
            try {
                return Condition.valueOf(selection);
            } catch (IllegalArgumentException ex) {
<<<<<<< HEAD
                System.out.printf("%s is not a valid Condition.%n", selection);
            }
        }
    }


=======
                displayText(String.format("'%s' is not a Condition number.%n", selection));
            }
        }
    }
>>>>>>> eb983f0c1b1af51da999e65215d750aaa7907d11
}
