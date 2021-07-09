package learn.recordcollection.ui;

import learn.recordcollection.data.DataAccessException;
import learn.recordcollection.domain.RecordResult;
import learn.recordcollection.domain.RecordService;
import learn.recordcollection.models.Record;

import java.util.List;

public class Controller {
    private final RecordService service;
    private final View view;

    public Controller(RecordService service, View view) {
        this.service = service;
        this.view = view;
    }

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

}
