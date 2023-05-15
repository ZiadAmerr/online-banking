

import java.time.LocalDateTime;
import java.util.ArrayList;

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
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");

        if (name == null || name.equals(""))
            throw new IllegalArgumentException("Name cannot be empty");

        if (type == null)
            throw new IllegalArgumentException("Type cannot be null");

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
            if (type == ItemType.BILL)
                wasPurchased = true;
            return true;
        }
        return false;
    }



    // getters
    public ItemType getType() {
        return type;
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
        if (datePurchased == null)
            return LocalDateTime.MIN;
        return datePurchased;
    }
    public int getAccountNumber() {
        if (account == null)
            return -1;
        return account.getNumber();
    }

    private static void main(String[] args) {
    }
}

