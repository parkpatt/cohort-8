package learn.avengers;

import learn.avengers.data.DataAccessException;
import learn.avengers.ui.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("data.properties")
public class App {
    public static void main(String[] args) throws DataAccessException {
        ApplicationContext container = new AnnotationConfigApplicationContext(App.class);

        container.getBean(Controller.class).run();
    }
}
