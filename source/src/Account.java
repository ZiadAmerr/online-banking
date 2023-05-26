package source.src;

import java.util.ArrayList;

class Account {
    private static final ArrayList<Account> accounts = new ArrayList<>();
    private static int number_counter = 0;
    private final int number;
    private float balance;
    private String type;
    private String currency;
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
        balance += amount;
    }
    public boolean withdraw(float amount) {
        float new_balance = balance - amount;
        if (new_balance < 0) {
            return false;
        }

        balance = new_balance;
        return true;
    }

    // Transaction functions
    public void transact(float amount, int other_account_number) {
        Account other = getAccountByNumber(other_account_number);
        if (other == null)
            throw new IllegalArgumentException("Account with number " + other_account_number + " does not exist");

        transactions.add(new Transaction(amount, this.getNumber(), other_account_number));
    }
    public void transact(float amount, String name) {
        if (!Shop.itemExisted(name))
            throw new IllegalArgumentException("Item with name " + name + " does not exist");

        transactions.add(new Transaction(this.getNumber(), name));
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
    public String getType() {
        return type;
    }
    public String getCurrency() {
        return currency;
    }

    public AccountData getData() {
        return new AccountData(
                number,
                balance,
                currency,
                type
        );
    }


    // Function that returns an ArrayList of all the account numbers
    public static ArrayList<Integer> getAccountNumbers() {
        ArrayList<Integer> account_numbers = new ArrayList<>();
        for(Account account : accounts)
            account_numbers.add(account.getNumber());
        return account_numbers;
    }
}
