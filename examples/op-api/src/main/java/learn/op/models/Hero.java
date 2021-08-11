package learn.op.models;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private int id;
    private String alias;
    private String fullName;
    private String faction;
    private String imgUrl;
    private List<Ability> abilities;

    public Hero() { }

    public Hero(int id, String alias, String fullName, String faction, String imgUrl, List<Ability> abilities) {
        this.id = id;
        this.alias = alias;
        this.fullName = fullName;
        this.faction = faction;
        this.imgUrl = imgUrl;
        this.abilities = abilities;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public static List<Hero> makeHeroes() {
        return new ArrayList<>(
                List.of(
                        new Hero(1, "Little Jenny", "Jenny Tsang", "Red Dragons",
                                "https://cdn.omicronprotocol.com/img/cards/LittleJennyBio.png",
                                List.of(
                                    new Ability(1, true, "Taunt"),
                                    new Ability(2, false, "Gang Fighting")
                                )
                        ),
                        new Hero(2, "Artemis", "Kyanna “Artemis” Osei", "Survivalists",
                                "https://cdn.omicronprotocol.com/img/cards/ArtemisBio.png",
                                List.of(
                                    new Ability(3, true, "Double Shot"),
                                    new Ability(4, true, "Place Snare")
                                )
                        ),
                        new Hero(3, "Jane", "Jane", "Animals",
                                "https://cdn.omicronprotocol.com/img/cards/JaneBio.png",
                                List.of(
                                    new Ability(5, true, "Act Cute"),
                                    new Ability(6, false, "9 Lives")
                                )
                        ),
                        new Hero(4, "Lance", "Felix Chevalier", "Peacemakers",
                                "https://cdn.omicronprotocol.com/img/cards/LanceBio.png",
                                List.of(
                                    new Ability(7, false, "Cavalry Charge"),
                                    new Ability(8, false, "Equestrian")
                                )
                        )
                )
        );
    }
}
