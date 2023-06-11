package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

public class TestUserBuy {
    private static final String USERNAME = "batman";
    private static final String PASSWORD = "pswd";
    static User user = new User("Bruce", USERNAME, PASSWORD);


    User user1 = new User("x","y","z");

    @BeforeAll
    static void setUp() {
        user.login(USERNAME, PASSWORD);
    }

    @AfterEach
    public void breakUp(){
        user.logout();
    }

    @Test
    public void testUnsuccessfulPurchase() {
        Shop.addNewItem("coca", 3, 100);

        assertNotNull(user);

        user.login(USERNAME, PASSWORD);

        assertTrue(user.createAccount("EGP", "Checking"));
        assertEquals(1, user.getAccountNums().size());

        int accNum = user.getAccountNums().get(0);
        user.useAccount(accNum);

        assertFalse(user.buy("cola"));
    }
    @Test
    public void testSuccessfulPurchase() {


        user.login(USERNAME, PASSWORD);

        user.createAccount("EGP", "Checking");

        int accNum = user.getAccountNums().get(0);
        user.useAccount(accNum);
        user.deposit(323);
        assertTrue(user.buy("coca"));
        assertEquals(1, user.hasHowManyItems("coca"));
        assertTrue(user.buy("coca"));
        assertTrue(user.buy("coca"));
        assertFalse(user.buy("coca"));
        assertEquals(3, user.hasHowManyItems("coca"));
        assertEquals(0, user.hasHowManyItems("pepsi"));

    }
}
