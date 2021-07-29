package corbos.data;

import corbos.models.Pokemon;

public class PokemonRepository extends BaseRepository<Pokemon> {

    private final static String HEADER = "Name,Total,HP,Attack,Defence,Sp_attack,Sp_defence,Speed";

    public PokemonRepository(String filePath) {
        super(filePath, 8, HEADER);
    }

    @Override
    protected String serialize(Pokemon pokemon) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                pokemon.getName(),
                pokemon.getTotal(),
                pokemon.getHitPoints(),
                pokemon.getAttack(),
                pokemon.getDefense(),
                pokemon.getSpecialAttack(),
                pokemon.getSpecialDefense(),
                pokemon.getSpeed());
    }

    @Override
    protected Pokemon deserialize(String[] fields) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(fields[0]);
        pokemon.setTotal(Integer.parseInt(fields[1]));
        pokemon.setHitPoints(Integer.parseInt(fields[2]));
        pokemon.setAttack(Integer.parseInt(fields[3]));
        pokemon.setDefense(Integer.parseInt(fields[4]));
        pokemon.setSpecialAttack(Integer.parseInt(fields[5]));
        pokemon.setSpecialDefense(Integer.parseInt(fields[6]));
        pokemon.setSpeed(Integer.parseInt(fields[7]));
        return pokemon;
    }
}
