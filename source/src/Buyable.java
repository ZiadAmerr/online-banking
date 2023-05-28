package source.src;

public abstract class Buyable {
    private static int counter = 0;
    private final String name;
    private final float price;
    private final int count;


    // Constructor
    protected Buyable(String name, float price) throws IllegalArgumentException {
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");

        if (name == null || name.equals(""))
            throw new IllegalArgumentException("Name cannot be empty");

        this.name = name;
        this.price = price;
        this.count = counter++;
    }


    // Getters
    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public int getCount() {
        return count;
    }


    // Override
    @Override
    public abstract boolean equals(Object obj);
}
