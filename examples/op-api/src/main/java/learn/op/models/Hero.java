package learn.op.models;

import learn.op.validations.MustHaveMinTwoAbilities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Hero {
    private int id;

    @NotBlank(message = "Alias is required")
    private String alias;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Faction is required")
    private String faction;

    @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()!@:%_\\+.~#?&\\/\\/=]*)",
            message = "Image url must be a valid url")
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
        Map<Integer, Ability> abilitiesMap = Ability.abilitiesMap();
        return new ArrayList<>(
                List.of(
                        new Hero(1, "Little Jenny", "Jenny Tsang", "Red Dragons",
                                "https://cdn.omicronprotocol.com/img/cards/LittleJennyBio.png",
                                List.of(abilitiesMap.get(1), abilitiesMap.get(2))
                        ),
                        new Hero(2, "Artemis", "Kyanna “Artemis” Osei", "Survivalists",
                                "https://cdn.omicronprotocol.com/img/cards/ArtemisBio.png",
                                List.of(abilitiesMap.get(3), abilitiesMap.get(4))
                        ),
                        new Hero(3, "Jane", "Jane", "Animals",
                                "https://cdn.omicronprotocol.com/img/cards/JaneBio.png",
                                List.of(abilitiesMap.get(5), abilitiesMap.get(6))
                        ),
                        new Hero(4, "Lance", "Felix Chevalier", "Peacemakers",
                                "https://cdn.omicronprotocol.com/img/cards/LanceBio.png",
                                List.of(abilitiesMap.get(7), abilitiesMap.get(8))
                        )
                )
        );
    }
}
