package source.src;

public record InventoryData(int item,int count) {
    public int getItem(){
        return item;
    }
    public int getCount(){
        return count;
    }
}
