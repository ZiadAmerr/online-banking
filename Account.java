import java.time.LocalDateTime;
import java.util.ArrayList;

public class Account {
    private static ArrayList<Account> accounts = new ArrayList<Account>();
    private static int number_counter = 0;
    private final int number;
    private double balance;
    private final ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private final User owner;

    public Account(User owner) {
        number = number_counter++;
        balance = 0.0;
        this.owner = owner;
        accounts.add(this);
    }
    
    public static Account getAccountByNumber(int number) {
        for(Account account : accounts)
            if (account.getNumber() == number)
                return account;
        return null;
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

    public boolean buy(Item item) {
        if (!item.buy(this)) return false;
        transactions.add(new Transaction(this, item));
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public User getOwner() {
        return owner;
    }

    public int getNumber() {
        return number;
    }
}
