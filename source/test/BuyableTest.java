package source.test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import source.src.*;

public class BuyableTest {

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }
    @Test
    public void testGetName() {
        String name = "Product";
        float price = 10.0f;
        Buyable buyable = new BuyableImpl(name, price);
        String actualName = buyable.getName();
        assertEquals(name, actualName);
    }

    @Test
    public void testGetPrice() {
        String name = "Product";
        float price = 10.0f;
        Buyable buyable = new BuyableImpl(name, price);
        float actualPrice = buyable.getPrice();
        assertEquals(price, actualPrice, 0.01);
    }

    @Test
    public void testInvalidPrice() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new BuyableImpl("Product", -10.0f));

        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test
    public void testEmptyName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new BuyableImpl("", 10.0f));

        assertEquals("Name cannot be empty", exception.getMessage());
    }

    public static class BuyableImpl extends Buyable {
        public BuyableImpl(String name, float price) {
            super(name, price);
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}