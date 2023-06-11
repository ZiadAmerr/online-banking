package source.src;

import java.util.*;

public class User {
    public static final String ERROR_NOT_USING_ACCOUNT = "You are not currently using an account";
    private final String name;
    private final String username;
    private final String password;
    private final ArrayList<Account> accounts = new ArrayList<>();
    private final ArrayList<Notification> notifications = new ArrayList<>();
    private Account account = null;
    private boolean loggedIn = false;
    private final Map<String, Integer> itemsInventory = new HashMap<>();
    private static final ArrayList<User> users = new ArrayList<>();

    // Constructor
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = Helpers.getHash(password);
    }

    // Get User
    public static User getUser(String username) {
        for(User user : users)
            if (user.getUsername().equals(username))
                return user;
        return null;
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
    public boolean createAccount(String currency, String type) {
        if (!loggedIn)
            return false;

        if (!currency.equals("EGP") && !currency.equals("USD"))
            throw new IllegalArgumentException("Currency must be either EGP or USD");

        if (!type.equals("Checking") && !type.equals("Savings"))
            throw new IllegalArgumentException("Account type must be either Checking or Savings");

        Account createdAccount = new Account(this, currency, type);
        accounts.add(createdAccount);
        notifications.add(
                new Notification("Account with number " + createdAccount.getNumber() + " was created")
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
        // 6. Add the item to the user's itemsInventory.
        // 7. Add a notification to the user.
        // 8. Return true if the item was bought successfully.
        // 9. Return false if the item was not bought successfully.
        if (!loggedIn) return false;

        if (account == null) {
            notifications.add(
                    new Notification(ERROR_NOT_USING_ACCOUNT)
            );
            return false;
        }
        boolean hasBill = Shop.hasBill(name) && account.hasBill(name);
        boolean hasItem = Shop.hasItem(name);
        if (!hasBill && !hasItem) {
            notifications.add(
                    new Notification("Buyable with name " + name + " does not exist")
            );
            return false;
        }

        float price;
        if (hasBill) {
            price = Shop.getBillPrice(name);
        } else {
            price = Shop.getItemPrice(name);
        }

        float balanceInEGP = Helpers.convert(account.getCurrency(), "EGP", account.getBalance());
        float priceInAccountCurrency = Helpers.convert("EGP", account.getCurrency(), price);

        if (price > balanceInEGP) {
            notifications.add(
                    new Notification("You don't have enough money to buy " + name)
            );
            return false;
        }

        if (!account.withdraw(priceInAccountCurrency)) {
            notifications.add(
                    new Notification("Could not withdraw " + priceInAccountCurrency + " " +  account.getCurrency() + " from your account")
            );
            return false;
        }

        if (!(hasBill ? Shop.payBill(name) : Shop.sellItem(name))) {
            notifications.add(new Notification("Shop could not sell " + name));
            return false;
        }

        if (hasItem)
            itemsInventory.put(name, itemsInventory.getOrDefault(name, 0) + 1);

        account.transact(name);
        notifications.add(new Notification("You bought " + name + " for " + priceInAccountCurrency + " " + account.getCurrency()));

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
                    new Notification(ERROR_NOT_USING_ACCOUNT)
            );
            return false;
        }

        if (amount < 0) throw new IllegalArgumentException("Amount must be positive");

        Account toAcc = Account.getAccountByNumber(toAccountNumber);

        if (toAcc == null) {
            notifications.add(
                    new Notification("source.src.Account with number " + toAccountNumber + " does not exist")
            );
            return false;
        }

        if (amount > account.getBalance()) {
            notifications.add(
                    new Notification("You don't have enough money to transfer " + amount + " to " + toAcc.getUsername())
            );
            return false;
        }

        if (account.withdraw(amount)) {
            toAcc.deposit(Helpers.convert(account.getCurrency(), toAcc.getCurrency(), amount));
            notifications.add(
                    new Notification("You transferred " + amount + " to " + toAcc.getUsername())
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
                    new Notification(ERROR_NOT_USING_ACCOUNT)
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
    public boolean withdraw(float amount) {
        if (!loggedIn) return false;

        if (account == null) {
            notifications.add(
                    new Notification(ERROR_NOT_USING_ACCOUNT)
            );
            return false;
        }

        if (amount < 0) throw new IllegalArgumentException("Amount must be positive");

        if (account.withdraw(amount)) {
            notifications.add(
                    new Notification("You withdrew " + amount + " " + account.getCurrency() + " from your account")
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
    public List<Transaction> viewTransactions() {
        // 1. Check if the user is logged in.
        // 2. Check if the user is using an account.
        // 3. Return the transactions of the user's account.
        // 4. Return null if the user is not logged in or is not using an account.
         if (!loggedIn || account == null)
             return Collections.emptyList();
         return account.getTransactions();
    }
    public List<Notification> getNotifications() {
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
    public int hasHowManyItems(String name) {
        return itemsInventory.getOrDefault(name, 0);
    }
    public List<Integer> getAccountNums() {
        ArrayList<Integer> accountNums = new ArrayList<>();
        for (Account acc : accounts) {
            accountNums.add(acc.getNumber());
        }
        return accountNums;
    }
    public List<Bill> getAllBills() {
        ArrayList<Bill> bills = new ArrayList<>();
        for (Account acc : accounts) {
            bills.addAll(acc.getBills());
        }
        return bills;
    }
    public List<Bill> getBills() {
        if (!loggedIn || account == null)
            return Collections.emptyList();
        return account.getBills();
    }
    public int getAccountNumber() {
        if (!loggedIn || account == null)
            throw new IllegalStateException("User is not logged in or is not using an account");
        return account.getNumber();
    }


    // Getters for data
    public List<AccountData> getAccountsData() {
        if (!loggedIn) return Collections.emptyList();

        return accounts
                .stream()
                .map(Account::getData)
                .toList();
    }
    public List<TransactionData> getTransactionsData() {
        if (!loggedIn || account == null)
            return null;

        return account.getTransactions()
                .stream()
                .map(Transaction::getData)
                .toList();
    }
    public List<NotificationData> getNotificationsData() {
        return notifications
                .stream()
                .map(Notification::getData)
                .toList();
    }
    public List<InventoryData> getInventoryData() {
        return itemsInventory
                .entrySet()
                .stream()
                .map(entry -> new InventoryData(entry.getKey(), entry.getValue()))
                .toList();
    }
    
}