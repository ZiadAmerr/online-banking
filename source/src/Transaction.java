package source.src;

import java.time.LocalDateTime;

public class Transaction {
    public final float amount;
    public final LocalDateTime date;
    public final int fromAccountNumber;
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
}
