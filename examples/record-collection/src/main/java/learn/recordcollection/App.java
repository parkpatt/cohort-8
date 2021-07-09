package learn.recordcollection;

import learn.recordcollection.data.RecordFileRepository;
import learn.recordcollection.data.RecordRepository;
import learn.recordcollection.domain.RecordService;
import learn.recordcollection.models.Record;
import learn.recordcollection.ui.View;
import learn.recordcollection.ui.Controller;

import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) {
        RecordRepository repository = new RecordFileRepository("./data/records.csv");
        RecordService service = new RecordService(repository);
        View view = new View();
        Controller controller = new Controller(service, view);
        controller.tryRun();
    }

    public void run(){
        List<Record> stuff = new ArrayList<>();
    }
}
