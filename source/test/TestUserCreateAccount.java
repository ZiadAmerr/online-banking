package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

public class TestUserCreateAccount {
    static User user;
    private static final String USERNAME = "batman";
    private static final String PASSWORD = "pswd";

    @BeforeAll
    static void setUp() {
        user =  new User("Bruce", USERNAME, PASSWORD);
    }

    @AfterEach
    void breakUp(){
        user.logout();
    }

    @Test
    public void testInvalidCreateAccount() {
        User user1 = new User("x","y","z");
        assertFalse(user1.createAccount("EGP", "Checking"));
    }
    @Test
    public void testValidCreateAccount() {
        User user1 = new User("x","y","z");
        user1.login("y", "z");
        assertTrue(user1.createAccount("EGP", "Checking"));
        assertEquals(1, user1.getAccountNums().size());
    }
    @Test
    public void testCreateAnotherValidAccount() {
        User user1 = new User("x","y","z");
        user1.login("y", "z");
        assertTrue(user1.createAccount("EGP", "Checking"));
        assertTrue(user1.createAccount("USD", "Checking"));
        assertEquals(2, user1.getAccountNums().size());
        assertEquals((long) user1.getAccountNums().get(0) + 1, (long) user1.getAccountNums().get(1));
    }

}
