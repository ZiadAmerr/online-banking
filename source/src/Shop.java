package source.src;

import java.util.*;

public class Shop {
    private static final Map<Item, Integer> items = new HashMap<>();
    private static final ArrayList<Bill> bills = new ArrayList<>();


    // Constructor
    private Shop() {} // Prevent instantiation


    // Items
    private static Item getItem(String name) {
        if (items.isEmpty())
            return null;

        for (Item item : items.keySet()) {
            if (item.getName().equals(name))
                return item;
        }
        return null;
    }
    public static void addItem(String name, int count) {
        Item item = getItem(name);
        if (item == null)
            throw new IllegalArgumentException("Item does not exist, please use addNewItem() instead");

        // Item exists, add it to the inventory
        items.put(item, items.get(item) + count);
    }
    public static void addNewItem(String name, int count, float price) {
        if (getItem(name) == null) {
            // Item does not exist, add it to the inventory
            items.put(new Item(name, price), 1);
            addItem(name, count - 1);
            return;
        }

        throw new IllegalArgumentException("Item already exists");
    }
    public static boolean sellItem(String name) {
        Item item = getItem(name);
        if (hasItem(name)) {
            items.put(item, items.get(item) - 1);
            return true;
        }
        return false;
    }
    public static boolean hasItem(String name) {
        Item item = getItem(name);
        return items.containsKey(item) && items.get(item) >= 1;
    }
    public static boolean itemExisted(String name) {
        return getItem(name) != null;
    }
    public static float getItemPrice(String name) {
        Item item = getItem(name);
        if (item == null)
            throw new IllegalArgumentException("Item does not exist");
        return item.getPrice();
    }


    // Bills
    private static Bill getBill(String name) {
        if (bills.isEmpty())
            return null;

        for (Bill bill : bills) {
            if (bill.getName().equals(name))
                return bill;
        }
        return null;
    }
    public static void addBill(String name, float price, int accountNumber) {
        Account account = Account.getAccountByNumber(accountNumber);
        if (account == null)
            throw new IllegalArgumentException("Account does not exist");

        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");

        Bill bill = new Bill(name, price, accountNumber);
        bills.add(bill);
        account.addBill(bill);
    }
    public static boolean payBill(String name) {
        Bill bill = getBill(name);
        if (hasBill(name)) {
            assert bill != null;
            bill.pay();
            return true;
        }
        return false;
    }
    public static boolean hasBill(String name) {
        return getBill(name) != null;
    }
    public static float getBillPrice(String name) {
        Bill bill = getBill(name);

        if (bill == null)
            throw new IllegalArgumentException("Bill does not exist");

        return bill.getPrice();
    }

    public static List<ItemData> getAllItemsData() {
        return items.entrySet()
                .stream()
                .map(entry -> new ItemData(
                        entry.getKey().getName(),
                        entry.getKey().getPrice(),
                        entry.getValue())
                )
                .toList();
    }
}