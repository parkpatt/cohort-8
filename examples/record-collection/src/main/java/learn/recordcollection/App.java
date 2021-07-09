package learn.recordcollection;

import learn.recordcollection.data.RecordFileRepository;
import learn.recordcollection.data.RecordRepository;
import learn.recordcollection.domain.RecordService;
import learn.recordcollection.ui.Controller;
import learn.recordcollection.ui.View;

public class App {
    public static void main(String[] args) {
        RecordRepository repository = new RecordFileRepository("./data/records.csv");
        RecordService service = new RecordService(repository);
        View view = new View();
        Controller controller = new Controller(service, view);
        controller.run();
    }
}
