package learn.inheritance;

public class Guitar extends StringedInstrument {

    private final boolean hasWhammyBar;

    public Guitar(String tuningSystem, String name, int numberOfStrings, boolean isElectric, boolean hasWhammyBar) {
        super(tuningSystem, name, numberOfStrings, isElectric);
        this.hasWhammyBar = hasWhammyBar;
    }
}
