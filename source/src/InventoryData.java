package source.src;

public record InventoryData(
        String item,
        int count
) {
    public String getItem() {
        return item;
    }
    public int getCount() {
        return count;
    }
}
