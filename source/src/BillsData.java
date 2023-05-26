package source.src;

public record BillsData(int bill,int item,float price) {
    public int getBill(){
        return bill;
    }
    public int getItem(){
        return item;
    }
    public float getPrice(){
        return price;
    }
}
