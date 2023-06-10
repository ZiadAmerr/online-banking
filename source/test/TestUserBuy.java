package source.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import source.src.Shop;
import source.src.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserBuy {
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
    void UnseccPurchase() {
        Shop.addNewItem("cola", 3, 100);

        user.login(USERNAME, PASSWORD);

        user.createAccount("EGP", "Checking");

        int accNum = user.getAccountNums().get(0);
        user.useAccount(accNum);

        assertFalse(user.buy("cola"));
    }
    @Test
    void SeccPurchase() {


        user.login(USERNAME, PASSWORD);

        user.createAccount("EGP", "Checking");

        int accNum = user.getAccountNums().get(0);
        user.useAccount(accNum);
        user.deposit(323);
        assertTrue(user.buy("cola"));
        assertEquals(1, user.hasHowManyItems("cola"));
        assertTrue(user.buy("cola"));
        assertTrue(user.buy("cola"));
        assertFalse(user.buy("cola"));
        assertEquals(3, user.hasHowManyItems("cola"));
        assertEquals(0, user.hasHowManyItems("pepsi"));

    }
}
