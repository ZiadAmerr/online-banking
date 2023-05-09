import java.util.List;

public class Account {
    private static int account_number_counter = 0;
    private final int account_number;
    private double balance;
    private List<Transaction> transaction_history;
    private final User owner;

    public Account(User owner) {
        account_number = account_number_counter++;
        balance = 0.0;
        transaction_history = null;
        this.owner = owner;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        double new_balance = balance - amount;
        if (new_balance < 0) {
            return false;
        } else {
            balance = new_balance;
            return true;
        }
    }

    public boolean transfer_money(double amount, Account to_account) {
        if (withdraw(amount)) {
            to_account.deposit(amount);
            return true;
        }
        return false;
    }

    public double get_balance() {
        return balance;
    }

    public List<Transaction> view_statements() {
        return transaction_history;
    }
}