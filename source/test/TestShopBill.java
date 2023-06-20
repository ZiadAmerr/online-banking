package source.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

public class TestShopBill {
    private static User user1;
    private static User user2;
    private static int accNum1;
    private static int accNum2;

    @BeforeAll
    public static void init() {
        // Initialize the Shop class here
        Shop.addBill("bill1", 100.0f, accNum1);
        Shop.addBill("bill2", 200.0f, accNum2);
    }

    @AfterAll
    public static void cleanUp() {
        // Reset the Shop class here
        Shop.reset();
    }

    @BeforeEach
    public void setUp() {
        // Initialize the User and Account objects here
        user1 = new User("ahmed","ahmed","ah");
        user2 = new User("osama","osama","os");
        user1.login("ahmed","ah");
        user2.login("osama","os");
        user1.createAccount("EGP","Checking");
        user2.createAccount("EGP","Savings");

        user1.useAccount(user1.getAccountNums().get(0));
        user2.useAccount(user2.getAccountNums().get(0));
        accNum1 = user1.getAccountNumber();
        accNum2 = user2.getAccountNumber();
    }

    @AfterEach
    public void tearDown() {
        // Reset the User and Account objects here
        user1 = null;
        user2 = null;
        accNum1 = 0;
        accNum2 = 0;
    }

    @Test
    public void testAddBillWithNonexistentAccount() {
        assertThrows(IllegalArgumentException.class, () -> {
            Shop.addBill("bill4", 400.0f, 111);
        });
    }

    @Test
    public void testAddBillWithNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Shop.addBill("bill5", -500.0f, accNum1);
        });
    }
}