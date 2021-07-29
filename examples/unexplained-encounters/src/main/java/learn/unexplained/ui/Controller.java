package learn.unexplained.ui;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.domain.EncounterResult;
import learn.unexplained.domain.EncounterService;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.List;

public class Controller {

    private final EncounterService service;
    private final View view;

    public Controller(EncounterService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        view.printHeader("Welcome To Unexplained Encounters.");

        try {
            runMenuLoop();
        } catch (DataAccessException ex) {
            view.printHeader("CRITICAL ERROR:" + ex.getMessage());
        }

        view.printHeader("Goodbye");
    }

    private void runMenuLoop() throws DataAccessException {
        MenuOption option;
        do {
            option = view.displayMenuGetOption();
            switch (option) {
                case DISPLAY_ALL:
                    displayAllEncounters();
                    break;
                case FIND_BY_TYPE:
                    displayByType();
                    break;
                case ADD:
                    addEncounter();
                    break;
                case UPDATE:
                    updateEncounter();
                    break;
                case DELETE:
                    deleteEncounter();
                    break;
            }
        } while (option != MenuOption.EXIT);
    }


    private void displayAllEncounters() throws DataAccessException {
        List<Encounter> encounters = service.findAll();
        view.printEncounters(MenuOption.DISPLAY_ALL.getMessage(), encounters);
    }

    private void displayByType() throws DataAccessException {
        EncounterType type = view.readType(MenuOption.FIND_BY_TYPE.getMessage());
        List<Encounter> encounters = service.findByType(type);
        view.printEncounters(null, encounters);
    }

    private void addEncounter() throws DataAccessException {
        Encounter encounter = view.makeEncounter();
        EncounterResult result = service.add(encounter);
        view.printResult(result);
    }

    private void updateEncounter() throws DataAccessException {
        EncounterType type = view.readType(MenuOption.UPDATE.getMessage());
        List<Encounter> encounters = service.findByType(type);
        Encounter encounter = view.chooseEncounter(encounters);
        if (encounter == null) {
            return;
        }
        encounter = view.update(encounter);
        EncounterResult result = service.update(encounter);
        view.printResult(result);
    }

    private void deleteEncounter() throws DataAccessException {
        int encounterId = view.readEncounterId(MenuOption.DELETE.getMessage());
        boolean success = service.deleteById(encounterId);
        if (success) {
            view.print("Encounter successfully deleted.");
        } else {
            view.print("ERR: Encounter ID " + encounterId + " not found.");
        }
    }
}
