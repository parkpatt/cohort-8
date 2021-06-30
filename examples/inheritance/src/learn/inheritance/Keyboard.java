package learn.inheritance;

public class Keyboard extends KeyedInstrument {
    public Keyboard(String tuningSystem, String name, int numberOfKeys) {
        super(tuningSystem, name, numberOfKeys);
    }

    @Override
    public String getName() {
        String name = super.getName();
        return String.format("Keyboard: %s", name);
    }
}
