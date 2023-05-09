import java.util.List;

public class Account {
    private static int number_counter = 0;
    private final int number;
    private double balance;
    private List<Transaction> transactions;
    private final User owner;

    public Account(User owner) {
        number = number_counter++;
        balance = 0.0;
        transactions = null;
        this.owner = owner;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        double new_balance = balance - amount;
        if (new_balance < 0) {
            return false;
        }

        balance = new_balance;
        return true;
    }

    public boolean transfer(double amount, Account to_account) {
        if (!withdraw(amount))
            return false;

        to_account.deposit(amount);
        transactions.add(new Transaction(amount, this, to_account));
        return true;
    }

    public boolean pay(Item item) {
        if (!withdraw(item.getPrice()))
            return false;

        transactions.add(new Transaction(this, item));
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public User getOwner() {
        return owner;
    }

    public int getNumber() {
        return number;
    }
}