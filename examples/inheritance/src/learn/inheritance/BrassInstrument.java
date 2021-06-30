package learn.inheritance;

public class BrassInstrument extends Instrument {

    private final boolean hasMouthPiece = true;
    private final int numberOfValves;

    public BrassInstrument(String tuningSystem, String name, int numberOfValves) {
        super(tuningSystem, name);
        this.numberOfValves = numberOfValves;
    }
}
