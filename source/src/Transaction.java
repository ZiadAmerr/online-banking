package source.src;

import java.time.LocalDateTime;

public class Transaction {
    private final int fromAccountNumber;
    private final float amount;
    private final LocalDateTime date;
    private final int toAccount;
    private final boolean isToBuyable;
    private final String buyableName;
    private final String type;
    private static int counter = 0;
    private final int id;


    // Constructors
    public Transaction(String type, float amount, int fromAccount, int toAccount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount must be positive");

        if (Account.getAccountByNumber(fromAccount) == null)
            throw new IllegalArgumentException("Sender account does not exist");

        if (Account.getAccountByNumber(toAccount) == null)
            throw new IllegalArgumentException("Receiver account does not exist");

        this.amount = amount;
        this.fromAccountNumber = fromAccount;
        this.date = LocalDateTime.now();
        this.toAccount = toAccount;
        this.isToBuyable = false;
        this.buyableName = null;
        this.type = type;
        this.id = ++counter;
    }
    public Transaction(String type, int fromAccount, String toBuyable) {
        if (toBuyable == null || toBuyable.equals(""))
            throw new IllegalArgumentException("Name cannot be empty");

        if (!Shop.hasBill(toBuyable) && !Shop.itemExisted(toBuyable))
            throw new IllegalArgumentException("Buyable does not exist");

        if (Shop.hasBill(toBuyable))
            this.amount = Shop.getBillPrice(toBuyable);
        else
            this.amount = Shop.getItemPrice(toBuyable);


        this.fromAccountNumber = fromAccount;
        this.date = LocalDateTime.now();
        this.toAccount = -1;
        this.isToBuyable = true;
        this.buyableName = toBuyable;
        this.type = type;
        this.id = ++counter;
    }

    // getters
    public int getFromAccountNumber() {
        return fromAccountNumber;
    }


    // Data getter
    public TransactionData getData() {
        return new TransactionData(
                type,
                id,
                fromAccountNumber,
                amount,
                date,
                toAccount,
                isToBuyable,
                buyableName);
    }
}
