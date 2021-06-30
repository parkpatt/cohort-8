package learn.roguelike.game;

import learn.roguelike.characters.*;
import learn.roguelike.characters.Character;
import learn.roguelike.characters.attacks.Attack;
import learn.roguelike.characters.attacks.Claw;
import learn.roguelike.characters.attacks.Fireball;
import learn.roguelike.characters.movements.AggressiveMovement;
import learn.roguelike.characters.movements.DefensiveMovement;
import learn.roguelike.characters.movements.Movement;
import learn.roguelike.items.Treasure;

import java.util.Random;
import java.util.Scanner;

public class Game {

    // constants
    private final static int WIDTH = 10;
    private final static String WALL_CHARACTER = "M";
    private final static String EMPTY_CHARACTER = " ";

    private final Scanner console = new Scanner(System.in);
    private Hero hero;
    private Treasure treasure;
    private Monster monster;
    private boolean isOver;
    private Character[] characters = new Character[2];

    public void run() {
        setUp();
        while (!isOver) {
            printWorld();
            move();
        }
        printWorld();
    }

    private void setUp() {
        System.out.print("What is the name of your hero?: ");
        String name = console.nextLine();

        Random rand = new Random();
        int x = rand.nextInt(WIDTH);
        int y = rand.nextInt(WIDTH);

        hero = new Hero(name, x, y);
        characters[0] = hero;

        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == hero.getPosition().getX() && y == hero.getPosition().getY());

        treasure = new Treasure(x, y);

        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == hero.getPosition().getX() && y == hero.getPosition().getY());

        characters[1] = createMonster(rand, x, y);
    }

    private Monster createMonster(Random rand, int x, int y) {
        Movement movement = rand.nextBoolean()
                ? new AggressiveMovement()
                : new DefensiveMovement();

        Attack attack = rand.nextBoolean()
                ? new Fireball()
                : new Claw();

        return new Monster("Big Bad", x, y, attack, movement);
    }

    private void printWorld() {
        // top wall border
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));

        for (int row = 0; row < WIDTH; row++) {
            // left wall border
            System.out.print(WALL_CHARACTER);
            for (int col = 0; col < WIDTH; col++) {
                Character character = characterAtLocation(row, col);
                if (character != null) {
                    System.out.print(character.getSymbol());
                } else {
                    if (row == treasure.getY() && col == treasure.getX()) {
                        System.out.print("T");
                    } else {
                        System.out.print(EMPTY_CHARACTER);
                    }
                }
            }

            // right wall border
            System.out.println(WALL_CHARACTER);
        }

        // bottom wall border
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));
    }

    private Character characterAtLocation(int row, int col) {
        for (Character character : characters) {
            if (character.getPosition().isAt(row, col)) {
                return character;
            }
        }
        return null;
    }

    private void move() {

        System.out.print(hero.getName() + ", move [WASD]: ");
        String move = console.nextLine().trim().toUpperCase();

        if (move.length() != 1) {
            return;
        }

        switch (move.charAt(0)) {
            case 'W':
                hero.moveUp();
                break;
            case 'A':
                hero.moveLeft();
                break;
            case 'S':
                hero.moveDown();
                break;
            case 'D':
                hero.moveRight();
                break;
        }

        if (hero.getPosition().getX() < 0 || hero.getPosition().getX() >= WIDTH
                || hero.getPosition().getY() < 0 || hero.getPosition().getY() >= WIDTH) {
            System.out.println(hero.getName() + " touched lava! You lose.");
            isOver = true;
        } else if (hero.getPosition().getX() == treasure.getX()
                && hero.getPosition().getY() == treasure.getY()) {
            System.out.println(hero.getName() + " found the treasure! You win.");
            isOver = true;
        }
    }
}
