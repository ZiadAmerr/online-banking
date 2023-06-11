package source.src;

public class Item extends Buyable {
    // Constructor
    public Item(String name, float price) {
        super(name, price);
    }


    // Override
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item item) {
            return item.getName().equals(getName()) && item.getPrice() == getPrice();
        }
        return false;
    }
}


