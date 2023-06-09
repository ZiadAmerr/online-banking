package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

import java.util.Collections;

public class TestUserViewTransc {
    static User user;
    private static final String USERNAME = "batman";
    private static final String PASSWORD = "pswd";
    User user1 = new User("x","y","z");

    @BeforeAll
    static void setUp() {
        user =  new User("Bruce", USERNAME, PASSWORD);
    }

    @AfterEach
    void breakUp(){
        user.logout();
    }

    @Test
    public void testNotLoggedIn(){
        assertEquals(user1.viewTransactions(), Collections.emptyList());
    }
    @Test
    public void testNotUsedAcc(){
        user1.login("y","z");
        user1.createAccount("EGP", "Checking");

        assertEquals(user1.viewTransactions(), Collections.emptyList());

    }
    @Test
    public void testMakingDeposit(){
        User user1 = new User("x","y","z");
        // Not logged in
        assertEquals(user1.viewTransactions(), Collections.emptyList());

        user1.login("y","z");
        user1.createAccount("EGP", "Checking");
        user1.useAccount(user1.getAccountNums().get(0));
        assertEquals(0, user1.viewTransactions().size());

        assertTrue(user1.deposit(123));
        assertEquals(1, user1.viewTransactions().size());
    }

    @Test
    public void testFailedTransfer(){
        User user1 = new User("x","y","z");
        user1.login("y","z");
        user1.createAccount("EGP", "Checking");
        assertEquals(user1.viewTransactions(), Collections.emptyList());
        assertFalse(user1.transfer(12,123));
        assertEquals(0, user1.viewTransactions().size());
    }
}
