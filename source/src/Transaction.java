package source.src;

import java.time.LocalDateTime;

class Transaction {
    public final int fromAccountNumber;
    public final float amount;
    public final LocalDateTime date;
    private int toAccount;
    private boolean isToItem;
    private String item;

    public Transaction(float amount, int fromAccount) {
        if (!Account.getAccountNumbers().contains(fromAccount))
            throw new IllegalArgumentException("Account with number " + fromAccount + " does not exist");
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

    /** TODO: implement this method
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
    public Object[] getData() {
        Object[] transactionDetails = new Object[6];
        transactionDetails[0] = fromAccountNumber;
        transactionDetails[1] = amount;
        transactionDetails[2] = date;
        transactionDetails[3] = toAccount;
        transactionDetails[4] = isToItem;
        transactionDetails[5] = item;

        return transactionDetails;
    }
}
