package source.src;
public record BillsData(String bill, float price) {
    public String getBill(){
        return bill;
    }
    public float getPrice(){
        return price;
    }
}
