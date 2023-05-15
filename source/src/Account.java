package source.src;

import java.util.ArrayList;

public class Account {
    private static final ArrayList<Account> accounts = new ArrayList<>();
    private static int number_counter = 0;
    private final int number;
    private float balance;
    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private final User user;

    // Constructor
    Account(User user) {
        number = number_counter++;
        balance = 0;
        this.user = user;
        accounts.add(this);
    }

    // Static functions
    static Account getAccountByNumber(int number) {
        for(Account account : accounts)
            if (account.getNumber() == number)
                return account;
        return null;
    }

    // Money functions
    public void deposit(float amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount must be positive");

        balance += amount;
    }
    public boolean withdraw(float amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount must be positive");

        float new_balance = balance - amount;
        if (new_balance < 0) {
            return false;
        }

        balance = new_balance;
        return true;
    }

    // getters
    public float getBalance() {
        return balance;
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public String getUsername() {
        return user.getUsername();
    }
    public int getNumber() {
        return number;
    }
}
