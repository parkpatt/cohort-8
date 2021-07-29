package learn.unexplained.ui;

public enum MenuOption {
    EXIT("Exit"),
    DISPLAY_ALL("Display All Encounters"),
    FIND_BY_TYPE("Display Encounters By Type"),
    ADD("Add An Encounter"),
    UPDATE("Update An Encounter"),
    DELETE("Delete An Encounter");

    private String message;

    MenuOption(String name) {
        this.message = name;
    }

    public String getMessage() {
        return message;
    }
}
