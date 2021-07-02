package learn.mavenunittests.moneystorage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void shouldDepositMoneyIfMoneyStoragePresent() {
        Person person = new Person("Alfie", "Billington");
        Wallet wallet = new Wallet(0.0, person.getFirstName() + "'s Wallet");
        person.setMoneyStorage(wallet);

        boolean actual = person.giveMoney(120.0);
        double balance = person.getMoneyStorage().getBalance();

        assertTrue(actual);
        assertEquals(120.0, balance);
    }

    @Test
    void shouldNotAddMoneyIfMoneyDepositFails() {
        Person person = new Person("Alfie", "Billington");
        Wallet wallet = new Wallet(0.0, person.getFirstName() + "'s Wallet");
        person.setMoneyStorage(wallet);

        boolean result = person.giveMoney(-123.0);
        assertFalse(result);
    }

    @Test
    void shouldNotAddMoneyIfNoStorage() {
        Person person = new Person("Alfie", "Billington");
        boolean actual = person.giveMoney(120.0);

        assertFalse(actual);
    }
}