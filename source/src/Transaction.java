

import java.time.LocalDateTime;

public class Transaction {
    private static int idCounter = 0;
    private final int id;
    private final double amount;
    private final LocalDateTime date;
    private final Account fromAccount;
    private Account toAccount;
    private boolean isToItem;
    private Item item;

    public Transaction(double amount, Account fromAccount) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.id = idCounter++;
        this.date = LocalDateTime.now();
    }
    public Transaction(double amount, Account fromAccount, Account toAccount) {
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

    public int getId() {
        return id;
    }

    public double getAmount() {
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
