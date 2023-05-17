import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    static User user;
    private final String userName = "batman";
    private final String pass = "ilovecatwoman";

    @BeforeAll
    static void setUp() {
        user =  new User("Bruce", "batman", "ilovecatwoman");
    }

    @AfterEach
    void breakUp(){
        user.logout();
    }

    @Test
    void login() {
        assertTrue(user.login("batman", "ilovecatwoman"));
        user.logout();
        assertFalse(user.login("batman", "Ilovecatwoman"));
    }

    @Test
    void createAccount() {
        assertEquals(-1, user.createAccount());
        user.login(userName,pass);
        assertTrue(user.createAccount()>=0);

    }

    @Test
    void useAccount() {
        user.login(userName,pass);
        int ac1 = user.createAccount();
        assertTrue(user.useAccount(ac1));
        User user2 = new User("temp","temp","temp");
        int ac2 = user2.createAccount();
        assertFalse(user.useAccount(ac2));
        user2.login("temp","temp");
        assertTrue(user2.useAccount(ac2));
        assertFalse(user2.useAccount(ac1));


    }

    @Test
    void logout() {
        user.login(userName,pass);
        assertTrue(user.isLoggedIn());
        user.logout();
        assertFalse(user.isLoggedIn());
    }

    @Test
    void buy() {
        Item item = new Item("cola",32, Item.ItemType.ITEM);
        Shop.addItem("cola",32);
        user.login(userName,pass);
        user.createAccount();
        int ac1 = user.getAccountNums()[0];
        user.useAccount(ac1);
        Account.getAccountByNumber(ac1).deposit(323);
        assertTrue(user.buy("cola"));
    }

    @Test
    void transfer() {
        User user2 = new User("temp","temp","temp");
        user.login(userName,pass);
        int ac1 = user.createAccount();
        int ac2 = user2.createAccount();
        Account.getAccountByNumber(ac1).deposit(100);
        assertThrows(IllegalArgumentException.class,()->user.transfer(-12,ac2));
        assertTrue(user.transfer(10,ac2));
        assertEquals(10, Account.getAccountByNumber(ac2).getBalance());
    }

    @Test
    void viewTransactions() {
        user.login(userName,pass);
        int ac1 = user.createAccount();
        Account.getAccountByNumber(ac1).deposit(123);
        user.transfer(12,2);
        assertEquals(1, user.viewTransactions().size());
    }

    @Test
    void getNotifications() {
        user.login(userName,pass);
        user.createAccount();
        user.createAccount();
        assertEquals(2, user.getNotifications().size());
    }

    @Test
    void getName() {
        assertSame("Bruce", user.getName());
    }

    @Test
    void isLoggedIn() {
        assertFalse(user.isLoggedIn());
        user.login("batman", "ilovecatwoman");
        assertTrue(user.isLoggedIn());
    }

    @Test
    void getUsername() {
        assertSame("batman", user.getUsername());
    }
}