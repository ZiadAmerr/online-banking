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
}

