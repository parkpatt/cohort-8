package learn.models;

public class Model {
    private final String name;
    private final Make make;

    public String getName() {
        return name;
    }

    public Make getMake() {
        return make;
    }

    public Model(String name, Make make) {
        this.name = name;
        this.make = make;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", make=" + make +
                '}';
    }
}
