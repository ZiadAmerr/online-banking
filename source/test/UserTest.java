

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    static User user;
    static Account account;
    static Account account2;
    @BeforeAll
    static void init() {
        user =  new User("Bruce", "batman", "ilovecatwoman");
        account = new Account(user);
        account2 = new Account(user);

    }

    @Test
    void login() {
        assertTrue(user.login("batman", "ilovecatwoman"));
        user.logout();
        assertFalse(user.login("batman", "Ilovecatwoman"));
    }

    @Test
    void testIsLoggedIn() {
        assertFalse(user.isLoggedIn());
        user.login("batman", "ilovecatwoman");
        assertTrue(user.isLoggedIn());
    }

    @Test
    void useAccount() {
        System.out.println(user.login("batman", "ilovecatwoman"));
        assertTrue(user.useAccount(0));
        assertTrue(user.useAccount(1));
    }

    @Test
    void logout() {
        user.login("batman", "ilovecatwoman");
        assertTrue(user.isLoggedIn());
        user.logout();
        assertFalse(user.isLoggedIn());
    }

    @Test
    void buyItem() {
        Item item = new Item("Gun", 34, Item.ItemType.ITEM);
        user.useAccount(0);
        account.deposit(1000);
        user.buyItem(item);

    }

    @Test
    void payBill() {
        Item bill = new Item("Gas", 100, Item.ItemType.BILL);
        user.useAccount(1);
        account2.deposit(200);
        assertTrue(user.payBill(bill));
    }

    @Test
    void transferMoney() {
        account.withdraw(account.getBalance());
        assertEquals(0, account.getBalance());
        user.useAccount(1);
        account2.deposit(150);
        assertTrue(user.transferMoney(100,0));
        assertEquals(100,account.getBalance());
    }

    @Test
    void viewTransactions() {
        assertTrue(user.viewTransactions().size()>1);
    }

    @Test
    void getNotifications() {
        assertTrue(user.getNotifications().size()>1);
    }

    @Test
    void getName() {
        assertEquals("Bruce", user.getName());
    }

    @Test
    void getId() {
        assertEquals(0,user.getId());
    }
}