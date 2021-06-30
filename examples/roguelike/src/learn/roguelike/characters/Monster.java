package learn.roguelike.characters;

import learn.roguelike.characters.attacks.Attack;
import learn.roguelike.characters.movements.Movement;
import learn.roguelike.characters.movements.Position;

public class Monster implements Character, Attacker {
    private final String name;
    private final char symbol = '^';
    private final Attack attack;
    private final Movement movement;
    private Position position;

    // Create a monster with a name and an initial position.
    public Monster(String name, int x, int y, Attack attack, Movement movement) {
        this.name = name;
        this.attack = attack;
        this.movement = movement;
        this.position = new Position(x, y);
    }

    // getters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    public Position getPosition() {
        return position;
    }

    public int performAttack() {
        return attack.doDamage();
    }

    @Override
    public void performMovement(Position target) {
        Position position = movement.move(target, this.position);
        this.position = position;
    }
}
