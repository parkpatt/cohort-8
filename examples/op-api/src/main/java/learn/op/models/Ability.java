package learn.op.models;

public class Ability {
    private int Id;
    private boolean active;
    private String name;

    public Ability(int id, boolean active, String name) {
        Id = id;
        this.active = active;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
