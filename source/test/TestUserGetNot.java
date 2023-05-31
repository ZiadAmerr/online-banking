package source.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import source.src.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserGetNot {
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
    void testNoNotifications(){
        user1.login("y","z");
        assertEquals(0, user1.getNotifications().size());
    }
    @Test
    void testReceiveNotifications(){
        user1.createAccount("EGP", "Checking");
        assertEquals(1, user1.getNotifications().size());

        user1.createAccount("EGP", "Checking");
        assertEquals(2, user1.getNotifications().size());
    }
}
