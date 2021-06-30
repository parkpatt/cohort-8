package learn.roguelike.characters.movements;

public class AggressiveMovement implements Movement {

    private final int speed = 2;

    @Override
    public Position move(Position target, Position current) {


        return new Position(1, 1);
    }
}
