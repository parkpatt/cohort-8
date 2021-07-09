package learn.recordcollection.ui;

import learn.recordcollection.domain.RecordResult;
import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class View {
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
