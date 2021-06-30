public class Animal {
    private final String species;
    private String name;
    private final int accelerationMph;

    private int currentMph = 0;

    private final static int MIN_MPH = 0;

    public Animal(String species, String name, int accelerationMph) {
        this.accelerationMph = accelerationMph;
        this.species = species;
        this.name = name;
    }

    public void run() {
        currentMph += accelerationMph;
    }

    public void decelerate() {
        currentMph = Math.max(currentMph - accelerationMph, MIN_MPH);
    }

    public String getSpecies() {
        return species;
    }

}
