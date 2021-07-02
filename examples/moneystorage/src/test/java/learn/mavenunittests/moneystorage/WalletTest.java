package learn.mavenunittests.moneystorage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    @Test
    void shouldDepositPositiveValue() {
        Wallet wallet = new Wallet(0, "Test");
        boolean result = wallet.deposit(123.0);
        double actual = wallet.getBalance();
        assertTrue(result);
        assertEquals(123.0, actual);
    }

    @Test
    void shouldNotDepositNegativeValue(){
        Wallet wallet = new Wallet(0, "Test");
        boolean result = wallet.deposit(-123.0);
        double actual = wallet.getBalance();
        assertFalse(result);
        assertEquals(0.0, actual);
    }

    @Test
    void withdraw() {
    }
}