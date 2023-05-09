import java.time.LocalDateTime;

public class Item {
    private static int id_counter = 0;
    private final int id;
    private final String name;
    private final ItemType type;
    private final double price;
    private LocalDateTime datePurchased = null;
    private Account account = null;
    private boolean wasPurchased = false;

    public enum ItemType {
        BILL,
        ITEM
    }
    public Item(ItemType type, double price, String name) {
        id = id_counter++;
        this.name = name;
        this.type = type;
        this.price = price;
        wasPurchased = false;
        datePurchased = null;
        this.account = null;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public LocalDateTime getDatePurchased() {
        return datePurchased;
    }
    public Account getAccount() {
        return account;
    }
    public boolean purchase(Account account) {
        if (wasPurchased) {
            return false;
        }
        if (account.withdraw(price)) {
            this.account = account;
            datePurchased = LocalDateTime.now();
            wasPurchased = true;
            return true;
        }
        return false;
    }
    public String getType() {
        return type.toString();
    }

    private static void main(String[] args) {
        // Create a new item object
        Item item = new Item(Item.ItemType.ITEM, 10.00, "Test Item");
        Item bill = new Item(Item.ItemType.BILL, 200.00, "Test Item");


        // Test the getId() method
        assert item.getId() == 0 : "getId() test failed";
        assert bill.getId() == 1 : "getId() test failed";

        // Test the getName() method
        assert item.getName().equals("Test Item") : "getName() test failed";

        // Test the getPrice() method
        assert item.getPrice() == 10.00 : "getPrice() test failed";

        // Test the getDatePurchased() method before purchase
        assert item.getDatePurchased() == null : "getDatePurchased() test failed";

        // Test the getAccount() method before purchase
        assert item.getAccount() == null : "getAccount() test failed";

        // Test the purchase() method with sufficient funds
//        Account account = new Account(20.00);
//        boolean result = item.purchase(account);
//        assert result == true : "purchase() test failed";
//        assert item.getAccount() == account : "purchase() test failed";
//        assert item.getDatePurchased() != null : "purchase() test failed";
//
//        // Test the purchase() method with insufficient funds
//        account = new Account(5.00);
//        result = item.purchase(account);
//        assert result == false : "purchase() test failed";
//
//        // Test the getType() method
//        assert item.getType().equals("ITEM") : "getType() test failed";
    }
}

