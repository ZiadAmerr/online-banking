package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

public class TestUserLogout {
    private static final String USERNAME = "batman";
    private static final String PASSWORD = "pswd";
    static User user = new User("Bruce", USERNAME, PASSWORD);


    @BeforeEach
    static void setUp() {
        user.login(USERNAME,PASSWORD);
    }

    @AfterEach
    void breakUp(){
        user.logout();
    }


    @Test
    public void SuccLogout() {
        user.login(USERNAME, PASSWORD);
        assertTrue(user.isLoggedIn());
    }
    @Test
    public void FailedLogout() {
        user.logout();
        assertFalse(user.isLoggedIn());
    }
}
