package source.src;

import java.time.LocalDateTime;

public class Transaction {
    public final int fromAccountNumber;
    public final float amount;
    public final LocalDateTime date;
    private int toAccount;
    private boolean isToItem;
    private String item;



    public Transaction(float amount, int fromAccount) {
//        if (!Account.getAccountNumbers().contains(fromAccount))
//            throw new IllegalArgumentException("Account with number " + fromAccount + " does not exist");
        this.amount = amount;
        this.fromAccountNumber = fromAccount;
        this.date = LocalDateTime.now();
    }
    public Transaction(float amount, int fromAccount, int toAccount) {
        this(amount, fromAccount);
        this.toAccount = toAccount;
        this.isToItem = false;
        this.item = null;
    }
    public Transaction(int fromAccount, String toItem) {
        this(Shop.getPrice(toItem), fromAccount);
        this.toAccount = -1;
        this.isToItem = true;
        this.item = toItem;
    }

    public Object getRecipient() {
        if (isToItem) {
            return item;
        } else {
            return toAccount;
        }
    }

    /**
     * Returns an array of the transaction details
     * @return The array contains the following elements:
     * <ol start="0">
     *   <li>fromAccountNumber - type: int
     *   <li>amount - type: float
     *   <li>date - type: LocalDateTime
     *   <li>toAccount - type: int
     *   <li>isToItem - type: boolean
     *   <li>item - type: String
     */
    public int getToAccount(){
        return toAccount;
    }
    public boolean getIsToItem(){
        return isToItem;
    }
    public String getItem(){

        return item;
    }


    public Object[] getData() {
        return new Object[] {
                fromAccountNumber,
                amount,
                date,
                toAccount,
                isToItem,
                item
        };
    }
    
    public static void main(String[] args) {
        Transaction t = new Transaction(100, 1, 2);
        Object[] data = t.getData();
        assert (int) data[0] == 1;
        assert (int) data[1] == 100;
        assert data[2] instanceof LocalDateTime;
        assert (int) data[3] == 2;
        assert !((boolean) data[4]);
        assert data[5] == null;

//        t = new Transaction(1, "item");
//        data = t.getData();
//        assert data[0] == 1;
//        assert data[1] == Shop.getPrice("item");
//        assert data[2] instanceof LocalDateTime;
//        assert data[3] == -1;
//        assert (boolean) data[4] == false;
//        assert data[5] == "item";
    }
}
