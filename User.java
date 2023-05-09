import java.util.*;

public class User {
    private static int counter = 0;
    private final int id;
    private final String name;
    private final String username;
    private final String password;
    private final Set<Account> accounts;
    private final List<Notification> notifications;
    private Account account = null;
    private boolean isLoggedIn = false;
    
    public User(String name, String username, String password) {
        this.id = counter++;
        this.name = name;
        this.username = username;
        this.password = password;
        this.accounts = new HashSet<>();
        this.notifications = new ArrayList<>();
    }
    public boolean login(String username, String password) {
        if (isLoggedIn)
            return true;

        if (this.username.equals(username) && this.password.equals(password)) {
            isLoggedIn = true;
            return true;
        }

        return false;
    }
    public boolean useAccount(int number) {
        if (!isLoggedIn)
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
        isLoggedIn = false;
    }
    public boolean buyItem(Item item) {
        if (account.buy(item)) {
            notifications.add(
                    new Notification("You don't have enough money to buy " + item.getName())
            );
            return false;
        }

        notifications.add(
                new Notification("You bought " + item.getName() + " for " + item.getPrice())
        );
        return true;

    }
    public boolean payBill(Item bill) {
        if (!account.buy(bill)) {
            notifications.add(
                    new Notification("You don't have enough money to pay " + bill.getName())
            );
            return false;
        }

        notifications.add(
                new Notification("You paid " + bill.getName() + " for " + bill.getPrice())
        );
        return true;
    }

    public boolean transferMoney(double amount, int toNumber) {
        if (!isLoggedIn) return false;

        if (account == null) {
            notifications.add(
                    new Notification("You are not currently using an account")
            );
            return false;
        }

        Account toAccount = Account.getAccountByNumber(toNumber);

        if (toAccount == null) {
            notifications.add(
                    new Notification("Account with number " + toNumber + " does not exist")
            );
            return false;
        }

        if (!account.transfer(amount, toAccount)) {
            notifications.add(
                    new Notification("You don't have enough money to transfer " + amount + " to " + toAccount.getOwner().getName())
            );
            return false;
        }

        notifications.add(
                new Notification("You transferred " + amount + " to " + toAccount.getOwner().getName())
        );
        return true;
    }

    public List<Transaction> viewTransactions() {
        return !isLoggedIn || account == null ? null : account.getTransactions();
    }

    public List<Notification> getNotifications() {
        return notifications;
    }
    
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
