public class Car {
    private final Model model;
    private final int year;
    private final int maxMph;
    private int currentMph;

    public Car(Model model, int year, int maxMph) {
        this.model = model;
        this.year = year;
        this.maxMph = maxMph;
    }

    public Model getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setCurrentMph(int currentMph) {
        if (currentMph > maxMph || currentMph < 0) {
            return;
        }
        this.currentMph = currentMph;
    }

    public void accelerate(int mph) {
        this.currentMph = Math.min(currentMph + mph, maxMph);
    }

    @Override
    public String toString() {
        return String.format("Make: %s, Model: %s, Year: %s", getModel().getMake(), getModel().getName(), getYear());
    }
}
