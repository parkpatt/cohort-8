package learn.op.models;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ability {
    private int Id;
    private String name;

    public Ability(int id, String name) {
        Id = id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Map<Integer, Ability> abilitiesMap() {
        List<Ability> abilities = List.of(
                new Ability(1, "Taunt"),
                new Ability(2, "Gang Fighting"),
                new Ability(3, "Double Shot"),
                new Ability(4, "Place Snare"),
                new Ability(5, "Act Cute"),
                new Ability(6,  "9 Lives"),
                new Ability(7, "Cavalry Charge"),
                new Ability(8, "Equestrian")
        );

        return abilities.stream()
                .collect(Collectors.toMap(a -> a.Id, a -> a));
    }
}
