package corbos.data;

import corbos.models.Hero;

import java.util.List;

public class HeroRepository extends BaseRepository<Hero> {

    private static final String HEADER = "id,name";

    public HeroRepository(String filePath) {
        super(filePath, 2, HEADER);
    }

    public Hero findById(int heroId) {
        return filterOne(hero -> hero.getId() == heroId);
    }

    public List<Hero> findNameStartsWith(String prefix) {
        return filter(hero -> hero.getName().startsWith(prefix));
    }

    public Hero add(Hero hero) {
        List<Hero> all = findAll();
        hero.setId(all.stream()
                .mapToInt(h -> h.getId())
                .max().orElse(0) + 1);
        all.add(hero);
        write(all);
        return hero;
    }

    @Override
    protected String serialize(Hero hero) {
        return String.format("%s,%s",
                hero.getId(),
                hero.getName());
    }

    @Override
    protected Hero deserialize(String[] fields) {
        Hero hero = new Hero();
        hero.setId(Integer.parseInt(fields[0]));
        hero.setName(fields[1]);
        return hero;
    }
}
