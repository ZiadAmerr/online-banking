import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Item {
    private static ArrayList<Item> items = new ArrayList<Item>();
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




    // Constructor
    public Item(String name, double price, ItemType type) {
        id = id_counter++;
        this.name = name;
        this.type = type;
        this.price = price;
        wasPurchased = false;
        datePurchased = null;
        this.account = null;
        items.add(this);
    }



    // Core function
    public boolean buy(Account account) {
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



    // getters
    public String getType() {
        return type.toString();
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

    private static void main(String[] args) {
    }
}

