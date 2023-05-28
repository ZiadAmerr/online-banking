package source.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import source.src.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
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
    void testLogin() {
        assertFalse(user.isLoggedIn());
        assertTrue(user.login(USERNAME, PASSWORD));
        assertTrue(user.isLoggedIn());

        user.logout();
        assertFalse(user.isLoggedIn());
        assertFalse(user.login(USERNAME, "Pswd"));
        assertFalse(user.isLoggedIn());
    }

    @Test
    void testCreateAccount() {
        // TODO: Test with invalid currency and type
        // TODO: Test with other currencies and types
        User user1 = new User("x","y","z");
        assertFalse(user1.createAccount("EGP", "Checking"));

        user1.login("y", "z");
        assertTrue(user1.createAccount("EGP", "Checking"));
        assertEquals(1, user1.getAccountNums().size());

        assertTrue(user1.createAccount("EGP", "Checking"));
        assertEquals(2, user1.getAccountNums().size());
        assertEquals(user1.getAccountNums().get(0) + 1, user1.getAccountNums().get(1));
    }

    @Test
    void testUseAccount() {
        User user1 = new User("x","y","z");
        assertTrue(user1.login("y", "z"));
        assertTrue(user1.createAccount("EGP", "Checking"));
        int acc1 = user1.getAccountNums().get(0);
        assertTrue(user1.useAccount(acc1));


        User user2 = new User("temp","temp","temp");
        user2.login("temp","temp");
        user2.createAccount("EGP", "Checking");
        int acc2 = user2.getAccountNums().get(0);
        assertFalse(user2.useAccount(acc1));
        assertTrue(user2.useAccount(acc2));
    }

    @Test
    void logout() {
        user.login(USERNAME, PASSWORD);
        assertTrue(user.isLoggedIn());
        user.logout();
        assertFalse(user.isLoggedIn());
    }

    @Test
    void testBuy() {
        // 1. Add item
        // 2. Login
        // 3. Create account
        // 4. Use account
        // 5. Try to buy item
        // 6. Deposit money

        Shop.addNewItem("cola", 3, 100);

        user.login(USERNAME, PASSWORD);

        user.createAccount("EGP", "Checking");

        int accNum = user.getAccountNums().get(0);
        user.useAccount(accNum);

        assertFalse(user.buy("cola"));

        user.deposit(323);
        assertTrue(user.buy("cola"));
        assertEquals(1, user.hasHowManyItems("cola"));
        assertTrue(user.buy("cola"));
        assertTrue(user.buy("cola"));
        assertFalse(user.buy("cola"));
        assertEquals(3, user.hasHowManyItems("cola"));
        assertEquals(0, user.hasHowManyItems("pepsi"));
    }

    @Test
    void testTransfer() {
        User user1 = new User("x","y","z");
        User user2 = new User("temp","temp","temp");

        user1.login("y","z");
        user2.login("temp","temp");

        user1.createAccount("EGP", "Checking");
        user2.createAccount("EGP", "Checking");

        int currAccNum = user1.getAccountNums().get(0);
        int otherAccNum = user2.getAccountNums().get(0);

        // Acc num does not exist
        assertFalse(user1.transfer(12, 1324));

        // Not enough money
        assertFalse(user1.transfer(12, otherAccNum));

        // Not using account
        assertFalse(user1.transfer(0, otherAccNum));
        assertFalse(user2.transfer(0, currAccNum));

        // use Account
        user1.useAccount(currAccNum);
        user2.useAccount(otherAccNum);

        // Using account
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

        assertThrows(IllegalArgumentException.class,
                        () -> user1.transfer(-12, otherAccNum));
    }

    @Test
    void testViewTransactions() {
        User user1 = new User("x","y","z");
        // Not logged in
        assertNull(user1.viewTransactions());

        user1.login("y","z");
        user1.createAccount("EGP", "Checking");

        // Not using an account
        assertNull(user1.viewTransactions());

        user1.useAccount(user1.getAccountNums().get(0));
        assertEquals(0, user1.viewTransactions().size());

        assertTrue(user1.deposit(123));
        assertEquals(1, user1.viewTransactions().size());

        assertFalse(user1.transfer(12,123));
        assertEquals(1, user1.viewTransactions().size());
    }

    @Test
    void testGetNotifications() {
        User user1 = new User("x","y","z");
        user1.login("y","z");
        assertEquals(0, user1.getNotifications().size());

        user1.createAccount("EGP", "Checking");
        assertEquals(1, user1.getNotifications().size());

        user1.createAccount("EGP", "Checking");
        assertEquals(2, user1.getNotifications().size());
    }

    @Test
    void getName() {
        assertSame("Bruce", user.getName());
    }

    @Test
    void isLoggedIn() {
        assertFalse(user.isLoggedIn());
        user.login(USERNAME, PASSWORD);
        assertTrue(user.isLoggedIn());

        user.logout();
        assertFalse(user.isLoggedIn());
        user.login(USERNAME, "Pswd");
        assertFalse(user.isLoggedIn());
    }

    @Test
    void getUsername() {
        assertSame(USERNAME, user.getUsername());
    }
}