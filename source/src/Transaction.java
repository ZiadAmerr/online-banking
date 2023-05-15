package source.src;

import java.time.LocalDateTime;

public class Transaction {
    public final float amount;
    public final LocalDateTime date;
    public final int fromAccountNumber;
    private Account toAccount;
    private boolean isToItem;
    private Item item;

    public Transaction(float amount, Account fromAccount) {
        this.amount = amount;
        this.fromAccountNumber = fromAccount.getNumber();
        this.date = LocalDateTime.now();
    }
    public Transaction(float amount, Account fromAccount, Account toAccount) {
        this(amount, fromAccount);
        this.toAccount = toAccount;
        this.isToItem = false;
        this.item = null;
    }
    public Transaction(Account fromAccount, Item toItem) {
        this(toItem.price, fromAccount);
        this.toAccount = null;
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
