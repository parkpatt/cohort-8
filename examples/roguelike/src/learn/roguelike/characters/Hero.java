package learn.roguelike.characters;

import learn.roguelike.characters.movements.Position;

public class Hero implements Character {

    private final String name;
    private final char symbol = '@';
    private int hitPoints = 10;
    private final Position position;

    // Create a hero with a name and an initial position.
    public Hero(String name, int x, int y) {
        this.name = name;
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

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public int performAttack() {
        return 0;
    }

    @Override
    public void performMovement(Position target) { }

    public int getHitPoints() {
        return hitPoints;
    }

    // movement
    public void moveLeft() {
        position.setX(position.getX() -1);
    }

    public void moveRight() {
        position.setX(position.getX() + 1);
    }

    public void moveUp() {
        position.setY(position.getY() -1);
    }

    public void moveDown() {
        position.setY(position.getY() + 1);
    }

    public void takeDamage(int damage) {
        this.hitPoints -= damage;
    }
}
