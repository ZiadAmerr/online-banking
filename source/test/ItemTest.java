package source.test;
import source.src.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class ItemTest {
    private static Item item0;
    private static Item item1;
    @BeforeAll
    static void init() {
        item0 = new Item("Pepsi",5, Item.ItemType.ITEM);
        item1 = new Item("Fees",99999, Item.ItemType.BILL);
    }
    @Test
    void testGetId() {
        assertEquals(0, item0.getId());
        assertEquals(1, item1.getId());
    }
    @Test
    void testGetName() {
        assertEquals("Pepsi", item0.getName());
        assertEquals("Fees", item1.getName());
    }
    @Test
    void getType() {
        Assertions.assertEquals(Item.ItemType.ITEM, item0.getType());
        Assertions.assertEquals(Item.ItemType.BILL, item1.getType());
    }
    @Test
    void testGetPrice() {
        assertEquals(5, item0.getPrice());
        assertEquals(99999, item1.getPrice());
        assertThrows(IllegalArgumentException.class ,()->
                new Item("Water", -23, Item.ItemType.ITEM));
    }
    @Test
    void testBuy() {
        User user = new User("Bruce","batman","ilovecatwoman");
        Account account = new Account(user);
        account.deposit(5000);
        assertTrue(item0.buy(account));
        assertFalse(item1.buy(account));

    }
    @Test
    void getDatePurchased() {
        User user = new User("Bruce", "batman", "ilovecatwoman");
        Account account = new Account(user);
        account.deposit(5000);


        assertEquals(LocalDateTime.MIN, item0.getDatePurchased());
        assertEquals(LocalDateTime.MIN, item1.getDatePurchased());

        assertTrue(item0.buy(account));

        assertEquals(
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                item0.getDatePurchased().truncatedTo(ChronoUnit.SECONDS)
        );
    }
    @Test
    void getAccount() {
        User user1 = new User("Bruce","batman","ilovecatwoman");
        User user2 = new User("Bruce","batman","ilovecatwoman");
        User user3 = new User("Bruce","batman","ilovecatwoman");
        User user4 = new User("Bruce","batman","ilovecatwoman");
        Account account = new Account(user4);
        account.deposit(5000);

        assertTrue(item0.buy(account));
        assertEquals(account.getNumber(), item0.getAccountNumber());

        assertFalse(item1.buy(account));
        assertEquals(-1, item1.getAccountNumber());
    }
}