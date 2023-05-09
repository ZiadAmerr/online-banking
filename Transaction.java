import java.time.LocalDateTime;

public class Transaction {
    private int transaction_id;
    private double amount;
    private LocalDateTime date_time;
    private Account from_account;
    private Account to_account;

    public int get_transaction_id() {
        return 0;
    }

    public double get_amount() {
        return 0.0;
    }

    public LocalDateTime get_date_time() {
        return null;
    }

    public Account get_from_account() {
        return null;
    }

    public Account get_to_account() {
        return null;
    }
}
