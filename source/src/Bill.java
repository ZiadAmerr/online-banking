package source.src;

public class Bill extends Buyable {
    private final int accountNumber;
    private boolean isPaid = false;


    // Constructor
    public Bill(String name, float price, int accountNumber) {
        super(name, price);
        this.accountNumber = accountNumber;
    }


    // Getters
    public int getAccountNumber() {
        return accountNumber;
    }
    public boolean getIsPaid() {
        return isPaid;
    }
    public void pay() {
        isPaid = true;
    }


    // Data getter
    public BillData getData() {
        return new BillData(
                getName(),
                getPrice(),
                accountNumber,
                isPaid
        );
    }


    // Override
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bill bill) {
            return bill.getName().equals(getName()) &&
                    bill.getPrice() == getPrice() &&
                    bill.getAccountNumber() == getAccountNumber();
        }
        return false;
    }
}
