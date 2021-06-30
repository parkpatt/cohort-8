package learn.inheritance;

public class KeyedInstrument extends Instrument {

    private final int numberOfKeys;

    public KeyedInstrument(String tuningSystem, String name, int numberOfKeys) {
        super(tuningSystem, name);
        this.numberOfKeys = numberOfKeys;
    }
}
