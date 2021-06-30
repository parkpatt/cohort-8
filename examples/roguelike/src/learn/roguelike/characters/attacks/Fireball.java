package learn.roguelike.characters.attacks;

import java.util.Random;

public class Fireball implements Attack {
    private final Random random = new Random();

    @Override
    public String getName() {
        return "Fireball";
    }

    @Override
    public int doDamage() {
        return random.nextInt(4) + 1;
    }
}
