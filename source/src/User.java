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
    private static final Map<Item, Integer> inventory = new HashMap<>();

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

        Item item = Shop.getItem(name);

        if (item == null) {
            notifications.add(
                    new Notification("Item with name " + name + " does not exist")
            );
            return false;
        }

        float price = item.price;

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

        inventory.put(item, inventory.getOrDefault(item, 0) + 1);

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
}
