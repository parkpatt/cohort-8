package learn.roguelike.characters.movements;

public interface Movement {
    Position move(Position target, Position currentPosition);
}
