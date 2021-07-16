package learn.recordcollection.ui;

import learn.recordcollection.data.DataAccessException;
import learn.recordcollection.domain.RecordResult;
import learn.recordcollection.domain.RecordService;
import learn.recordcollection.models.Record;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Controller {
    private final RecordService service;
    private final View view;

    public Controller(RecordService service, View view) {
        this.service = service;
        this.view = view;
    }

<<<<<<< HEAD
    public void tryRun(){
        view.printHeader("Welcome to Record Collection");
        try {
            run();
        } catch (DataAccessException e){
            //TODO
        }
    }

    private void run() throws DataAccessException{
        boolean run = true;
        while (run) {
            int menuSelection = view.getMenuSelection();
            switch (menuSelection) {
                case 0:
                    run = false;
                    System.out.println("Goodbye!"); break;
                case 1:
                    showAllRecords(); break;
                case 2:
                    showRecordsByArtist(); break;
                case 3:
                    add(); break;
                case 4:
                    update(); break;
                case 5:
                    delete(); break;
                default:
                    System.out.printf("%s is not a valid option.%n", menuSelection);
            }
        }
    }

    private void showAllRecords() throws DataAccessException{
        List<Record> records = service.findAll();
        view.displayRecords(records, "All Records");
    }

    private void showRecordsByArtist() throws DataAccessException {
        view.printHeader("Records By Artist");
        String artist = view.readString("Artist: ");
        List<Record> records = service.findByArtist(artist);
        view.displayRecords(records, String.format("Records by %s", artist.trim()));
    }

    private void add() throws DataAccessException {
        view.printHeader("Add a record");
        Record newRecord = view.makeRecord();
        RecordResult result = service.add(newRecord);
        view.displayResult(result);
    }

    private void update() throws DataAccessException {
        view.printHeader("Update a record");
        List<Record> all = service.findAll();
        Record record = view.findRecord(all);
        if (record == null){
            System.out.println("Could not find record with given id.");
        } else {
            record = view.update(record);
            RecordResult result = service.update(record);
            view.displayResult(result);
        }
    }

    private void delete(){
        //TODO
    }

=======
    public void run() {
        view.displayHeader("Welcome to Your record Collection!");
        try {
            runMenu();
        } catch (DataAccessException ex) {
            view.displayText(ex.getMessage());
        }
        view.displayText("Bye!");
    }

    private void runMenu() throws DataAccessException {
        int selection;
        do {
            selection = view.getMenuSelection();
            switch (selection) {
                case 0:
                    break;
                case 1:
                    viewRecords();
                    break;
                case 2:
                    viewRecordsByArtist();
                    break;
                case 3:
                    addRecord();
                    break;
                case 4:
                    updateRecord();
                    break;
                case 5:
                    deleteRecord();
                    break;
                default:
                    view.displayText(String.format("%s is not a valid option.", selection));
            }
        } while (selection != 0);
    }

    private void viewRecords() throws DataAccessException {
        List<Record> records = service.findAll();
        view.displayRecords(records, "Records");
    }

    private void viewRecordsByArtist() throws DataAccessException {
        String artist = view.readArtist();
        List<Record> records = service.findByArtist(artist);
        view.displayRecords(records, String.format("Records by `%s`", artist.trim()));
    }

    private void addRecord() throws DataAccessException {
        Record record = view.makeRecord();
        RecordResult result = service.add(record);
        if (result.isSuccess()) {
            view.displayText(String.format("`%s` by `%s` successfully added!",
                    record.getTitle(), record.getArtist()));
        } else {
            view.displayErrors(result.getMessages());
        }
    }

    private void updateRecord() throws DataAccessException {
        String artist = view.readArtist();
        List<Record> records = service.findByArtist(artist);
        Record record = view.getRecord(records, String.format("Records by `%s`", artist.trim()));
        if (record != null) {
            record = view.update(record);
            RecordResult result = service.update(record);
            if (result.isSuccess()) {
                view.displayText(String.format("`%s` by `%s` successfully updated!",
                        record.getTitle(), record.getArtist()));
            } else {
                view.displayErrors(result.getMessages());
            }
        }
    }

    private void deleteRecord() throws DataAccessException {
        String artist = view.readArtist();
        List<Record> records = service.findByArtist(artist);
        Record record = view.getRecord(records, String.format("Records by `%s`", artist.trim()));
        if (record != null) {
            boolean delete = view.confirmDelete(record);
            if (delete) {
                RecordResult result = service.deleteById(record.getRecordId());
                if (result.isSuccess()) {
                    view.displayText("Record successfully deleted!");
                } else {
                    view.displayErrors(result.getMessages());
                }
            }
        }
    }
>>>>>>> eb983f0c1b1af51da999e65215d750aaa7907d11
}
