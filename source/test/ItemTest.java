import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
class ItemTest {
    private static Item item0;
    private static Item item1;
    @BeforeAll
    static void init(){
        item0 = new Item(Item.ItemType.ITEM,5,"pepsi");
        item1 = new Item(Item.ItemType.BILL,400,"fees");
    }
    @Test
    void getId() {
        assertEquals(0,item0.getId());
        assertEquals(1,item1.getId());
    }

    @Test
    void getName() {
        assertEquals("pepsi",item0.getName());
        assertEquals("fees",item1.getName());
    }

    @Test
    void getPriceCaseNormal() {
        assertEquals(5,item0.getPrice());
        assertEquals(400,item1.getPrice());
    }

    @Test
    void itemWithNegativePrice(){
        assertThrows(IllegalArgumentException.class ,()->
        { new Item(Item.ItemType.ITEM,-23,"water");});
    }

    @Test
    void getDatePurchased() {

    }

    @Test
    void getAccount() {
    }

    @Test
    void purchase() {
    }

    @Test
    void getType() {
        assertEquals(Item.ItemType.ITEM,item0.getType());
        assertEquals(Item.ItemType.BILL,item1.getType());
    }
}