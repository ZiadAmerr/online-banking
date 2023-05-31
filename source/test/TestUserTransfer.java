package source.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import source.src.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserTransfer {
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
    void testNoExictNum(){
        User user1 = new User("x","y","z");
        User user2 = new User("temp","temp","temp");

        user1.login("y","z");
        user2.login("temp","temp");

        user1.createAccount("EGP", "Checking");
        user2.createAccount("EGP", "Checking");



        assertFalse(user1.transfer(12, 1324));
    }
    @Test
    void testNotEnoughMoney(){
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
    void testNotusingAcc(){
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
    void testUseAcc(){
        User user1 = new User("x","y","z");
        User user2 = new User("temp","temp","temp");

        user1.login("y","z");
        user2.login("temp","temp");

        user1.createAccount("EGP", "Checking");
        user2.createAccount("EGP", "Checking");
        int currAccNum = user1.getAccountNums().get(0);
        int otherAccNum = user2.getAccountNums().get(0);
        assertTrue(user1.transfer(0, otherAccNum));
        assertTrue(user2.transfer(0, currAccNum));

        user1.useAccount(user1.getAccountNums().get(0));
        assertFalse(user1.transfer(12, otherAccNum));

        user1.deposit(24);
        assertEquals(24, user1.getBalance());
        assertEquals(0, user2.getBalance());

        assertTrue(user1.transfer(12, otherAccNum));
        assertEquals(12, user1.getBalance());
        assertEquals(12, user2.getBalance());

        assertTrue(user1.transfer(12, otherAccNum));
        assertEquals(0, user1.getBalance());
        assertEquals(24, user2.getBalance());

        assertFalse(user1.transfer(12, otherAccNum));
        assertEquals(0, user1.getBalance());
        assertEquals(24, user2.getBalance());

        assertTrue(user2.transfer(12, currAccNum));
        assertEquals(12, user1.getBalance());
        assertEquals(12, user2.getBalance());

        assertTrue(user2.transfer(12, currAccNum));
        assertEquals(24, user1.getBalance());
        assertEquals(0, user2.getBalance());

        assertFalse(user2.transfer(12, currAccNum));
        assertEquals(24, user1.getBalance());
        assertEquals(0, user2.getBalance());
    }
    @Test
    void IllegalException(){
        User user1 = new User("x","y","z");
        User user2 = new User("temp","temp","temp");

        user1.login("y","z");
        user2.login("temp","temp");

        user1.createAccount("EGP", "Checking");
        user2.createAccount("EGP", "Checking");
        int currAccNum = user1.getAccountNums().get(0);
        int otherAccNum = user2.getAccountNums().get(0);
        assertThrows(IllegalArgumentException.class,

                () -> user1.transfer(-12, otherAccNum));
    }


}
