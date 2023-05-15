package source.src;

public class Item {
    private final String name;
    private final ItemType type;
    private final float price;
    public enum ItemType {
        BILL,
        ITEM
    }

    // Constructor
    Item(String name, float price, ItemType type) {
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");

        if (name == null || name.equals(""))
            throw new IllegalArgumentException("Name cannot be empty");

        if (type == null)
            throw new IllegalArgumentException("Type cannot be null");

        this.name = name;
        this.type = type;
        this.price = price;
    }



    // Core function
//    public boolean buy(Account account) {
//        if (wasPurchased) {
//            return false;
//        }
//        if (account.withdraw(price)) {
//            this.account = account;
//            datePurchased = LocalDateTime.now();
//            if (type == ItemType.BILL)
//                wasPurchased = true;
//            return true;
//        }
//        return false;
//    }



    // getters
    public ItemType getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
//    public LocalDateTime getDatePurchased() {
//        if (datePurchased == null)
//            return LocalDateTime.MIN;
//        return datePurchased;
//    }
//    public int getAccountNumber() {
//        if (account == null)
//            return -1;
//        return account.getNumber();
//    }

    private static void main(String[] args) {
    }
}

