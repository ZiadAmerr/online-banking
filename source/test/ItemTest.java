import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

class ItemTest {
    private static Item item0;
    private static Item item1;
    @BeforeAll
    static void init(){
        item0 = new Item(Item.ItemType.ITEM,5,"pepsi");
        item1 = new Item(Item.ItemType.BILL,99999,"fees");
    }
    @Test
    void testGetId() {
        assertEquals(0,item0.getId());
        assertEquals(1,item1.getId());
    }

    @Test
    void testGetName() {
        assertEquals("pepsi",item0.getName());
        assertEquals("fees",item1.getName());
    }

    @Test
    void getPriceCaseNormal() {
        assertEquals(5,item0.getPrice());
        assertEquals(99999,item1.getPrice());
    }

    @Test
    void itemWithNegativePrice(){
        assertThrows(IllegalArgumentException.class ,()->
                new Item(Item.ItemType.ITEM,-23,"water"));
    }

    @Test
    void getDatePurchased() {
        User user = new User("Bruce","batman","ilovecatwoman");
        Account account = new Account(user);
        account.deposit(5000);
        item0.purchase(account);
        assertEquals(LocalDateTime.now(),item0.getDatePurchased());
    }

    @Test
    void getAccount() {
        User user = new User("Bruce","batman","ilovecatwoman");
        Account account = new Account(user);
        account.deposit(5000);
        item0.purchase(account);
        assertEquals(account,item0.getAccount());
    }

    @Test
    void purchase() {
        User user = new User("Bruce","batman","ilovecatwoman");
        Account account = new Account(user);
        account.deposit(5000);
        assertTrue(item0.purchase(account));
        assertFalse(item1.purchase(account));

    }

    @Test
    void getType() {
        assertEquals("ITEM",item0.getType());
        assertEquals("BILL",item1.getType());
    }
}