

public class Item {
    public final String name;
    public final ItemType type;
    public final float price;
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
}

