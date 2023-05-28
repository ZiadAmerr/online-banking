package source.src;

public record BillData(
        String name,
        float price,
        int accountNumber,
        boolean isPaid
) {
    // getters
    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public boolean getIsPaid() {
        return isPaid;
    }
}