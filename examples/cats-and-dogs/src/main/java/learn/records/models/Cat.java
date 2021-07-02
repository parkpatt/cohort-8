package learn.records.models;

public class Cat {
    private String breed;
    private String name;
    private String registry;

    public Cat() {
    }

    public Cat(String breed, String name, String registry) {
        this.breed = breed;
        this.name = name;
        this.registry = registry;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public String getOwnerName() {
        return ""; // ommited
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        if (!breed.equals(cat.breed)) return false;
        if (!name.equals(cat.name)) return false;
        return registry.equals(cat.registry);
    }

    @Override
    public int hashCode() {
        int result = breed.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + registry.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", registry='" + registry + '\'' +
                '}';
    }
}
