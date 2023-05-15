package source.src;

import java.time.LocalDateTime;

public class Transaction {
    private final float amount;
    private final LocalDateTime date;
    private final Account fromAccount;
    private Account toAccount;
    private boolean isToItem;
    private Item item;

    public Transaction(float amount, Account fromAccount) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.date = LocalDateTime.now();
    }
    public Transaction(float amount, Account fromAccount, Account toAccount) {
        this(amount, fromAccount);
        this.toAccount = toAccount;
        this.isToItem = false;
        this.item = null;
    }
    public Transaction(Account fromAccount, Item toItem) {
        this(toItem.getPrice(), fromAccount);
        this.toAccount = null;
        this.isToItem = true;
        this.item = toItem;
    }

    public float getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Object getRecipient() {
        if (isToItem) {
            return item;
        } else {
            return toAccount;
        }
    }
}
