package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

public class TestUserLogin {

    private static final String USERNAME = "batman";
    private static final String PASSWORD = "pswd";
    static User user = new User("Bruce", USERNAME, PASSWORD);



    @Test
    public void testLoginCorrectPass() {
        assertFalse(user.isLoggedIn());
        assertTrue(user.login(USERNAME, PASSWORD));
        assertTrue(user.isLoggedIn());
    }
    @Test
    public void testLoginWrongPass(){
        user.logout();
        assertFalse(user.isLoggedIn());
        assertFalse(user.login(USERNAME, "Pswd"));
        assertFalse(user.isLoggedIn());
    }

}
