package source.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import source.src.User;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUserLogout {
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
    void SuccLogout() {
        user.login(USERNAME, PASSWORD);
        assertTrue(user.isLoggedIn());
    }
    @Test
    void FailedLogout() {
        assertFalse(user.isLoggedIn());
    }
}
