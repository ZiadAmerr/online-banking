package source.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final String name;
    private final String username;
    private final String password;
    private final ArrayList<Account> accounts = new ArrayList<>();
    private final ArrayList<Notification> notifications = new ArrayList<>();
    private Account account = null;
    private boolean loggedIn = false;
    private static final Map<String, Integer> inventory = new HashMap<>();

    // Constructor
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = Helpers.getHash(password);
    }

    // User functions
    public boolean login(String username, String password) {
        if (loggedIn)
            return true;

        if (this.username.equals(username) && this.password.equals(Helpers.getHash(password))) {
            loggedIn = true;
            return true;
        }

        return false;
    }
    public boolean createAccount() {
        if (!loggedIn)
            return false;

        Account account = new Account(this);
        accounts.add(account);
        notifications.add(
                new Notification("Account with number " + account.getNumber() + " was created")
        );
        return true;
    }
    public boolean useAccount(int number) {
        if (!loggedIn)
            return false;
        
        for(Account curr_account : accounts)
            if (curr_account.getNumber() == number) {
                account = curr_account;
                return true;
            }

        account = null;
        return false;
    }
    public void logout() {
        loggedIn = false;
    }

    // Account functions
    public boolean buy(String name) {
        // 1. Check if the user is logged in.
        // 2. Check if the user is using an account.
        // 3. Check if the item exists.
        // 4. Check if the user has enough money.
        // 5. Withdraw money from the user's account.
        // 6. Add the item to the user's inventory.
        // 7. Add a notification to the user.
        // 8. Return true if the item was bought successfully.
        // 9. Return false if the item was not bought successfully.
        if (!loggedIn) return false;

        if (account == null) {
            notifications.add(
                    new Notification("You are not currently using an account")
            );
            return false;
        }

        if (!Shop.hasItem(name)) {
            notifications.add(
                    new Notification("Item with name " + name + " does not exist")
            );
            return false;
        }

        float price = Shop.getPrice(name);

        if (price > account.getBalance()) {
            notifications.add(
                    new Notification("You don't have enough money to buy " + name)
            );
            return false;
        }

        if (!account.withdraw(price)) {
            notifications.add(
                    new Notification("Could not withdraw " + price + " from your account")
            );
            return false;
        }

        if (!Shop.sellItem(name)) {
            notifications.add(
                    new Notification("Shop could not sell " + name)
            );
            return false;
        }
        inventory.put(name, inventory.getOrDefault(name, 0) + 1);

        account.transact(price, name);
        notifications.add(new Notification("You bought " + name + " for " + price));

        return true;
    }
    public boolean transfer(float amount, int toAccountNumber) {
        // 1. Check if the user is logged in.
        // 2. Check if the user is using an account.
        // 3. Check if amount is positive
        // 4. Check if other account exists
        // 5. Check if amount is sufficient
        // 6. Withdraw amount from user's account
        // 7. Deposit amount to other account
        // 8. Add a notification about the successful transfer.
        // 9. Return true to indicate a successful transfer.
        // 10. If any of the above steps fail, add a notification about the failure and return false.
        if (!loggedIn) return false;

        if (account == null) {
            notifications.add(
                    new Notification("You are not currently using an account")
            );
            return false;
        }

        if (amount < 0) throw new IllegalArgumentException("Amount must be positive");

        Account to_account = Account.getAccountByNumber(toAccountNumber);

        if (to_account == null) {
            notifications.add(
                    new Notification("source.src.Account with number " + toAccountNumber + " does not exist")
            );
            return false;
        }

        if (amount > account.getBalance()) {
            notifications.add(
                    new Notification("You don't have enough money to transfer " + amount + " to " + to_account.getUsername())
            );
            return false;
        }

        if (account.withdraw(amount)) {
            to_account.deposit(amount);
            notifications.add(
                    new Notification("You transferred " + amount + " to " + to_account.getUsername())
            );

            account.transact(amount, account.getNumber());

            return true;
        } else {
            notifications.add(
                    new Notification("Could not withdraw " + amount + " from your account")
            );
            return false;
        }
    }
    public boolean deposit(float amount) {
        // 1. Check if the user is logged in.
        // 2. Check if the user is using an account.
        // 3. Check if amount is positive
        // 4. Deposit amount to user's account
        // 5. Add a notification about the successful deposit.
        // 6. Return true to indicate a successful deposit.
        // 7. If any of the above steps fail, add a notification about the failure and return false.
        if (!loggedIn) return false;

        if (account == null) {
            notifications.add(
                    new Notification("You are not currently using an account")
            );
            return false;
        }

        if (amount < 0) throw new IllegalArgumentException("Amount must be positive");

        account.deposit(amount);  // account.balance += amount
        notifications.add(
                new Notification("You deposited " + amount + " to your account")
        );

        account.transact(amount, account.getNumber());

        return true;
    }
    public float getBalance() {
        // 1. Check if the user is logged in.
        // 2. Check if the user is using an account.
        // 3. Return the balance of the user's account.
        // 4. Return -1 if the user is not logged in or is not using an account.
        if (!loggedIn || account == null)
            return -1;
        return account.getBalance();
    }

    // Getters
    public ArrayList<Transaction> viewTransactions() {
        // 1. Check if the user is logged in.
        // 2. Check if the user is using an account.
        // 3. Return the transactions of the user's account.
        // 4. Return null if the user is not logged in or is not using an account.
         if (!loggedIn || account == null)
             return null;
         return account.getTransactions();
    }
    public ArrayList<Notification> getNotifications() {
        return notifications;
    }
    public String getName() {
        return name;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public String getUsername() {
        return username;
    }
    public int hasHowMany(String name) {
        return inventory.getOrDefault(name, 0);
    }
    public ArrayList<Integer> getAccountNums() {
        ArrayList<Integer> accountNums = new ArrayList<>();
        for (Account account : accounts) {
            accountNums.add(account.getNumber());
        }
        return accountNums;
    }

    /**
     * Returns an array of the account details
     * @return The array contains the following elements:
     * <ol start="0">
     *      <li>accountNumber - type: int
     *      <li>balance - type: float
     *      <li>transactions - type: ArrayList of Transaction
     * */
    public ArrayList<AccountData> getAccountsData() {
        if (!loggedIn) return null;

        ArrayList<Integer> accountNums = getAccountNums();
        ArrayList<AccountData> accountsData = new ArrayList<>();

        for (int accountNum : accountNums) {
            accountsData.add(getAccountData(accountNum));
        }

        return accountsData;
    }
    private AccountData getAccountData(int accountNum) {
        if (!loggedIn) return null;

        Account account = Account.getAccountByNumber(accountNum);

        if (account == null) return null;

        return account.getData();
    }


    public ArrayList<TransactionData> getTransactionsData() {
        if (!loggedIn || account == null)
            return null;
        ArrayList<Transaction> transactions = viewTransactions();
        ArrayList<TransactionData> transactionRows = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionRows.add(getTransactionData(transaction));
        }
        return transactionRows;
}
    private TransactionData getTransactionData(Transaction transaction){

        return new TransactionData(transaction.amount,transaction.getToAccount(),transaction.fromAccountNumber,transaction.getIsToItem(),
                transaction.getItem(),transaction.date);

    }




        public static void main(String[] args) {
        User ziad = new User("Ziad", "ziad", "1234");

        ziad.login("ziad", "1234");

        assert ziad.isLoggedIn();

        ziad.createAccount();
        ziad.createAccount();
        ziad.createAccount();

        int curr_num = ziad.getAccountNums().get(2);

        // ziad.useAccount(curr_num);

        ziad.deposit(1000);

        ziad.buy("Apple");

        ziad.logout();
    }
}
