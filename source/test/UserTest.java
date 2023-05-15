package source.test;
import source.src.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    static User user;
    @BeforeAll
    static void init() {
        user =  new User("bruce", "batman", "ilovecatwoman");
    }

    @Test
    void login() {
        assertTrue(user.login("batman", "ilovecatwoman"));
        user.logout();
        assertFalse(user.login("batman", "Ilovecatwoman"));
    }

    @Test
    void testIsLoggedIn() {
        assertFalse(user.isLoggedIn());
        user.login("batman", "ilovecatwoman");
        assertTrue(user.isLoggedIn());
    }

    @Test
    void useAccount() {

    }

    @Test
    void logout() {
    }

    @Test
    void buyItem() {
    }

    @Test
    void payBill() {
    }

    @Test
    void transferMoney() {
    }

    @Test
    void viewTransactions() {
    }

    @Test
    void getNotifications() {
    }

    @Test
    void getName() {
    }

    @Test
    void getId() {
    }
}