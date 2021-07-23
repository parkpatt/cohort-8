package learn.avengers.ui;

import learn.avengers.data.DataAccessException;
import learn.avengers.domain.AvengerService;
import learn.avengers.models.Avenger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Controller {

    private final View view;
    private final AvengerService service;

    public Controller(View view, AvengerService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        try {
            runMenu();
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
        view.displayText("Bye!");
    }

    private void runMenu() throws DataAccessException {
        boolean exit = false;
        do {
            int selection = view.getMenuSelection();
            switch (selection) {
                case 0:
                    exit = view.confirmExit();
                    break;
                case 1:
                    viewAvengers();
                    break;
            }
        } while (!exit);
    }

    private void viewAvengers() throws DataAccessException {
        List<Avenger> avengers = service.findAll();
        view.viewAvengers(avengers);
    }
}
