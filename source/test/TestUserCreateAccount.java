package source.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import source.src.User;

import static org.junit.jupiter.api.Assertions.*;

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
    void testInvalidCreateAccount() {
        User user1 = new User("x","y","z");
        assertFalse(user1.createAccount("EGP", "Checking"));
    }
    @Test
    void testValidCreateAccount() {
        User user1 = new User("x","y","z");
        user1.login("y", "z");
        assertTrue(user1.createAccount("EGP", "Checking"));
        assertEquals(1, user1.getAccountNums().size());
    }
    @Test
    void testCreateAnotherValidAccount() {
        User user1 = new User("x","y","z");
        assertTrue(user1.createAccount("EGP", "Checking"));
        assertEquals(2, user1.getAccountNums().size());
        assertEquals(user1.getAccountNums().get(0) + 1, user1.getAccountNums().get(1));
    }

}
