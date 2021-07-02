package learn.mavenunittests;

import learn.mavenunittests.asciirender.BoxDrawer;
import learn.mavenunittests.moneystorage.Person;
import learn.mavenunittests.moneystorage.Wallet;

public class App {
    public static void main(String[] args) {
        // COMPOSITION vs. INHERITANCE

        /* Take a look inside the Person class.
        The initial developer made an interesting design decision. In order to allow payment to a Person, they made
        the Person class extend Wallet. Currently, a Person is-a Wallet. While it sort of works - we can give a Person
        money via the `deposit` method and ask for money via `withdraw` - it's a little strange. A Person isn't truly
        a Wallet. A Person has-a Wallet.
         */

        // 1. Refactor the Person class so that it no longer extends Wallet. Instead, solve the problem using
        // composition - a has-a relationship.
        // 2. While you're at it, change Wallet to the more generic MoneyStorage.
        // Person may optionally have a MoneyStorage.

        Person p = new Person("Sidonnie", "Antonietti");
//        Wallet wallet = new Wallet(0.0, String.format("%s's Wallet", p.getFirstName()));
//        p.setMoneyStorage(wallet);

        System.out.println(p.getFullName());

        p.giveMoney(125.85);
        System.out.println(p.getMoneyStorage().getDescription());
        System.out.println(p.getMoneyStorage().getBalance());



//        BoxDrawer drawer = new BoxDrawer();
//        drawer.drawFancyBox();
    }
}
