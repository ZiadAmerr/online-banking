import java.util.List;

public class Account {
    private int account_number;
    private double balance;
    private List<Transaction> transaction_history;
    private User owner;

    public void deposit(double amount) {
    }

    public void withdraw(double amount) {
    }

    public void transfer_money(double amount, Account to_account) {
    }

    public double get_balance() {
        return 0.0;
    }

    public List<Transaction> view_statements() {
        return null;
    }
}