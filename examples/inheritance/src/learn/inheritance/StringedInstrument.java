package learn.inheritance;

public class StringedInstrument extends Instrument {

    private final int numberOfStrings;
    private final boolean isElectric;

    public StringedInstrument(String tuningSystem, String name, int numberOfStrings, boolean isElectric) {
        super(tuningSystem, name);
        this.numberOfStrings = numberOfStrings;
        this.isElectric = isElectric;
    }
}
