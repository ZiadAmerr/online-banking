package source.test;

import org.junit.jupiter.api.Test;
import source.src.Item;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testEquals() {
        Item item = new Item("cola",32);
        Item item2 = new Item("cola",32);
        assertEquals(item, item2);
    }

    @Test
    void testEqualsDiffPrice(){
        Item item = new Item("cola",32);
        Item item2 = new Item("cola",39);
        assertNotEquals(item, item2);
    }

    @Test
    void testEqualsDiffName(){
        Item item = new Item("pepsi",32);
        Item item2 = new Item("cola",39);
        assertNotEquals(item, item2);
    }

    @Test
    void testConstructor(){
        assertThrows(IllegalArgumentException.class,()->new Item("cola",-12));
    }
}