package learn.roguelike.characters.attacks;

import java.util.Random;

public class Claw implements Attack {
    private final Random random = new Random();

    @Override
    public String getName() {
        return "Claw";
    }

    @Override
    public int doDamage() {
        return random.nextInt(2) + 1;
    }
}
