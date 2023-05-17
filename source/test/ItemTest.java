import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


class ItemTest {
    @Test
     void testConstructor() {
        try {
            Item item0 = new Item("Pepsi", 5, Item.ItemType.ITEM);
            Item item1 = new Item("Fees", 99999, Item.ItemType.BILL);
        }catch (Exception e){
            fail();
        }
        assertThrows(IllegalArgumentException.class,()->new Item("CocaCola",-32, Item.ItemType.ITEM));
        assertThrows(IllegalArgumentException.class,()->new Item("",32, Item.ItemType.ITEM));

    }

}
