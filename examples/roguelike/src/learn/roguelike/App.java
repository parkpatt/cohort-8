package learn.roguelike;

import learn.roguelike.game.Game;

public class App {

    public static void main(String[] args) {
        Game game = new Game();
        game.run();

//        Position position = new Position(1, 1);
//        String positionString = position.toString();
//        System.out.println(position);
//
//        Monster monster = new Monster("Big Bad", 1, 1, new Fireball(), new AggresiveMovement());
//        String monsterString = monster.toString();

//        Random rand = new Random();
//
//        Attack monsterAttack = rand.nextBoolean()
//                ? new Fireball()
//                : new Claw();
//
//        Movement movement = rand.nextBoolean()
//                ? new AggresiveMovement()
//                : new DefensiveMovement();
//
//        Character monster = new Monster("Scary guy", 1, 1, monsterAttack, movement);
//
//        int damage = monster.performAttack();
//
//        System.out.printf("%s damage: %s", monsterAttack.getName(), damage);
    }
}
