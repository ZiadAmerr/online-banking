package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;

import source.src.*;

public class TestShopItems {
    @BeforeEach
    public static void setUp() {
        Shop.addNewItem("item1", 1, 10.0f);
        Shop.addNewItem("item2", 2, 20.0f);
    }

    @AfterEach
    public void breakUp(){
        Shop.reset();
    }

    @Test
    public void testAddNewItem() {
        Shop.addNewItem("item3", 3, 30.0f);
        assertTrue(Shop.hasItem("item3"));
    }

    @Test
    public void testAddNonExistingItemError() {
        assertThrows(IllegalArgumentException.class, () -> {
            Shop.addItem("banana", 5);
        });
    }

    @Test
    public void testAddItemAfterAddingNewItem() {
        // Test adding an item to the shop after adding a new item
        Shop.reset();
        Shop.addNewItem("cola", 10, 0.5f);
        Shop.addItem("cola", 5);

        assertTrue(Shop.itemExisted("cola"));

        assertEquals(0.5f, Shop.getItemPrice("cola"), 0.001f);

        User user = new User("Ziad", "ziad", "1234");
        user.login("ziad", "1234");
        user.createAccount("EGP", "Checking");
        user.useAccount(user.getAccountNums().get(0));
        user.deposit(100);
        for (int i = 0; i < 15; i++) {
            user.buy("cola");
        }
        assertEquals(15, user.hasHowManyItems("cola"));
        assertFalse(Shop.hasItem("cola"));
    }

    @Test
    public void testSellItemError() {
        // Test selling an item that exists in the shop
        Shop.addNewItem("apple", 10, 0.5f);
        for (int i = 0; i < 10; i++) {
            assertTrue(Shop.sellItem("apple"));
        }
        assertFalse(Shop.sellItem("apple"));

        // Test selling an item that does not exist in the shop
        assertFalse(Shop.sellItem("banana"));
    }
}
