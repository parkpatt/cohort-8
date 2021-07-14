package learn.recordcollection;

import learn.recordcollection.data.RecordFileRepository;
import learn.recordcollection.data.RecordRepository;
import learn.recordcollection.domain.RecordService;
import learn.recordcollection.ui.Controller;
import learn.recordcollection.ui.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
@PropertySource("data.properties")
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        Controller controller = context.getBean(Controller.class);
        controller.run();
    }

    public static void xmlConfiguration() {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency-configuration.xml");
        Controller controller = context.getBean(Controller.class);
        controller.run();
    }

    public static void manualConfiguration() {
        RecordRepository repository = new RecordFileRepository("./data/records.csv");
        RecordService service = new RecordService(repository);
        View view = new View();
        Controller controller = new Controller(service, view);
        controller.run();
    }
}
