package learn.roguelike.characters;

import learn.roguelike.characters.movements.Position;

public interface Character {
    String getName();
    char getSymbol();
    Position getPosition();
    int performAttack();
    void performMovement(Position target);
}
