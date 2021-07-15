package learn.models;

public class Make {
    private final String name;

    public String getName() {
        return name;
    }

    public Make(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Make{" +
                "name='" + name + '\'' +
                '}';
    }
}
