package source.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

public class TestShopGeneral {

    @BeforeEach
    public void setUp() {
        Shop.reset();
    }

    @Test
    public void testAddBill() {
        User user = new User("Ziad", "ziad", "1234");
        user.login("ziad", "1234");
        user.createAccount("EGP", "Checking");
        user.useAccount(user.getAccountNums().get(0));

        Shop.addBill("bill1", 10.0f, user.getAccountNumber());
        assertTrue(Shop.hasBill("bill1"));
        for (Bill bill : user.getBills()) {
            if (bill.getName().equals("bill1")) {
                assertEquals(10.0f, bill.getPrice(), 0.001f);
                return;
            }
        }
        fail("Didn't find bill1 in user's bills");
    }

    @Test
    public void testAddBillToNonExistingAccount() {
        assertThrows(IllegalArgumentException.class, () -> {
            Shop.addBill("bill1", 10.0f, 4383342);
        });
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAddNegativePriceBill() {
        User user = new User("Ziad", "ziad", "1234");
        user.login("ziad", "1234");
        user.createAccount("EGP", "Checking");
        user.useAccount(user.getAccountNums().get(0));

       Shop.addBill("bill1", -10.0f, user.getAccountNumber());
    }

    @Test
    public void testPayBill() {

        User user = new User("Ziad", "ziad", "1234");
        user.login("ziad", "1234");
        user.createAccount("EGP", "Checking");
        user.useAccount(user.getAccountNums().get(0));
        Shop.addBill("bill1", 10.0f, user.getAccountNumber());

        assertTrue(Shop.payBill("bill1"));
        assertTrue(Shop.hasBill("bill1"));

        // Test paying a bill that does not exist in the shop
        assertFalse(Shop.payBill("bill2"));
    }

    @Test
    public void testHasBill() {
        User user = new User("Ziad", "ziad", "1234");
        user.login("ziad", "1234");
        user.createAccount("EGP", "Checking");
        user.useAccount(user.getAccountNums().get(0));

        Shop.addBill("bill1", 10.0f, user.getAccountNumber());
        assertTrue(Shop.hasBill("bill1"));
    }
}
