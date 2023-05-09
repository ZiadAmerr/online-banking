import java.util.*;

public class User {
    private int user_id;
    private String name;
    private String email;
    private String password;
    private List<Account> accounts;
    private List<Notification> notifications;

    public void buy_item(Item item, Account account) {
    }

    public void pay_bill(Item bill, Account account) {
    }

    public void transfer_money(double amount, Account from_account, Account to_account) {
    }

    public void view_statements(Account account) {
    }

    public List<Notification> get_notifications() {
        return notifications;
    }
}