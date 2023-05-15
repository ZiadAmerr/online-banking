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
}

