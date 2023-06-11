package source.test;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import source.src.Buyable;

import static org.junit.Assert.*;

public class BuyableTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    void testGetName() {
        String name = "Product";
        float price = 10.0f;
        Buyable buyable = new BuyableImpl(name, price);
        String actualName = buyable.getName();
        assertEquals(name, actualName);
    }

    @Test
    void testGetPrice() {
        String name = "Product";
        float price = 10.0f;
        Buyable buyable = new BuyableImpl(name, price);
        float actualPrice = buyable.getPrice();
        assertEquals(price, actualPrice, 0.01);
    }

    @Test
    void testInvalidPrice() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new BuyableImpl("Product", -10.0f));

        assertEquals("Price cannot be negative", exception.getMessage());
    }

    @Test
    void testEmptyName() {
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