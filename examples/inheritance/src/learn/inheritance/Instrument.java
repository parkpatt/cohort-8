package learn.inheritance;

public class Instrument {
    private final String tuning;
    private final String name;

    public Instrument(String tuning, String name) {
        this.tuning = tuning;
        this.name = name;
    }

    public String getTuning() {
        return tuning;
    }

    public String getName() {
        return name;
    }
}
