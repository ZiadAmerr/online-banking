package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

public class TestUserTransfer {
    static User user;
    static float DELTA = 0.001f;
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
    public void testNoExictNum(){
        User user1 = new User("x","y","z");
        User user2 = new User("temp","temp","temp");

        user1.login("y","z");
        user2.login("temp","temp");

        user1.createAccount("EGP", "Checking");
        user2.createAccount("EGP", "Checking");



        assertFalse(user1.transfer(12, 1324));
    }
    @Test
    public void testNotEnoughMoney(){
        User user1 = new User("x","y","z");
        User user2 = new User("temp","temp","temp");

        user1.login("y","z");
        user2.login("temp","temp");

        user1.createAccount("EGP", "Checking");
        user2.createAccount("EGP", "Checking");
        int currAccNum = user1.getAccountNums().get(0);
        int otherAccNum = user2.getAccountNums().get(0);
        assertFalse(user1.transfer(12, otherAccNum));
    }
    @Test
    public void testNotusingAcc(){
        User user1 = new User("x","y","z");
        User user2 = new User("temp","temp","temp");

        user1.login("y","z");
        user2.login("temp","temp");

        user1.createAccount("EGP", "Checking");
        user2.createAccount("EGP", "Checking");
        int currAccNum = user1.getAccountNums().get(0);
        int otherAccNum = user2.getAccountNums().get(0);
        assertFalse(user1.transfer(0, otherAccNum));
        assertFalse(user2.transfer(0, currAccNum));
    }
    @Test
    public void testUseAcc(){
        User user1 = new User("x","y","z");
        User user2 = new User("temp","temp","temp");

        user1.login("y","z");
        user2.login("temp","temp");

        user1.createAccount("EGP", "Checking");
        user2.createAccount("EGP", "Checking");
        int currAccNum = user1.getAccountNums().get(0);
        int otherAccNum = user2.getAccountNums().get(0);
        user1.useAccount(currAccNum);
        user2.useAccount(otherAccNum);
        assertTrue(user1.transfer(0, otherAccNum));
        assertTrue(user2.transfer(0, currAccNum));

        user1.useAccount(user1.getAccountNums().get(0));
        assertFalse(user1.transfer(12, otherAccNum));

        user1.deposit(24);
        assertEquals(24, user1.getBalance(), DELTA);
        assertEquals(0, user2.getBalance(), DELTA);

        assertTrue(user1.transfer(12, otherAccNum));
        assertEquals(12, user1.getBalance(), DELTA);
        assertEquals(12, user2.getBalance(), DELTA);

        assertTrue(user1.transfer(12, otherAccNum));
        assertEquals(0, user1.getBalance(), DELTA);
        assertEquals(24, user2.getBalance(), DELTA);

        assertFalse(user1.transfer(12, otherAccNum));
        assertEquals(0, user1.getBalance(), DELTA);
        assertEquals(24, user2.getBalance(), DELTA);

        assertTrue(user2.transfer(12, currAccNum));
        assertEquals(12, user1.getBalance(), DELTA);
        assertEquals(12, user2.getBalance(), DELTA);

        assertTrue(user2.transfer(12, currAccNum));
        assertEquals(24, user1.getBalance(), DELTA);
        assertEquals(0, user2.getBalance(), DELTA);

        assertFalse(user2.transfer(12, currAccNum));
        assertEquals(24, user1.getBalance(), DELTA);
        assertEquals(0, user2.getBalance(), DELTA);
    }
    @Test
    public void IllegalException(){
        User user1 = new User("x","y","z");
        User user2 = new User("temp","temp","temp");

        user1.login("y","z");
        user2.login("temp","temp");

        user1.createAccount("EGP", "Checking");
        user2.createAccount("EGP", "Checking");
        int currAccNum = user1.getAccountNums().get(0);
        int otherAccNum = user2.getAccountNums().get(0);
        user1.useAccount(currAccNum);
        user2.useAccount(otherAccNum);
        assertThrows(IllegalArgumentException.class,

                () -> user1.transfer(-12, otherAccNum));
    }


}
